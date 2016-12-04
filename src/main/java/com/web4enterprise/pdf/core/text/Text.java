/*
 * Copyright 2016 web4enterprise.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	 * The state of underline.
	 */
	protected boolean underlined;
	/**
	 * The color of the underline.
	 */
	protected Color underlineColor;
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
	 * @return The X coordinate.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Set the X position of text in the page.
	 * 
	 * @param x the X coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Get the Y position of text in the page.
	 * 
	 * @return The Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the Y position of the text in the page. 
	 * 
	 * @param y The Y coordinate.
	 */
	public void setY(int y) {
		this.y = y;
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
	 * Set the size of text.
	 * 
	 * @param size The size of text.
	 */
	public void setSize(int size) {
		this.size = size;
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
	 * Set the variant of font to use to render text.
	 * 
	 * @param fontVariant The font variant.
	 */
	public void setFontVariant(FontVariant fontVariant) {
		this.fontVariant = fontVariant;
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
	 * Set the color of the text.
	 * 
	 * @param color The color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Get the underlined state of the text.	
	 * 
	 * @return The underlined state.
	 */
	public boolean isUnderlined() {
		return underlined;
	}

	/**
	 * Set the underlined state of the text.	
	 * 
	 * @param underlined The underlined state.
	 */
	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
	}

	/**
	 * Get the color of underline.
	 * 
	 * @return The color of underline or the color of text if not set.
	 */
	public Color getUnderlineColor() {
		Color returnedColor = underlineColor;
		if(returnedColor == null) {
			returnedColor = this.color;
		}
		return returnedColor;
	}

	/**
	 * Set the color of underline.
	 * 
	 * @param underlineColor The color of underline.
	 */
	public void setUnderlineColor(Color underlineColor) {
		this.underlineColor = underlineColor;
	}

	/**
	 * Get the value of the text.
	 * 
	 * @return The character string.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value of the text.
	 * 
	 * @param value The character string.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
