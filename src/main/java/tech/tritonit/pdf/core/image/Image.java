/*
 * Copyright 2016 web4enterprise.
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
package tech.tritonit.pdf.core.image;

import tech.tritonit.pdf.core.document.PdfContext;
import tech.tritonit.pdf.core.document.PdfObject;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;

import java.io.IOException;
import java.io.OutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

/**
 * Class representing an image that must be embedded and rendered into a PDF document.
 * The same class is used to both represent the image data and image positioning (position, size, skew, etc).
 * The image data must be added only once to PDF to save space while it can be positioned many times without adding its data.
 *
 * @author Régis Ramillien
 */
public class Image implements PdfObject {
    /**
     * The context of document.
     */
    protected final PdfContext context;

    /**
     * The identifier of image in PDF.
     */
    protected final int id;
    /**
     * The X position of the rendered image in page.
     */
    protected float x;
    /**
     * The Y position of the rendered image in page.
     */
    protected float y;
    /**
     * The width of the definition of the image in PDF.
     */
    protected int originalWidth;
    /**
     * The height of the definition of the image in PDF.
     */
    protected int originalHeight;

    /**
     * The width of the rendered image.
     */
    protected float width;
    /**
     * The height of the rendered image.
     */
    protected float height;
    /**
     * The skew on X axis of the rendered image.
     */
    protected int skewX;
    /**
     * The skew on Y axis of the rendered image.
     */
    protected int skewY;
    /**
     * The pixels of the image.
     */
    protected byte[] pixels;
    /**
     * The pixels of the image.
     */
    protected AlphaChannel alphaChannel;
    /**
     * Define if image have an alpha channel.
     */
    protected boolean hasAlphaChannel = false;

    /**
     * Creates an image with the given id.
     *
     * @param id The identifier in image.
     */
    public Image(PdfContext context, int id) {
        this.context = context;
        this.id = id;
    }

    /**
     * Get the X position in page of the rendered image.
     *
     * @return The X position.
     */
    public float getX() {
        return x;
    }

    /**
     * Set the X position in page of the rendered image.
     *
     * @param x The X position.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Get the Y position in page of the rendered image.
     *
     * @return The Y position.
     */
    public float getY() {
        return y;
    }

    /**
     * Set the Y position in page of the rendered image.
     *
     * @param y The Y position.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Get the width of the definition of the image in PDF.
     *
     * @return The width.
     */
    public int getOriginalWidth() {
        return originalWidth;
    }

    /**
     * Set the width of the definition of the image in PDF.
     *
     * @param originalWidth the width.
     */
    public void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    /**
     * Get the height of the definition of the image in PDF.
     *
     * @return The height.
     */
    public int getOriginalHeight() {
        return originalHeight;
    }

    /**
     * Set the height of the definition of the image in PDF.
     *
     * @param originalHeight the height.
     */
    public void setOriginalHeight(int originalHeight) {
        this.originalHeight = originalHeight;
    }

    /**
     * Get the width of the rendered image.
     *
     * @return The width of the rendered image.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Set the width of the rendered image.
     *
     * @param width The width of the rendered image.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Get the height of the rendered image.
     *
     * @return The height of the rendered image.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set the height of the rendered image.
     *
     * @param height The height of the rendered image.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Get the horizontal skew of the rendered image.
     *
     * @return The horizontal skew of the rendered image.
     */
    public int getSkewX() {
        return skewX;
    }

    /**
     * Set the horizontal skew of the rendered image.
     *
     * @param skewX The horizontal skew of the rendered image.
     */
    public void setSkewX(int skewX) {
        this.skewX = skewX;
    }

    /**
     * Get the vertical skew of the rendered image.
     *
     * @return The vertical skew of the rendered image.
     */
    public int getSkewY() {
        return skewY;
    }

    /**
     * Set the vertical skew of the rendered image.
     *
     * @param skewY The vertical skew of the rendered image.
     */
    public void setSkewY(int skewY) {
        this.skewY = skewY;
    }

    /**
     * Get the pixels of the image.
     * The pixels are represented as 3 bytes per pixel (RGB).
     *
     * @return The pixels of the image.
     */
    public byte[] getPixels() {
        return pixels;
    }

    /**
     * Set the pixels of the image.
     * The pixels are represented as 3 bytes per pixel (RGB).
     *
     * @param pixels The pixels of the image.
     */
    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }

    /**
     * Get the alpha channel of the image.
     *
     * @return The alpha channel of the image.
     */
    public AlphaChannel getAlphaChannel() {
        return alphaChannel;
    }

    /**
     * Set the alpha channel of the image.
     *
     * @param alphaChannel The alpha channel of the image.
     */
    public void setAlphaChannel(AlphaChannel alphaChannel) {
        this.alphaChannel = alphaChannel;
        this.hasAlphaChannel = alphaChannel != null;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        StringBuilder builder = new StringBuilder();
        //Write image header.
        builder.append(id).append(BEGIN_INDIRECT_OBJECT).append(LINE_SEPARATOR
               ).append(LENGTH).append(pixels.length).append(LINE_SEPARATOR
               ).append(TYPE).append(X_OBJECT).append(LINE_SEPARATOR
               ).append(SUB_TYPE).append(IMAGE).append(LINE_SEPARATOR
               ).append(FILTER).append(FLATE_DECODE).append(LINE_SEPARATOR
               ).append(BITS_PER_COMPONENT).append(EIGHT).append(LINE_SEPARATOR
               ).append(WIDTH).append(originalWidth).append(LINE_SEPARATOR
               ).append(HEIGHT).append(originalHeight).append(LINE_SEPARATOR
               ).append(COLOR_SPACE).append(DEVICE_RGB).append(LINE_SEPARATOR);

        if (this.hasAlphaChannel) {
            builder.append(SOFT_MASK).append(alphaChannel.id).append(REFERENCE);
        }

        builder.append(END_DICTIONARY).append(LINE_SEPARATOR
               ).append(BEGIN_STREAM).append(LINE_SEPARATOR);

        String asString = builder.toString();
        int length = asString.length();

        try {
            stream.write(asString.getBytes());
            stream.write(pixels);

            length += pixels.length;

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

    /**
     * Clone the image parameters but not its content.
     *
     * @return The image filled-in with parameters.
     */
    public Image cloneReference() {
        Image clone = new Image(context, this.id);

        clone.x = this.x;
        clone.y = this.y;
        clone.width = this.width;
        clone.height = this.height;
        clone.skewX = this.skewX;
        clone.skewY = this.skewY;
        clone.alphaChannel = this.alphaChannel;

        return clone;
    }
}
