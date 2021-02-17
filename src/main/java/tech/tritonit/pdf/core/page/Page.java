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
import tech.tritonit.pdf.core.font.FontVariant;
import tech.tritonit.pdf.core.image.Image;
import tech.tritonit.pdf.core.path.BezierPath;
import tech.tritonit.pdf.core.path.StraightPath;
import tech.tritonit.pdf.core.styling.Color;
import tech.tritonit.pdf.core.text.Text;

import java.io.IOException;
import java.io.OutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

/**
 * Class representing a page and its content in PDF.
 *
 * @author Régis Ramillien
 */
public class Page implements PageNode {
    /**
     * The context of document.
     */
    protected final PdfContext context;

    /**
     * The content stream needed for PDF format.
     */
    protected final ContentStream contentStream;
    /**
     * The identifier of page in PDF.
     */
    protected final int id;
    /**
     * The identifier of parent in PDF.
     */
    protected final int parentId;
    /**
     * The width of the page.
     */
    protected final float width;
    /**
     * The height of the page.
     */
    protected final float height;

    /**
     * Create a page in PDF.
     *
     * @param parentId      The parent object identifier of the page.
     * @param id            The page object identifier.
     * @param contentStream The content stream of the page.
     * @param width         The width of the page.
     * @param height        The height of the page.
     */
    public Page(PdfContext context, int parentId, int id, ContentStream contentStream, float width, float height) {
        this.context = context;
        this.parentId = parentId;
        this.id = id;
        this.contentStream = contentStream;
        this.width = width;
        this.height = height;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        String asString = id + BEGIN_INDIRECT_OBJECT + LINE_SEPARATOR
                + TYPE + PAGE + LINE_SEPARATOR
                + PARENT + parentId + REFERENCE + LINE_SEPARATOR
                + CONTENTS + contentStream.getId() + REFERENCE + LINE_SEPARATOR
                + END_DICTIONARY + LINE_SEPARATOR
                + END_INDIRECT_OBJECT + LINE_SEPARATOR;

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
     * Add a text to the page.
     *
     * @param x    The X position of the text in the page.
     * @param y    The Y position of the text in the page.
     * @param size The size of the text to add.
     * @param text The text to add.
     * @return The text instance.
     */
    public Text addText(float x, float y, int size, String text) {
        Text textInstance = new Text(x, y, size, context.getDefaultFontVariant(), text);
        contentStream.addText(textInstance);
        return textInstance;
    }

    /**
     * Add a text to the page.
     *
     * @param x           The X position of the text in the page.
     * @param y           The Y position of the text in the page.
     * @param size        The size of the text to add.
     * @param fontVariant The font variant of the text.
     * @param text        The text to add.
     * @return The text instance.
     */
    public Text addText(float x, float y, int size, FontVariant fontVariant, String text) {
        Text textInstance = new Text(x, y, size, fontVariant, text);
        contentStream.addText(textInstance);
        return textInstance;
    }

    /**
     * Add a text to the page.
     *
     * @param x           The X position of the text in the page.
     * @param y           The Y position of the text in the page.
     * @param size        The size of the text to add.
     * @param fontVariant The font variant of the text.
     * @param color       The color of the text.
     * @param text        The text to add.
     * @return The text instance.
     */
    public Text addText(float x, float y, int size, FontVariant fontVariant, Color color, String text) {
        Text textInstance = new Text(x, y, size, fontVariant, color, text);
        contentStream.addText(textInstance);
        return textInstance;
    }

    /**
     * Add a text to the page.
     *
     * @param text The text to add in the page.
     */
    public void addText(Text text) {
        contentStream.addText(text);
    }

    /**
     * Add a path to page.
     *
     * @param path The straight path to add.
     */
    public void addPath(StraightPath path) {
        contentStream.addPath(path);
    }

    /**
     * Add a path to page.
     *
     * @param path The Bezier path to add.
     */
    public void addPath(BezierPath path) {
        contentStream.addPath(path);
    }

    /**
     * Add an image to the page.
     *
     * @param image The image to add.
     */
    public void addImage(Image image) {
        contentStream.addImage(image);
    }

    /**
     * Get the width of the page.
     *
     * @return The width of the page.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Get the height of the page.
     *
     * @return The height of the page.
     */
    public float getHeight() {
        return height;
    }
}
