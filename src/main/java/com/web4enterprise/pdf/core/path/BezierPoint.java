package com.web4enterprise.pdf.core.path;

import com.web4enterprise.pdf.core.geometry.Point;

/**
 * Class extending a simple point to add controls necessary for Bezier curves.
 * 
 * @author Régis Ramillien
 */
public class BezierPoint extends Point {
	/**
	 * The X position of first control point.
	 */
	protected int x1;
	/**
	 * The Y position of first control point.
	 */
	protected int y1;
	/**
	 * The X position of second control point.
	 */
	protected int x2;
	/**
	 * The Y position of second control point.
	 */
	protected int y2;
	
	/**
	 * Create a Bezier point with control points over the main none.
	 * 
	 * @param x The X position of the point.
	 * @param y The Y position of the point.
	 */
	public BezierPoint(int x, int y) {
		super(x, y);
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}
	
	/**
	 * Create a Bezier point.
	 * 
	 * @param x The X position of the point.
	 * @param y The Y position of the point.
	 * @param x1 The X position of the first control point.
	 * @param y1 The Y position of the first control point.
	 * @param x2 The X position of the second control point.
	 * @param y2 The Y position of the second control point.
	 */
	public BezierPoint(int x, int y, int x1, int y1, int x2, int y2) {
		super(x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Get the X position of first control point.
	 * 
	 * @return The X coordinate of first control point.
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Set the X position of first control point.
	 * 
	 * @param x1 The X coordinate of first control point.
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * Get the Y position of first control point.
	 * 
	 * @return The Y coordinate of first control point.
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Set the Y position of first control point.
	 * 
	 * @param y1 The X coordinate of first control point.
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}

	/**
	 * Get the X position of second control point.
	 * 
	 * @return The X coordinate of second control point.
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Set the X position of second control point.
	 * 
	 * @param x2 The X coordinate of second control point.
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * Get the Y position of second control point.
	 * 
	 * @return The Y coordinate of second control point.
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Set the Y position of second control point.
	 * 
	 * @param y2 The Y coordinate of second control point.
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
}
