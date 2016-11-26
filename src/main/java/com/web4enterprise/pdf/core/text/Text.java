package com.web4enterprise.pdf.core.text;

import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontsVariant;
import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.styling.Color;

/**
 * Class representing a text to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class Text {
	/**
	 * The X position of text in page.
	 */
	protected int x;
	/**
	 * The Y position of text in page.
	 */
	protected int y;
	/**
	 * The size of text.
	 */
	protected int size;
	/**
	 * The variant of font to use to render text.
	 */
	protected FontVariant fontVariant;
	/**
	 * The color of the text.
	 */
	protected Color color;
	/**
	 * The string to display.
	 */
	protected String value;
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param value The string to display.
	 */
	public Text(int x, int y, int size, String value) {
		this(x, y, size, Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN), Color.BLACK, value);
	}
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param fontVariant The variant of font to use to render text.
	 * @param value The string to display.
	 */
	public Text(int x, int y, int size, FontVariant fontVariant, String value) {
		this(x, y, size, fontVariant, Color.BLACK, value);
	}
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param fontVariant The variant of font to use to render text.
	 * @param color The color of the text.
	 * @param value The string to display.
	 */
	public Text(int x, int y, int size, FontVariant fontVariant, Color color, String value) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.fontVariant = fontVariant;
		this.color = color;
		this.value = value;
	}
	
	/**
	 * Get the X position of text in the page.
	 * 
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the Y position of text in the page.
	 * 
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Get the size of text.
	 * 
	 * @return The size of text.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Get the variant of font to use to render text.
	 * 
	 * @return The font variant.
	 */
	public FontVariant getFontVariant() {
		return fontVariant;
	}

	/**
	 * Get the color of the text.
	 * 
	 * @return The color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Get the value of the text.
	 * 
	 * @return The character string.
	 */
	public String getValue() {
		return value;
	}
}
