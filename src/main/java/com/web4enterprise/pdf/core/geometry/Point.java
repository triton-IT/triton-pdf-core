package com.web4enterprise.pdf.core.geometry;

/**
 * A simple point in a 2 dimensions space.
 * 
 * @author Régis Ramillien
 */
public class Point {
	/**
	 * The X position of point.
	 */
	protected int x;
	/**
	 * The Y position of point.
	 */
	protected int y;
	
	/**
	 * Create a Point with X and Y coordinates.
	 * 
	 * @param x The X position of point.
	 * @param y The Y position of point.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X coordinate of point.
	 * 
	 * @return The X position.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the X coordinate of point.
	 * 
	 * @param x The new position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the Y coordinate of point.
	 * 
	 * @return The Y position.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the Y coordinate of point.
	 * 
	 * @param y The new position.
	 */
	public void setY(int y) {
		this.y = y;
	}
}
