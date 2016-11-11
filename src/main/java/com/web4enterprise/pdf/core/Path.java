package com.web4enterprise.pdf.core;

public abstract class Path {
	protected Point startPoint;
	protected boolean stroked = true;
	protected boolean closed;
	protected boolean filled;
	protected float lineWidth = 1.0f;
	protected Color strokeColor = new Color(0.0f, 0.0f, 0.0f);
	protected Color fillColor = new Color(0.0f, 0.0f, 0.0f);
	
	public Path(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public boolean isStroked() {
		return stroked;
	}
	
	public void stroke() {
		stroked = true;
	}
	
	public void unstroke() {
		stroked = false;
	}

	public boolean isClosed() {
		return closed;
	}
	
	public void close() {
		closed = true;
	}
	
	public void open() {
		closed = false;
	}

	public boolean isFilled() {
		return filled;
	}
	
	public void fill() {
		filled = true;
	}
	
	public void unfill() {
		filled = false;
	}

	public float getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}
