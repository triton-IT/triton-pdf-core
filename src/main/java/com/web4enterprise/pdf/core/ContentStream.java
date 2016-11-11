package com.web4enterprise.pdf.core;

import java.util.ArrayList;
import java.util.List;

public class ContentStream implements PDFObject {
	protected int id;
	
	protected List<Text> texts = new ArrayList<>();
	protected List<StraightPath> lines = new ArrayList<>();
	protected List<BezierPath> bezierLines = new ArrayList<>();
	
	public ContentStream(int id) {
		this.id = id;
	}
	
	@Override
	public String asString() {
		String textsValues = "";
		for(Text text : texts) {
			textsValues += "  BT" + LINE_SEPARATOR //Begin text
			+ "    /F1 " + text.getSize() + " Tf" + LINE_SEPARATOR //Use font named "F1"
			+ "    " + text.getX() + " " + text.getY() + " Td" + LINE_SEPARATOR //Start text as 0, 0
			+ "    (" + text.getValue() + ") Tj" + LINE_SEPARATOR
			+ "  ET" + LINE_SEPARATOR; //End text
		}
		
		String linesValues = "";		
		for(StraightPath path : lines) {
			linesValues += path.getLineWidth() + " w ";
			
			linesValues += path.getStrokeColor().getR() + " "
					+ path.getStrokeColor().getG() + " "
					+ path.getStrokeColor().getB() + " "
					+ "RG ";
			
			linesValues += path.getFillColor().getR() + " "
					+ path.getFillColor().getG() + " "
					+ path.getFillColor().getB() + " "
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
			
			bezierLinesValues += path.getStrokeColor().getR() + " "
					+ path.getStrokeColor().getG() + " "
					+ path.getStrokeColor().getB() + " "
					+ "RG ";
			
			bezierLinesValues += path.getFillColor().getR() + " "
					+ path.getFillColor().getG() + " "
					+ path.getFillColor().getB() + " "
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

		String asString = id + " 0 obj" + LINE_SEPARATOR
				+ "<< /Length " + textsValues.length() + " >>" + LINE_SEPARATOR
				+ "stream" + LINE_SEPARATOR
				+ textsValues
				+ linesValues
				+ bezierLinesValues
				+ "endstream" + LINE_SEPARATOR;
		
		return asString;
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
}
