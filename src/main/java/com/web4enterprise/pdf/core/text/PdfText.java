/*
 * Copyright 2017 web4enterprise.
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

import com.web4enterprise.pdf.core.path.PdfStraightPath;
import com.web4enterprise.report.commons.font.FontCache;
import com.web4enterprise.report.commons.font.FontVariant;
import com.web4enterprise.report.commons.geometry.Point;
import com.web4enterprise.report.commons.style.Color;
import com.web4enterprise.report.commons.text.Text;

/**
 * Class representing a text to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class PdfText extends Text {
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param value The string to display.
	 */
	public PdfText(float x, float y, float size, String value) {
		this(x, y, size, FontCache.TIMES_ROMAN.getVariant(FontVariant.PLAIN), Color.BLACK, value);
	}
	
	/**
	 * Creates a text.
	 * @param x The X position of text in page.
	 * @param y The X position of text in page.
	 * @param size The size of text.
	 * @param fontVariant The variant of font to use to render text.
	 * @param value The string to display.
	 */
	public PdfText(float x, float y, float size, FontVariant fontVariant, String value) {
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
	public PdfText(float x, float y, float size, FontVariant fontVariant, Color color, String value) {
		super(x, y, size, fontVariant, color, value);
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
			new PdfStraightPath(getFontVariant().getUnderlineThickness(getSize()), getUnderlineColor(), 
					new Point(getX(), underlineY), 
					new Point(getX() + getFontVariant().getWidth(getSize(), getValue()), 
							underlineY)).render(builder);
		}
	}
}
