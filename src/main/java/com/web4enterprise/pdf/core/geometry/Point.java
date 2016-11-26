package com.web4enterprise.pdf.core.geometry;

/**
 * A simple point in a 2 dimensions space.
 * 
 * @author Régis Ramillien
 */
public class Point {
	protected int x;
	protected int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
