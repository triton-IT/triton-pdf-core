package com.web4enterprise.pdf.core;

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
