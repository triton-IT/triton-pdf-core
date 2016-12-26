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

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import com.web4enterprise.pdf.core.Renderable;
import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.font.FontsVariant;
import com.web4enterprise.pdf.core.geometry.Point;
import com.web4enterprise.pdf.core.geometry.Rect;
import com.web4enterprise.pdf.core.link.Anchor;
import com.web4enterprise.pdf.core.link.Linkable;
import com.web4enterprise.pdf.core.path.StraightPath;
import com.web4enterprise.pdf.core.styling.Color;

/**
 * Class representing a text to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class Text implements Anchor, Renderable {
	/**
	 * The X position of text in page.
	 */
	protected float x;
	/**
	 * The Y position of text in page.
	 */
	protected float y;
	/**
	 * The size of text.
	 */
	protected float size;
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
	 * The type of script.
	 */
	protected TextScript script = TextScript.NORMAL;
	/**
	 * The string to display.
	 */
	protected String value;
	/**
	 * The identifier of the page where this text is contained to.
	 */
	protected int pageId;
	/**
	 * The {@link Renderable} where this renderable is bound to.
	 */
	protected Linkable link;
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param value The string to display.
	 */
	public Text(float x, float y, float size, String value) {
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
	public Text(float x, float y, float size, FontVariant fontVariant, String value) {
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
	public Text(float x, float y, float size, FontVariant fontVariant, Color color, String value) {
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
	public float getX() {
		return x;
	}
	
	/**
	 * Set the X position of text in the page.
	 * 
	 * @param x the X coordinate.
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Get the Y position of text in the page.
	 * 
	 * @return The Y coordinate.
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set the Y position of the text in the page. 
	 * 
	 * @param y The Y coordinate.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Get the size of text.
	 * 
	 * @return The size of text.
	 */
	public float getSize() {
		return size;
	}

	/**
	 * Set the size of text.
	 * 
	 * @param size The size of text.
	 */
	public void setSize(float size) {
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
	 * Get the text script.
	 * 
	 * @return The text script.
	 */
	public TextScript getScript() {
		return script;
	}

	/**
	 * Set the text script.
	 * 
	 * @param script The text script.
	 */
	public void setScript(TextScript script) {
		this.script = script;
	}

	/**
	 * Set the value of the text.
	 * 
	 * @param value The character string.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public float getWidth() {
		float width;
		switch(script) {
		case SUPER:
		case SUB:
			width = getFontVariant().getWidth(getSize() / 2.0f, getValue());
			break;
		default:
			width = getFontVariant().getWidth(getSize(), getValue());
			break;
		}
		return width;
	}
	
	@Override
	public void setLink(Linkable destination) {
		this.link = destination;
	}
	
	@Override
	public Linkable getLink() {
		return link;
	}
	
	@Override
	public void setPage(int pageId) {
		this.pageId = pageId;
	}
	
	@Override
	public int getPage() {
		return pageId;
	}
	
	@Override
	public float getLinkX() {
		return getX();
	}
	
	@Override
	public float getLinkY() {
		return getY() + getFontVariant().getHeight(getSize());
	}
	
	@Override
	public void render(StringBuilder builder) {
		builder.append("BT").append(LINE_SEPARATOR) //Begin text
			.append("/").append(getFontVariant().getName()).append(" ");
		
		switch(script) {
		case SUPER:
		case SUB:
			builder.append(getSize() / 2);
			break;
		default:
			builder.append(getSize());
			break;
		}
			
		builder.append(" Tf").append(LINE_SEPARATOR) //Use font named "F1"
			.append(getX()).append(" ").append(getY()).append(" Td").append(LINE_SEPARATOR) //Start text as 0, 0
			.append(getColor().getRed() / 255.0f).append(" ")
			.append(getColor().getGreen() / 255.0f).append(" ")
			.append(getColor().getBlue() / 255.0f).append(" ")
			.append("rg").append(LINE_SEPARATOR);

		switch(script) {
		case SUPER:
			builder.append(getFontVariant().getDistanceFromTop(getSize()) - getFontVariant().getBaseLine(getSize() / 2));
			break;
		case SUB:
			builder.append(getFontVariant().getDistanceFromBottom(getSize()) + (getFontVariant().getHeight(getSize() / 2) - getFontVariant().getBaseLine(getSize() / 2)));
			break;
		default:
			builder.append(0);
			break;
		}
		builder.append(" Ts").append(LINE_SEPARATOR);
		
		//'(' and ')' are interpreted by PDF readers, so we must escape them.
		builder.append("(").append(getValue().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")).append(") Tj").append(LINE_SEPARATOR)
			.append("ET").append(LINE_SEPARATOR); //End text
		
		if(isUnderlined()) {
			int underlineY = (int) (getY() + getFontVariant().getUnderlinePosition(getSize()));
			new StraightPath(getFontVariant().getUnderlineThickness(getSize()), getUnderlineColor(), 
					new Point(getX(), underlineY), 
					new Point(getX() + getFontVariant().getWidth(getSize(), getValue()), 
							underlineY)).render(builder);
		}
	}
	
	@Override
	public Rect getBoundingBox() {
		return new Rect(getY() + (getSize() - (getFontVariant().getHeight(getSize()) - getFontVariant().getBaseLine(getSize()))),
			getX(), 
			getY() + getFontVariant().getDistanceFromBottom(getSize()), 
			getX() + getWidth());
	}
}
