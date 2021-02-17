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
import tech.tritonit.pdf.core.document.PdfObject;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;
import tech.tritonit.pdf.core.geometry.Point;
import tech.tritonit.pdf.core.image.Image;
import tech.tritonit.pdf.core.path.BezierPath;
import tech.tritonit.pdf.core.path.BezierPoint;
import tech.tritonit.pdf.core.path.StraightPath;
import tech.tritonit.pdf.core.styling.Color;
import tech.tritonit.pdf.core.text.Text;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

/**
 * Internal class to write data to the content-stream section of PDF.
 *
 * @author Régis Ramillien
 */
public class ContentStream implements PdfObject {
    /**
     * The context of document.
     */
    protected final PdfContext context;

    /**
     * The identifier of this object in the document.
     */
    protected final int id;
    /**
     * The list of texts in the document.
     */
    protected final List<Text> texts = new ArrayList<>();
    /**
     * The list of straight paths in the document.
     */
    protected final List<StraightPath> straightPaths = new ArrayList<>();
    /**
     * The list of Bezier paths in the document.
     */
    protected final List<BezierPath> bezierPaths = new ArrayList<>();
    /**
     * The list of images in the document.
     */
    protected final List<Image> images = new ArrayList<>();

    /**
     * Creates a content stream with the given identifier.
     *
     * @param id The identifier of content stream.
     */
    public ContentStream(PdfContext context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        String textsValues = writeTexts();
        String straightPathsValues = writeStraightPaths();
        String bezierPathsValues = writeBezierPaths();
        String imagesValues = writeImages();

        String asString = id + BEGIN_INDIRECT_OBJECT + LINE_SEPARATOR
                + LENGTH + (textsValues.length() + straightPathsValues.length() + bezierPathsValues.length() + imagesValues.length()) + LINE_SEPARATOR
                + END_DICTIONARY + LINE_SEPARATOR
                + BEGIN_STREAM + LINE_SEPARATOR
                + textsValues
                + straightPathsValues
                + bezierPathsValues
                + imagesValues
                + END_STREAM + LINE_SEPARATOR
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
     * Add text to content stream.
     *
     * @param text The text to add.
     */
    public void addText(Text text) {
        texts.add(text);
    }

    /**
     * Add straight path to content stream.
     *
     * @param path The path to add.
     */
    public void addPath(StraightPath path) {
        straightPaths.add(path);
    }

    /**
     * Add Bezier path to content stream.
     *
     * @param path The path to add.
     */
    public void addPath(BezierPath path) {
        bezierPaths.add(path);
    }

    /**
     * Add image to content stream.
     *
     * @param image The image to add.
     */
    public void addImage(Image image) {
        images.add(image);
    }

    /**
     * Create a String representing the images in PDF format.
     *
     * @return A String representing the images part of the PDF.
     */
    private String writeImages() {
        StringBuilder builder = new StringBuilder();
        for (Image image : images) {
            int x = context.getUnit().toNative(image.getX());
            int y = context.getUnit().toNative(image.getY());
            int width = context.getUnit().toNative(image.getWidth());
            int height = context.getUnit().toNative(image.getHeight());
            builder.append(START_IMAGE).append(LINE_SEPARATOR)
                    .append(width).append(SPACE)
                    .append(image.getSkewY()).append(SPACE)
                    .append(image.getSkewX()).append(SPACE)
                    .append(height).append(SPACE)
                    .append(x).append(SPACE)
                    .append(y).append(CURRENT_MATRIX).append(LINE_SEPARATOR)
                    .append(START_COMMAND).append(image.getId()).append(DO).append(LINE_SEPARATOR)
                    .append(END_IMAGE).append(LINE_SEPARATOR);
        }
        return builder.toString();
    }

    /**
     * Create a String representing the straight paths in PDF format.
     *
     * @return A String representing the straight paths part of the PDF.
     */
    private String writeStraightPaths() {
        StringBuilder builder = new StringBuilder();
        for (StraightPath path : straightPaths) {
            startWritingPath(builder, path.getLineWidth(), path.getStrokeColor(), path.getFillColor(), path.getStartPoint());

            for (Point point : path.getPoints()) {
                int x = context.getUnit().toNative(point.getX());
                int y = context.getUnit().toNative(point.getY());
                builder.append(x).append(SPACE).append(y).append(LINE_TO);
            }

            writePathFillAndStroke(builder, path.isFilled(), path.isStroked(), path.isClosed());
        }
        return builder.toString();
    }

    /**
     * Create a String representing the Bezier paths in PDF format.
     *
     * @return A String representing the Bezier paths part of the PDF.
     */
    private String writeBezierPaths() {
        StringBuilder builder = new StringBuilder();
        for (BezierPath path : bezierPaths) {
            startWritingPath(builder, path.getLineWidth(), path.getStrokeColor(), path.getFillColor(), path.getStartPoint());

            for (BezierPoint point : path.getBezierPoints()) {
                int x = context.getUnit().toNative(point.getX());
                int y = context.getUnit().toNative(point.getY());
                int x1 = context.getUnit().toNative(point.getX1());
                int y1 = context.getUnit().toNative(point.getY1());
                int x2 = context.getUnit().toNative(point.getX2());
                int y2 = context.getUnit().toNative(point.getY2());
                builder.append(x1).append(SPACE).append(y1).append(SPACE)
                        .append(x2).append(SPACE).append(y2).append(SPACE)
                        .append(x).append(SPACE).append(y)
                        .append(CURVE_TO);
            }

            writePathFillAndStroke(builder, path.isFilled(), path.isStroked(), path.isClosed());
        }
        return builder.toString();
    }

    /**
     * Create a String representing the texts in PDF format.
     *
     * @return A String representing the texts part of the PDF.
     */
    private String writeTexts() {
        StringBuilder builder = new StringBuilder();
        for (Text text : texts) {
            int nativeX = context.getUnit().toNative(text.getX());
            int nativeY = context.getUnit().toNative(text.getY());
            builder.append(BEGIN_TEXT).append(LINE_SEPARATOR) //Begin text
                    .append(START_COMMAND).append(text.getFontVariant().getName()).append(SPACE).append(text.getSize()).append(TEXT_FONT).append(LINE_SEPARATOR) //Use font named "F1"
                    .append(nativeX).append(SPACE).append(nativeY).append(NEXT_LINE).append(LINE_SEPARATOR) //Start text as 0, 0
                    .append(text.getColor().getRed() / 255.0f).append(SPACE)
                    .append(text.getColor().getGreen() / 255.0f).append(SPACE)
                    .append(text.getColor().getBlue() / 255.0f).append(SPACE)
                    .append(RGB_NON_STROKING_COLOR_AND_SPACE).append(LINE_SEPARATOR)
                    //'(' and ')' are interpreted by PDF readers, so we must escape them.
                    .append(BEGIN_STRING).append(text.getValue().replace("(", "\\(").replace(")", "\\)")).append(END_STRING).append(SHOW_TEXT).append(LINE_SEPARATOR)
                    .append(END_TEXT).append(LINE_SEPARATOR); //End text

            if (text.isUnderlined()) {
                float underlineY = text.getY() + text.getFontVariant().getUnderlinePosition(text.getSize());
                float textWidth = text.getFontVariant().getWidth(text.getSize(), text.getValue());
                addPath(new StraightPath(text.getFontVariant().getUnderlineThickness(text.getSize()), text.getUnderlineColor(),
                        new Point(text.getX(), underlineY),
                        new Point(text.getX() + textWidth, underlineY))
                );
            }
        }
        return builder.toString();
    }

    /**
     * Write fill and/or stroke of a path in PDF format.
     *
     * @param builder The String builder to append PDF commands to.
     * @param filled  true if path is filled, false otherwise.
     * @param stroked true if path is stroked, false otherwise.
     * @param closed  true if path is closed, false otherwise.
     */
    private void writePathFillAndStroke(StringBuilder builder, boolean filled, boolean stroked, boolean closed) {
        if (filled && stroked) {
            builder.append(FILL_AND_STROKE_PATH);
        } else if (filled) {
            builder.append(FILL_PATH);
        } else if (stroked) {
            builder.append(closed ? CLOSE_AND_STROKE_PATH : STROKE_PATH);
        }
        builder.append(LINE_SEPARATOR);
    }

    /**
     * Start writing path in PDF format.
     *
     * @param builder     The String builder to append PDF commands to.
     * @param lineWidth   The width of the line of the path.
     * @param strokeColor The stroke color of the path.
     * @param fillColor   THe fill color of the path.
     * @param startPoint  The start point of the path.
     */
    private void startWritingPath(StringBuilder builder, float lineWidth, Color strokeColor, Color fillColor, Point startPoint) {
        int x = context.getUnit().toNative(startPoint.getX());
        int y = context.getUnit().toNative(startPoint.getY());
        builder.append(lineWidth).append(LINE_WIDTH)

                .append(((float) strokeColor.getRed()) / 255.0f).append(SPACE)
                .append(((float) strokeColor.getGreen()) / 255.0f).append(SPACE)
                .append(((float) strokeColor.getBlue()) / 255.0f).append(SPACE)
                .append(RGB_STROKING_COLOR_AND_SPACE)

                .append(((float) fillColor.getRed()) / 255.0f).append(SPACE)
                .append(((float) fillColor.getGreen()) / 255.0f).append(SPACE)
                .append(((float) fillColor.getBlue()) / 255.0f).append(SPACE)
                .append(RGB_NON_STROKING_COLOR_AND_SPACE)

                .append(x).append(SPACE).append(y).append(MOVE_TO);
    }
}
