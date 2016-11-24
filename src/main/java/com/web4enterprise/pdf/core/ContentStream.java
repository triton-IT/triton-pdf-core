package com.web4enterprise.pdf.core;

import static com.web4enterprise.pdf.core.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

public class ContentStream implements PdfObject {
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
		String textsValues = writeTexts();
		String linesValues = writeStraightLines();
		String bezierLinesValues = writeBezierLines();
		String imagesValues = writeImages();

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

	private String writeBezierLines() {
		StringBuilder builder = new StringBuilder();
		for(BezierPath path : bezierLines) {
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
				builder.append(point.getX1()).append(" ").append(point.getY1())
				.append(" ").append(point.getX2()).append(" ").append(point.getY2())
				.append(" ").append(point.getX()).append(" ").append(point.getY())
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

	private String writeStraightLines() {
		StringBuilder builder = new StringBuilder();
		for(StraightPath path : lines) {
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

	private String writeTexts() {
		StringBuilder builder = new StringBuilder();
		for(Text text : texts) {
			builder.append("  BT").append(LINE_SEPARATOR) //Begin text
			.append("    /").append(text.getFontVariant().getName()).append(" ").append(text.getSize()).append(" Tf").append(LINE_SEPARATOR) //Use font named "F1"
			.append("    ").append(text.getX()).append(" ").append(text.getY()).append(" Td").append(LINE_SEPARATOR) //Start text as 0, 0
			.append("    ").append(text.getColor().getRed() / 255.0f) 
			.append(" ").append(text.getColor().getGreen() / 255.0f)
			.append(" ").append(text.getColor().getBlue() / 255.0f)
			.append(" rg").append(LINE_SEPARATOR)
			//( and ) are interpreted by PDF readers, so we must escape them.
			.append("    (").append(text.getValue().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")).append(") Tj").append(LINE_SEPARATOR)
			.append("  ET").append(LINE_SEPARATOR); //End text
		}
		return builder.toString();
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
