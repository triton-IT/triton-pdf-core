package com.web4enterprise.pdf.core;

import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.font.TimesRomanPlain;


public class Text {	
	protected int x;
	protected int y;
	protected int size;
	protected FontVariant fontVariant = new TimesRomanPlain();
	protected String value;
	
	public Text(int x, int y, int size, String value) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.value = value;
	}
	
	public Text(int x, int y, int size, FontVariant fontVariant, String value) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.fontVariant = fontVariant;
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
	
	public FontVariant getFontVariant() {
		return fontVariant;
	}

	public String getValue() {
		return value;
	}
}
