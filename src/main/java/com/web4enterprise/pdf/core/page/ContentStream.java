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
package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.geometry.Point;
import com.web4enterprise.pdf.core.image.Image;
import com.web4enterprise.pdf.core.path.BezierPath;
import com.web4enterprise.pdf.core.path.BezierPoint;
import com.web4enterprise.pdf.core.path.StraightPath;
import com.web4enterprise.pdf.core.text.Text;

/**
 * Internal class to write data to the content-stream section of PDF.
 * 
 * @author Régis Ramillien
 */
public class ContentStream implements PdfObject {
	/**
	 * The identifier of this object in the document.
	 */
	protected int id;
	/**
	 * The stream to output to PDF.
	 */
	StringBuilder streamBuilder = new StringBuilder();
	
	/**
	 * Creates a content stream with the given identifier.
	 * 
	 * @param id The identifier of content stream.
	 */
	public ContentStream(int id) {
		this.id = id;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {		
		streamBuilder.insert(0, id + " 0 obj <<" + LINE_SEPARATOR
				+ "/Length " + streamBuilder.length() + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "stream" + LINE_SEPARATOR);

		streamBuilder.append("endstream" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR);
		
		String asString = streamBuilder.toString();
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}
		
		return asString.length();
	}

	/**
	 * Create a String representing the images in PDF format.
	 * 
	 * @return A String representing the images part of the PDF.
	 */
	protected void streamImage(Image image) {
		streamBuilder.append("q").append(LINE_SEPARATOR)
			.append(image.getWidth()).append(" ")
			.append(image.getSkewY()).append(" ")
			.append(image.getSkewX()).append(" ")
			.append(image.getHeight()).append(" ")
			.append(image.getX()).append(" ")
			.append(image.getY()).append(" cm").append(LINE_SEPARATOR)
			.append("/image").append(image.getId()).append(" Do").append(LINE_SEPARATOR)
			.append("Q").append(LINE_SEPARATOR);
	}

	/**
	 * Create a String representing the straight paths in PDF format.
	 * 
	 * @return A String representing the straight paths part of the PDF.
	 */
	protected void streamStraightPath(StraightPath path) {
		streamBuilder.append(path.getLineWidth()).append(" w ")		
			.append(((float) path.getStrokeColor().getRed()) / 255.0f).append(" ")
			.append(((float) path.getStrokeColor().getGreen()) / 255.0f).append(" ")
			.append(((float) path.getStrokeColor().getBlue()) / 255.0f).append(" ")
			.append("RG ")
			
			.append(((float) path.getFillColor().getRed()) / 255.0f).append(" ")
			.append(((float) path.getFillColor().getGreen()) / 255.0f).append(" ")
			.append(((float) path.getFillColor().getBlue()) / 255.0f).append(" ")
			.append("rg ")
		
			.append(path.getStartPoint().getX()).append(" ").append(path.getStartPoint().getY()).append(" m ");
		for(Point point : path.getPoints()) {
			streamBuilder.append(point.getX()).append(" ").append(point.getY()).append(" l ");
		}
		if(path.isFilled() && path.isStroked()) {
			streamBuilder.append("B");
		} else if(path.isFilled()) {
			streamBuilder.append("f");
		} else if(path.isStroked()) {
			streamBuilder.append(path.isClosed()?"s":"S");
		}
		streamBuilder.append(LINE_SEPARATOR);
	}

	/**
	 * Create a String representing the Bezier paths in PDF format.
	 * 
	 * @return A String representing the Bezier paths part of the PDF.
	 */
	protected void streamBezierPath(BezierPath path) {
		streamBuilder.append(path.getLineWidth()).append(" w ")			
			.append(((float) path.getStrokeColor().getRed()) / 255.0f).append(" ")
			.append(((float) path.getStrokeColor().getGreen()) / 255.0f).append(" ")
			.append(((float) path.getStrokeColor().getBlue()) / 255.0f).append(" ")
			.append("RG ")
			
			.append(((float) path.getFillColor().getRed()) / 255.0f).append(" ")
			.append(((float) path.getFillColor().getGreen()) / 255.0f).append(" ")
			.append(((float) path.getFillColor().getBlue()) / 255.0f).append(" ")
			.append("rg ")
			
			.append(path.getStartPoint().getX()).append(" ").append(path.getStartPoint().getY()).append(" m ");
		for(BezierPoint point : path.getBezierPoints()) {
			streamBuilder.append(point.getX1()).append(" ").append(point.getY1()).append(" ")
				.append(point.getX2()).append(" ").append(point.getY2()).append(" ")
				.append(point.getX()).append(" ").append(point.getY())
				.append(" c ");
		}
		if(path.isFilled() && path.isStroked()) {
			streamBuilder.append("B");
		} else if(path.isFilled()) {
			streamBuilder.append("f");
		} else if(path.isStroked()) {
			streamBuilder.append(path.isClosed()?"s":"S");				
		}
		streamBuilder.append(LINE_SEPARATOR);
	}

	/**
	 * Create a String representing the texts in PDF format.
	 * 
	 * @return A String representing the texts part of the PDF.
	 */
	protected void streamText(Text text) {
		streamBuilder.append("BT").append(LINE_SEPARATOR) //Begin text
			.append("/").append(text.getFontVariant().getName()).append(" ").append(text.getSize()).append(" Tf").append(LINE_SEPARATOR) //Use font named "F1"
			.append(text.getX()).append(" ").append(text.getY()).append(" Td").append(LINE_SEPARATOR) //Start text as 0, 0
			.append(text.getColor().getRed() / 255.0f).append(" ")
			.append(text.getColor().getGreen() / 255.0f).append(" ")
			.append(text.getColor().getBlue() / 255.0f).append(" ")
			.append("rg").append(LINE_SEPARATOR)
			//'(' and ')' are interpreted by PDF readers, so we must escape them.
			.append("(").append(text.getValue().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")).append(") Tj").append(LINE_SEPARATOR)
			.append("ET").append(LINE_SEPARATOR); //End text
		
		if(text.isUnderlined()) {
			int underlineY = (int) (text.getY() + text.getFontVariant().getUnderlinePosition(text.getSize()));
			addPath(new StraightPath(text.getFontVariant().getUnderlineThickness(text.getSize()), text.getUnderlineColor(), 
					new Point(text.getX(), underlineY), 
					new Point(
							text.getX() + text.getFontVariant().getWidth(text.getSize(), text.getValue()), 
							underlineY)));
		}
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
		streamText(text);
	}
	
	/**
	 * Add straight path to content stream.
	 * 
	 * @param path The path to add.
	 */
	public void addPath(StraightPath path) {
		streamStraightPath(path);
	}
	
	/**
	 * Add Bezier path to content stream.
	 * 
	 * @param path The path to add.
	 */
	public void addPath(BezierPath path) {
		streamBezierPath(path);
	}
	
	/**
	 * Add image to content stream.
	 * 
	 * @param image The image to add.
	 */	
	public void addImage(Image image) {
		streamImage(image);
	}
}
