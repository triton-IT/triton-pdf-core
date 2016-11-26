package com.web4enterprise.pdf.core.text;

import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontStyle;
import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.styling.Color;

/**
 * Class representing a text to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class Text {	
	protected int x;
	protected int y;
	protected int size;
	protected FontVariant fontVariant;
	protected Color color;
	protected String value;
	
	public Text(int x, int y, int size, String value) {
		this(x, y, size, Font.TIMES_ROMAN.getVariant(FontStyle.PLAIN), Color.BLACK, value);
	}
	
	public Text(int x, int y, int size, FontVariant fontVariant, String value) {
		this(x, y, size, fontVariant, Color.BLACK, value);
	}
	
	public Text(int x, int y, int size, FontVariant fontVariant, Color color, String value) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.fontVariant = fontVariant;
		this.color = color;
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

	public Color getColor() {
		return color;
	}

	public String getValue() {
		return value;
	}
}
