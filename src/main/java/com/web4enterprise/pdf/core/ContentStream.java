package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContentStream implements PDFObject {
	protected int id;
	
	protected List<Text> texts = new ArrayList<>();
	protected List<StraightPath> lines = new ArrayList<>();
	protected List<BezierPath> bezierLines = new ArrayList<>();
	protected List<Image> images = new ArrayList<>();
	
	public ContentStream(int id) {
		this.id = id;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String textsValues = "";
		for(Text text : texts) {
			textsValues += "  BT" + LINE_SEPARATOR //Begin text
			+ "    /" + text.getFontVariant().getName() + " " + text.getSize() + " Tf" + LINE_SEPARATOR //Use font named "F1"
			+ "    " + text.getX() + " " + text.getY() + " Td" + LINE_SEPARATOR //Start text as 0, 0
			+ "    " + text.getColor().getRed() / 255.0f 
					+ " " + text.getColor().getGreen() / 255.0f
					+ " " + text.getColor().getBlue() / 255.0f
					+ " rg" + LINE_SEPARATOR
			+ "    (" + text.getValue() + ") Tj" + LINE_SEPARATOR
			+ "  ET" + LINE_SEPARATOR; //End text
		}
		
		String linesValues = "";		
		for(StraightPath path : lines) {
			linesValues += path.getLineWidth() + " w ";
			
			linesValues += (((float) path.getStrokeColor().getRed()) / 255.0f) + " "
					+ (((float) path.getStrokeColor().getGreen()) / 255.0f) + " "
					+ (((float) path.getStrokeColor().getBlue()) / 255.0f) + " "
					+ "RG ";
			
			linesValues += (((float) path.getFillColor().getRed()) / 255.0f) / 255.0f + " "
					+ (((float) path.getFillColor().getGreen()) / 255.0f) + " "
					+ (((float) path.getFillColor().getBlue()) / 255.0f) + " "
					+ "rg ";
			
			linesValues += path.getStartPoint().getX() + " " + path.getStartPoint().getY() + " m ";
			for(Point point : path.getPoints()) {
				linesValues += point.getX() + " " + point.getY();
				linesValues += " l ";
			}
			if(path.isFilled() && path.isStroked()) {
				linesValues += "B";
			} else if(path.isFilled()) {
				linesValues += "f";
			} else if(path.isStroked()) {
				linesValues += path.isClosed()?"s":"S";				
			}
			linesValues += LINE_SEPARATOR;
		}
		
		String bezierLinesValues = "";		
		for(BezierPath path : bezierLines) {
			bezierLinesValues += path.getLineWidth() + " w ";
			
			bezierLinesValues += (((float) path.getStrokeColor().getRed()) / 255.0f) + " "
					+ (((float) path.getStrokeColor().getGreen()) / 255.0f) + " "
					+ (((float) path.getStrokeColor().getBlue()) / 255.0f) + " "
					+ "RG ";
			
			bezierLinesValues += (((float) path.getFillColor().getRed()) / 255.0f) + " "
					+ (((float) path.getFillColor().getGreen()) / 255.0f) + " "
					+ (((float) path.getFillColor().getBlue()) / 255.0f) + " "
					+ "rg ";
			
			bezierLinesValues += path.getStartPoint().getX() + " " + path.getStartPoint().getY() + " m ";
			for(BezierPoint point : path.getBezierPoints()) {
				bezierLinesValues += point.getX1() + " " + point.getY1()
						 + " " + point.getX2() + " " + point.getY2()
						 + " " + point.getX() + " " + point.getY();

					bezierLinesValues += " c ";
			}
			if(path.isFilled() && path.isStroked()) {
				bezierLinesValues += "B";
			} else if(path.isFilled()) {
				bezierLinesValues += "f";
			} else if(path.isStroked()) {
				bezierLinesValues += path.isClosed()?"s":"S";				
			}
			bezierLinesValues += LINE_SEPARATOR;
		}
		
		String imagesValues = "";		
		for(Image image : images) {
			imagesValues += "q" + LINE_SEPARATOR;
			imagesValues += image.getWidth() + " " + image.getSkewY() + " " + image.getSkewX() + " " + image.getHeight() + " " 
					+ image.getX() + " " + image.getY() + " cm" + LINE_SEPARATOR;
			imagesValues += "/image" + image.getId() + " Do" + LINE_SEPARATOR;
			imagesValues += "Q" + LINE_SEPARATOR;
		}

		String asString = id + " 0 obj" + LINE_SEPARATOR
				+ "<< /Length " + textsValues.length() + " >>" + LINE_SEPARATOR
				+ "stream" + LINE_SEPARATOR
				+ textsValues
				+ linesValues
				+ bezierLinesValues
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

	@Override
	public int getId() {
		return id;
	}
	
	public void addText(Text text) {
		texts.add(text);
	}
	
	public void addPath(StraightPath path) {
		lines.add(path);
	}
	
	public void addPath(BezierPath path) {
		bezierLines.add(path);
	}
	
	public void addImage(Image image) {
		images.add(image);
	}
}
