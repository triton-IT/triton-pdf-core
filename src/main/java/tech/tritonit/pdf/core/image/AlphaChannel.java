package tech.tritonit.pdf.core.image;

import tech.tritonit.pdf.core.document.PdfContext;
import tech.tritonit.pdf.core.document.PdfObject;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;

import java.io.IOException;
import java.io.OutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

public class AlphaChannel implements PdfObject {
    /**
     * The context of document.
     */
    protected final PdfContext context;

    /**
     * The identifier of image in PDF.
     */
    protected final int id;

    /**
     * The width of the definition of the image in PDF.
     */
    protected int width;
    /**
     * The height of the definition of the image in PDF.
     */
    protected int height;

    /**
     * The pixels of the image.
     */
    protected byte[] alphaValues;

    /**
     * Creates an image with the given id.
     *
     * @param id The identifier in image.
     */
    public AlphaChannel(PdfContext context,  int id) {
        this.context = context;
        this.id = id;
    }

    /**
     * Get the width of the definition of the image in PDF.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }
    /**
     * Set the width of the definition of the image in PDF.
     *
     * @param width the width.
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Get the height of the definition of the image in PDF.
     *
     * @return The height.
     */
    public int getHeight() {
        return height;
    }
    /**
     * Set the height of the definition of the image in PDF.
     *
     * @param height the height.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * Get the pixels of the image.
     * The pixels are represented as 3 bytes per pixel (RGB).
     *
     * @return The pixels of the image.
     */
    public byte[] getAlphaValues() {
        return alphaValues;
    }
    /**
     * Set the pixels of the image.
     * The pixels are represented as 3 bytes per pixel (RGB).
     *
     * @param alphaValues The pixels of the image.
     */
    public void setAlphaValues(byte[] alphaValues) {
        this.alphaValues = alphaValues;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        //Write image header.
        String asString = id + BEGIN_INDIRECT_OBJECT + LINE_SEPARATOR
                + LENGTH + alphaValues.length + LINE_SEPARATOR
                + TYPE + X_OBJECT + LINE_SEPARATOR
                + SUB_TYPE + IMAGE + LINE_SEPARATOR
                + FILTER + FLATE_DECODE + LINE_SEPARATOR
                + BITS_PER_COMPONENT + EIGHT + LINE_SEPARATOR
                + WIDTH + width + LINE_SEPARATOR
                + HEIGHT + height + LINE_SEPARATOR
                + COLOR_SPACE + DEVICE_GRAY + LINE_SEPARATOR
                + END_DICTIONARY + LINE_SEPARATOR
                + BEGIN_STREAM + LINE_SEPARATOR;

        int length = asString.length();

        try {
            stream.write(asString.getBytes());
            stream.write(alphaValues);

            length += alphaValues.length;

            //Write image footer.
            asString = LINE_SEPARATOR
                    + END_STREAM + LINE_SEPARATOR
                    + END_INDIRECT_OBJECT + LINE_SEPARATOR;

            length += asString.length();

            stream.write(asString.getBytes());
        } catch (IOException e) {
            throw new PdfGenerationException("Cannot write root page tree to output stream", e);
        }

        return length;
    }

    @Override
    public int getId() {
        return id;
    }
}
