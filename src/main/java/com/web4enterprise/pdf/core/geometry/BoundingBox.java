package com.web4enterprise.pdf.core.geometry;

/**
 * Class representing the bounding box of a 2 dimensional object.
 * 
 * @author Régis Ramillien
 */
public class BoundingBox {
	protected int left;
	protected int bottom;
	protected int right;
	protected int top;

	public BoundingBox(int left, int bottom, int right,	int top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}
}
