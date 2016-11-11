package com.web4enterprise.pdf.writer;


public class Text {	
	protected int x;
	protected int y;
	protected int size;
	protected String value;
	
	public Text(int x, int y, int size, String value) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.value = value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getValue() {
		return value;
	}
}
