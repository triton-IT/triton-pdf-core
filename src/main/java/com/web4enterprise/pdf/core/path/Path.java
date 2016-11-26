package com.web4enterprise.pdf.core.path;

import com.web4enterprise.pdf.core.geometry.Point;
import com.web4enterprise.pdf.core.styling.Color;

/**
 * Class representing a simple path with its default properties.
 * 
 * @author Régis Ramillien
 */
public abstract class Path {
	/**
	 * The first point of path.
	 */
	protected Point startPoint;
	/**
	 * The stroke State of the path (default is true).
	 */
	protected boolean stroked = true;
	/**
	 * The close state of the path (default is false).
	 */
	protected boolean closed;
	/**
	 * The filled state of the path (default is false).
	 */
	protected boolean filled;
	/**
	 * The line width of the path (default is 1.0f).
	 */
	protected float lineWidth = 1.0f;
	/**
	 * The stroke color (default is black).
	 */
	protected Color strokeColor = Color.BLACK;
	/**
	 * The fill color (default is black).
	 */
	protected Color fillColor = Color.BLACK;
	
	/**
	 * Creates a path.
	 * 
	 * @param startPoint The first point of path.
	 */
	public Path(Point startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Get the first point of the path.
	 * 
	 * @return The first point in the path.
	 */
	public Point getStartPoint() {
		return startPoint;
	}

	/**
	 * Get the stroke state of path.
	 * 
	 * @return true if path is stroked, false otherwise.
	 */
	public boolean isStroked() {
		return stroked;
	}
	
	/**
	 * Set the stroke state to true.
	 */
	public void stroke() {
		stroked = true;
	}
	
	/**
	 * Set the stroke state to false.
	 */
	public void unstroke() {
		stroked = false;
	}

	/**
	 * Get the close state of path.
	 * 
	 * @return true if path is closed, false otherwise.
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * Set the close stae to true.
	 */
	public void close() {
		closed = true;
	}
	
	/**
	 * Set the close state to false.
	 */
	public void open() {
		closed = false;
	}

	/**
	 * Get the fill state of path.
	 * 
	 * @return true if path is filled, false otherwise.
	 */
	public boolean isFilled() {
		return filled;
	}
	
	/**
	 * Set the fill state to true.
	 */
	public void fill() {
		filled = true;
	}
	
	/**
	 * Set the fill state to false.
	 */
	public void unfill() {
		filled = false;
	}

	/**
	 * Get the line width of path.
	 * 
	 * @return The line width.
	 */
	public float getLineWidth() {
		return lineWidth;
	}

	/**
	 * Set the line width of path.
	 * 
	 * @param lineWidth The line width to set.
	 */
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * Get the stroke color of path.
	 * 
	 * @return The stroke color.
	 */
	public Color getStrokeColor() {
		return strokeColor;
	}

	/**
	 * Set the stroke color of path.
	 * 
	 * @param strokeColor The stroke color.
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	/**
	 * Get the fill color of path.
	 * 
	 * @return The fill color.
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Set the fill color of path.
	 * 
	 * @param fillColor The fill color.
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}
