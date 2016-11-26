package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
	 * The list of texts in the document.
	 */
	protected List<Text> texts = new ArrayList<>();
	/**
	 * The list of straight paths in the document.
	 */
	protected List<StraightPath> straightPaths = new ArrayList<>();
	/**
	 * The list of Bezier paths in the document.
	 */
	protected List<BezierPath> bezierPaths = new ArrayList<>();
	/**
	 * The list of images in the document.
	 */
	protected List<Image> images = new ArrayList<>();
	
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
		String textsValues = writeTexts();
		String straightPathsValues = writeStraightPaths();
		String bezierPathsValues = writeBezierPaths();
		String imagesValues = writeImages();

		String asString = id + " 0 obj <<" + LINE_SEPARATOR
				+ "/Length " + (textsValues.length() + straightPathsValues.length() + bezierPathsValues.length() + imagesValues.length()) + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "stream" + LINE_SEPARATOR
				+ textsValues
				+ straightPathsValues
				+ bezierPathsValues
				+ imagesValues
				+ "endstream" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
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
	private String writeImages() {
		StringBuilder builder = new StringBuilder();
		for(Image image : images) {
			builder.append("q").append(LINE_SEPARATOR)
			.append(image.getWidth()).append(" ")
			.append(image.getSkewY()).append(" ")
			.append(image.getSkewX()).append(" ")
			.append(image.getHeight()).append(" ")
			.append(image.getX()).append(" ")
			.append(image.getY()).append(" cm").append(LINE_SEPARATOR)
			.append("/image").append(image.getId()).append(" Do").append(LINE_SEPARATOR)
			.append("Q").append(LINE_SEPARATOR);
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
		for(StraightPath path : straightPaths) {
			builder.append(path.getLineWidth()).append(" w ")
			
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
				builder.append(point.getX()).append(" ").append(point.getY()).append(" l ");
			}
			if(path.isFilled() && path.isStroked()) {
				builder.append("B");
			} else if(path.isFilled()) {
				builder.append("f");
			} else if(path.isStroked()) {
				builder.append(path.isClosed()?"s":"S");
			}
			builder.append(LINE_SEPARATOR);
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
		for(BezierPath path : bezierPaths) {
			builder.append(path.getLineWidth()).append(" w ")
			
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
				builder.append(point.getX1()).append(" ").append(point.getY1()).append(" ")
				.append(point.getX2()).append(" ").append(point.getY2()).append(" ")
				.append(point.getX()).append(" ").append(point.getY())
				.append(" c ");
			}
			if(path.isFilled() && path.isStroked()) {
				builder.append("B");
			} else if(path.isFilled()) {
				builder.append("f");
			} else if(path.isStroked()) {
				builder.append(path.isClosed()?"s":"S");				
			}
			builder.append(LINE_SEPARATOR);
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
		for(Text text : texts) {
			builder.append("BT").append(LINE_SEPARATOR) //Begin text
			.append("/").append(text.getFontVariant().getName()).append(" ").append(text.getSize()).append(" Tf").append(LINE_SEPARATOR) //Use font named "F1"
			.append(text.getX()).append(" ").append(text.getY()).append(" Td").append(LINE_SEPARATOR) //Start text as 0, 0
			.append(text.getColor().getRed() / 255.0f).append(" ")
			.append(text.getColor().getGreen() / 255.0f).append(" ")
			.append(text.getColor().getBlue() / 255.0f).append(" ")
			.append("rg").append(LINE_SEPARATOR)
			//( and ) are interpreted by PDF readers, so we must escape them.
			.append("(").append(text.getValue().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")).append(") Tj").append(LINE_SEPARATOR)
			.append("ET").append(LINE_SEPARATOR); //End text
		}
		return builder.toString();
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
}
