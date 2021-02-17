/*
 * Copyright 2021 tritonit.tech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.tritonit.pdf.core.document;

import tech.tritonit.pdf.core.exceptions.ConfigurationException;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;
import tech.tritonit.pdf.core.font.AfmLoader;
import tech.tritonit.pdf.core.font.Font;
import tech.tritonit.pdf.core.font.FontVariant;
import tech.tritonit.pdf.core.image.AlphaChannel;
import tech.tritonit.pdf.core.image.Image;
import tech.tritonit.pdf.core.image.ImageData;
import tech.tritonit.pdf.core.image.ImageLoader;
import tech.tritonit.pdf.core.page.ContentStream;
import tech.tritonit.pdf.core.page.Page;
import tech.tritonit.pdf.core.page.PageTree;
import tech.tritonit.pdf.core.page.RootPageTree;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;
import static tech.tritonit.pdf.core.font.Font.*;
import static tech.tritonit.pdf.core.font.FontsVariant.PLAIN;

public class Pdf {
    /**
     * The context of this document shared among all PDF objects.
     */
    protected final PdfContext context;

    /**
     * The list of indirect objects.
     */
    protected final List<PdfObject> indirectObjects = new ArrayList<>();

    /**
     * The list of bytes written before each indirect object.
     */
    protected final List<Integer> indirectPositions = new ArrayList<>();

    /**
     * The catalog of PDF (root page tree for now only).
     */
    protected final Catalog catalog;

    /**
     * The root of pages tree.
     */
    protected final RootPageTree rootPageTree;

    /**
     * List of fonts used in this PDF document.
     */
    protected final Map<String, Font> fonts = new HashMap<>();

    /**
     * Instantiate a new empty PDF.
     */
    public Pdf() {
        this(Unit.MM);
    }

    /**
     * Instantiate a new empty PDF.
     *
     * @param unit The default unit of this document.
     */
    public Pdf(Unit unit) {
        context = new PdfContext();
        context.setUnit(unit);

        Font courier = loadFont(COURIER,
                Font.class.getResourceAsStream("/fonts/base14/Courier.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Courier-Bold.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Courier-Oblique.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Courier-BoldOblique.afm"));

        Font helvetica = loadFont(HELVETICA, Font.class.getResourceAsStream("/fonts/base14/Helvetica.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Helvetica-Bold.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Helvetica-Oblique.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Helvetica-BoldOblique.afm"));

        Font symbol = loadFont(SYMBOL, Font.class.getResourceAsStream("/fonts/base14/Symbol.afm"));

        Font timesRoman = loadFont(TIMES_ROMAN,
                Font.class.getResourceAsStream("/fonts/base14/Times-Roman.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Times-Bold.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Times-Italic.afm"),
                Font.class.getResourceAsStream("/fonts/base14/Times-BoldItalic.afm"));

        Font zapfDingbats = loadFont(ZAPF_DINGBATS, Font.class.getResourceAsStream("/fonts/base14/ZapfDingbats.afm"));

        context.setDefaultFontVariant(fonts.get(TIMES_ROMAN).getVariant(PLAIN));

        catalog = new Catalog(context);
        indirectObjects.add(catalog);
        rootPageTree = new RootPageTree(context, indirectObjects.size() + 1);
        rootPageTree.declareFont(courier);
        rootPageTree.declareFont(helvetica);
        rootPageTree.declareFont(symbol);
        rootPageTree.declareFont(timesRoman);
        rootPageTree.declareFont(zapfDingbats);

        indirectObjects.add(rootPageTree);
    }

    /**
     * Write PDF to stream.
     *
     * @param stream The stream to write PDF to.
     * @throws PdfGenerationException When PDF cannot be generated.
     */
    public void write(OutputStream stream) throws PdfGenerationException {
        try {
            int position = writeHeader(stream);
            position += writeBody(stream, position);
            writeXRef(stream);
            writeTrailer(stream);
            writeStartXRef(stream, position);
            stream.write(END_OF_FILE.getBytes());
        } catch (IOException e) {
            throw new PdfGenerationException("Cannot write PDF.", e);
        }
    }

    /**
     * Create a page.
     *
     * @param width  The width of the page.
     * @param height The height of the page.
     * @return The page reference.
     */
    public Page createPage(float width, float height) {
        PageTree pageTree = new PageTree(context, rootPageTree.getId(), indirectObjects.size() + 1, width, height);
        indirectObjects.add(pageTree);

        ContentStream contentStream = new ContentStream(context, indirectObjects.size() + 1);
        indirectObjects.add(contentStream);

        Page page = new Page(context, pageTree.getId(), indirectObjects.size() + 1, contentStream, width, height);
        indirectObjects.add(page);

        pageTree.addPageNode(page);
        rootPageTree.addPageNode(pageTree);

        return page;
    }

    /**
     * Create an image.
     *
     * @param imageStream The stream to get the image data from.
     * @return The image reference.
     * @throws PdfGenerationException When PDfd cannot be compressed.
     */
    public Image createImage(InputStream imageStream, ImageLoader imageLoader) throws PdfGenerationException {
        Image image = new Image(context, indirectObjects.size() + 1);

        try {
            ImageData imageData = imageLoader.load(imageStream);

            image.setWidth(imageData.getWidth());
            image.setOriginalWidth(imageData.getWidth());

            image.setHeight(imageData.getHeight());
            image.setOriginalHeight(imageData.getHeight());

            image.setPixels(deflate(imageData.getPixels()));

            indirectObjects.add(image);

            if (imageData.hasTransparency()) {
                //Create alpha channel PDF object.
                AlphaChannel alphaChannel = new AlphaChannel(context, indirectObjects.size() + 1);
                alphaChannel.setWidth(imageData.getWidth());
                alphaChannel.setHeight(imageData.getHeight());
                alphaChannel.setAlphaValues(deflate(imageData.getPixelsAlpha()));

                //Add alpha channel to indirect objects references.
                indirectObjects.add(alphaChannel);

                //Set alpha image to image.
                image.setAlphaChannel(alphaChannel);
            }

            rootPageTree.addImage(image);
        } catch (IOException e) {
            throw new PdfGenerationException("Cannot create image.", e);
        }

        return image;
    }

    /**
     * Load a font to PDF file.
     *
     * @param fontName         Name of font to load.
     * @param plainInputStream The input stream of plain font.
     * @return The loaded font.
     */
    public Font loadFont(String fontName,
                         InputStream plainInputStream) {
        return loadFont(fontName, plainInputStream, null, null, null);
    }

    /**
     * Load a font to PDF file.
     *
     * @param fontName              Name of font to load.
     * @param plainInputStream      The input stream of plain font.
     * @param boldInputStream       The input stream of bold font.
     * @param italicInputStream     The input stream of italic font.
     * @param boldItalicInputStream The input stream of boldItalic font.
     * @return The loaded font.
     */
    public Font loadFont(String fontName,
                         InputStream plainInputStream,
                         InputStream boldInputStream,
                         InputStream italicInputStream,
                         InputStream boldItalicInputStream) {
        try {
            FontVariant plainVariant = null;
            FontVariant boldVariant = null;
            FontVariant italicVariant = null;
            FontVariant boldItalicVariant = null;

            if (plainInputStream != null) {
                plainVariant = AfmLoader.load(context, plainInputStream);
            }
            if (boldInputStream != null) {
                boldVariant = AfmLoader.load(context, boldInputStream);
            }
            if (italicInputStream != null) {
                italicVariant = AfmLoader.load(context, italicInputStream);
            }
            if (boldItalicInputStream != null) {
                boldItalicVariant = AfmLoader.load(context, boldItalicInputStream);
            }

            Font font = new Font(context,
                    plainVariant,
                    boldVariant,
                    italicVariant,
                    boldItalicVariant);

            fonts.put(fontName, font);

            return font;
        } catch (IOException | NullPointerException e) {
            throw new ConfigurationException("Unable to load font Times-Roman.", e);
        }
    }

    /**
     * Get a font instance by its name.
     *
     * @param fontName The name of font to get.
     * @return The font.
     */
    public Font getFont(String fontName) {
        return fonts.get(fontName);
    }

    /**
     * Write the header of the PDF to output stream.
     *
     * @param stream The stream to write header.
     * @return The header in PDF format.
     * @throws IOException When header cannot be encoded to UTF-8.
     */
    protected int writeHeader(OutputStream stream) throws IOException {
        String pdfVersion = PDF_VERSION_1_7 + LINE_SEPARATOR;

        byte[] bytes = pdfVersion.getBytes(StandardCharsets.UTF_8);
        stream.write(bytes);

        return bytes.length;
    }

    /**
     * Get the body of the PDF.
     *
     * @param stream   The output stream where object will be rendered.
     * @param position The number of bytes written before the body in PDF.
     * @return The number of bytes written.
     * @throws PdfGenerationException When PDF cannot be generated.
     */
    protected int writeBody(OutputStream stream, int position) throws PdfGenerationException {
        int length = 0;

        int newPosition = position;
        for (PdfObject indirectObject : indirectObjects) {
            indirectPositions.add(newPosition);
            //Identifiers start at 1 and not 0.
            length += indirectObject.write(stream);
            newPosition = length + position;
        }

        return length;
    }

    /**
     * Write the cross-reference section in PDF format to stream.
     * The cross-reference section contains the list and position of each indirect object in PDF.
     *
     * @param stream The stream to write trailer to.
     * @throws IOException When trailer cannot be written to stream.
     */
    protected void writeXRef(OutputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();

        builder.append(X_REF + LINE_SEPARATOR)
                // cross-reference identifier is 0 because we always generate only one XRef.
                .append("0 ").append(indirectObjects.size() + 1).append(LINE_SEPARATOR)
                //This line is a PDF convention one.
                .append("0000000000 65535 f").append(LINE_SEPARATOR);

        for (int i = 0; i < indirectObjects.size(); i++) {
            String position = String.format("%010d", indirectPositions.get(i));
            //We generate object only once, so generation number is always 00000, and object is in use, so use 'n' tag.
            builder.append(position).append(" 00000 n").append(LINE_SEPARATOR);
        }
        String asString = builder.toString();

        stream.write(asString.getBytes());
    }

    /**
     * Write the trailer in PDF format to stream.
     * Trailer contains the identifier of root indirect object and number of indirect elements.
     *
     * @param stream The stream to write trailer to.
     * @throws IOException When trailer cannot be written to stream.
     */
    protected void writeTrailer(OutputStream stream) throws IOException {
        String asString = BEGIN_TRAILER + LINE_SEPARATOR
                //Catalog is always at 1, so let it hard-coded.
                + BEGIN_ROOT + LINE_SEPARATOR
                + SIZE + (indirectObjects.size() + 1) + LINE_SEPARATOR
                + END_DICTIONARY + LINE_SEPARATOR;

        stream.write(asString.getBytes());
    }

    /**
     * Write the startXRef in PDF format to stream.
     * The startXRef indicates the number of bytes written to PDF before the cross-reference section.
     *
     * @param stream       The stream to write start cross reference to.
     * @param xRefPosition The XRef position in PDF.
     * @throws IOException When trailer cannot be written to stream.
     */
    protected void writeStartXRef(OutputStream stream, int xRefPosition) throws IOException {
        String asString = START_X_REF + LINE_SEPARATOR
                + xRefPosition + LINE_SEPARATOR;

        stream.write(asString.getBytes());
    }

    /**
     * Deflate a byte array.
     *
     * @param data The data to deflate.
     * @return The deflated data.
     * @throws IOException When deflation cannot occur.
     */
    @SuppressWarnings("squid:S2093")
    //Because Jacoco reports coverage false positives, we cannot use try-with-resource. So do it old school.
    protected byte[] deflate(byte[] data) throws IOException {
        ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream(data.length);
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(compressedOutputStream);
        try {
            deflaterOutputStream.write(data, 0, data.length);
        } finally {
            deflaterOutputStream.close();
            compressedOutputStream.close();
        }
        return compressedOutputStream.toByteArray();
    }
}
