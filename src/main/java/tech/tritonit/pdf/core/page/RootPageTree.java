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
package tech.tritonit.pdf.core.page;

import tech.tritonit.pdf.core.document.PdfContext;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;
import tech.tritonit.pdf.core.font.Font;
import tech.tritonit.pdf.core.font.FontVariant;
import tech.tritonit.pdf.core.font.FontsVariant;
import tech.tritonit.pdf.core.image.Image;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

/**
 * Represent the root page of a PDF document.
 *
 * @author Régis Ramillien
 */
public class RootPageTree implements PageNode {
    /**
     * The context of document.
     */
    protected final PdfContext context;
    /**
     * The pages in the PDF.
     */
    protected final List<PageNode> pageNodes = new ArrayList<>();
    /**
     * The definition of images in the PDF.
     */
    protected final List<Image> images = new ArrayList<>();
    /**
     * List of font declared in the  PDF.
     */
    protected final List<Font> fonts = new ArrayList<>();
    /**
     * The identifier of the object.
     */
    protected final int id;

    /**
     * Construct a RootPageTree with the given identifier.
     *
     * @param id The identifier this object.
     */
    public RootPageTree(PdfContext context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        StringBuilder builder = new StringBuilder();

        builder.append(id).append(BEGIN_INDIRECT_OBJECT).append(LINE_SEPARATOR)
                .append(TYPE).append(PAGES).append(LINE_SEPARATOR)
                .append(BEGIN_KIDS).append(LINE_SEPARATOR);

        for (PageNode pageNode : pageNodes) {
            builder.append(pageNode.getId()).append(REFERENCE).append(LINE_SEPARATOR);
        }

        builder.append(END_ARRAY).append(LINE_SEPARATOR)
                .append(COUNT).append(pageNodes.size()).append(LINE_SEPARATOR)
                .append(BEGIN_RESOURCE).append(LINE_SEPARATOR)
                .append(BEGIN_FONT).append(LINE_SEPARATOR);

        for (Font font:
             fonts) {
            FontVariant plain = font.getVariant(FontsVariant.PLAIN);
            if(plain != null) {
                builder.append(embedFontVariant(plain.getName()));
            }

            FontVariant bold = font.getVariant(FontsVariant.BOLD);
            if(bold != null) {
                builder.append(embedFontVariant(bold.getName()));
            }

            FontVariant italic = font.getVariant(FontsVariant.ITALIC);
            if(italic != null) {
                builder.append(embedFontVariant(italic.getName()));
            }

            FontVariant boldItalic = font.getVariant(FontsVariant.BOLD_ITALIC);
            if(boldItalic != null) {
                builder.append(embedFontVariant(boldItalic.getName()));
            }
        }

        builder.append(END_DICTIONARY).append(LINE_SEPARATOR);
        if (!images.isEmpty()) {
            builder.append(START_X_OBJECT).append(LINE_SEPARATOR);
            for (Image image : images) {
                builder.append(START_COMMAND).append(image.getId()).append(SPACE).append(image.getId()).append(REFERENCE).append(LINE_SEPARATOR);
            }
            builder.append(END_DICTIONARY).append(LINE_SEPARATOR);
        }
        builder.append(END_DICTIONARY).append(LINE_SEPARATOR)
                .append(END_DICTIONARY).append(LINE_SEPARATOR)
                .append(END_INDIRECT_OBJECT).append(LINE_SEPARATOR);

        String asString = builder.toString();
        try {
            stream.write(asString.getBytes());
        } catch (IOException e) {
            throw new PdfGenerationException("Cannot write to output stream", e);
        }

        return asString.length();
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * Attach a page node to this object.
     *
     * @param pageNode The page node to attach.
     */
    public void addPageNode(PageNode pageNode) {
        pageNodes.add(pageNode);
    }

    /**
     * Attach a page node to this object.
     *
     * @param image The image to attach.
     */
    public void addImage(Image image) {
        images.add(image);
    }

    /**
     * Declare font to PDF.
     * @param font The font to declare to PDF document.
     */
    public void declareFont(Font font) {
        fonts.add(font);
    }

    /**
     * Write the definition item of a font variant in PDF format to a String.
     *
     * @param fontVariant The font variant to write as String.
     * @return A String representing a font definition item in PDF format.
     */
    private String embedFontVariant(String fontVariant) {
        return NAME_OBJECT_PREFIX + fontVariant + BEGIN_DICTIONARY + LINE_SEPARATOR
                + TYPE + FONT + LINE_SEPARATOR
                + SUB_TYPE + TYPE1 + LINE_SEPARATOR
                + BASE_FONT + NAME_OBJECT_PREFIX + fontVariant + LINE_SEPARATOR
                + END_DICTIONARY + LINE_SEPARATOR;
    }
}
