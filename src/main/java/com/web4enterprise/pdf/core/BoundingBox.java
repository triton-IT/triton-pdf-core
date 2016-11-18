package com.web4enterprise.pdf.core;

public class BoundingBox {
	protected int upperLeft;
	protected int upperRight;
	protected int lowerLeft;
	protected int lowerRight;

	public BoundingBox(int upperLeft, int upperRight, int lowerLeft,
			int lowerRight) {
		this.upperLeft = upperLeft;
		this.upperRight = upperRight;
		this.lowerLeft = lowerLeft;
		this.lowerRight = lowerRight;
	}
	
    public int getHeight() {
        return upperLeft - lowerLeft;
    }
}
