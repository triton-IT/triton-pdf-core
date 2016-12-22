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
package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.geometry.Rect;

/**
 * Class representing a font variant.
 * A font variant is a "styling" (plain, bold, italic) of a base font.
 * 
 * @author Régis Ramillien
 */
public class FontVariant {
	/**
	 * The width of each byte described in the font.
	 */
	protected Map<Byte, Integer> widths = new HashMap<>();
	/**
	 * The bounding box of each byte described in the font.
	 */
	protected Map<Byte, Rect> boxes = new HashMap<>();
	/**
	 * The kerning between two bytes.
	 */
	protected Map<Byte, Map<Byte, Float>> kernings = new HashMap<>();
	/**
	 * The position of underline.
	 */
	protected float underlinePosition;
	/**
	 * The thickness of underline.
	 */
	protected float underlineThickness;
	/**
	 * The name of font variant.
	 */
	protected String name;
	/**
	 * The bounding box of the font
	 */
	protected Rect boundingBox;

	/**
	 * Add the width of the given byte.
	 * 
	 * @param character The byte to add the width for.
	 * @param width The width of the byte.
	 */
	public void addWidth(Byte character, Integer width) {
		this.widths.put(character, width);
	}

	/**
	 * Add the bounding box of the given byte.
	 * 
	 * @param character The byte to add the bounding box for.
	 * @param box The bounding box of the byte.
	 */
	public void addBox(Byte character, Rect box) {
		this.boxes.put(character, box);
	}

	/**
	 * Add a kerning between two bytes.
	 * 
	 * @param source The source byte.
	 * @param destination The "bound" byte.
	 * @param kerning The kerning between the two bytes.
	 */
	public void addKerning(Byte source, Byte destination, Float kerning) {
		Map<Byte, Float> kernMap = this.kernings.get(source);
		if(kernMap == null) {
			kernMap = new HashMap<>();
			this.kernings.put(source, kernMap);
		}
		kernMap.put(destination, kerning);
	}

	/**
	 * Get the position of underline.
	 * 
	 * @return The position.
	 */
	public float getUnderlinePosition() {
		return underlinePosition;
	}

	/**
	 * Set the standard position of the underline.
	 * 
	 * @param position The position of the underline.
	 */
	public void setUnderlinePosition(float position) {
		this.underlinePosition = position;
	}

	/**
	 * Get the position of underline.
	 * 
	 * @param size The size of the font to get position for.
	 * @return The position.
	 */
	public float getUnderlinePosition(float size) {
		return underlinePosition * size / 1000.0f;
	}

	/**
	 * Set the standard position of the underline.
	 * 
	 * @param size The size of the font to set position for.
	 * @param position The position of the underline.
	 */
	public void setUnderlinePosition(int size, float position) {
		this.underlinePosition = position * 1000.f / size;
	}

	/**
	 * Get the thickness of the underline.
	 * 
	 * @return The thickness.
	 */
	public float getUnderlineThickness() {
		return underlineThickness;
	}

	/**
	 * Set the standard thickness of the underline.
	 * 
	 * @param thickness The thickness of the underline.
	 */
	public void setUnderlineThickness(float thickness) {
		this.underlineThickness = thickness;
	}

	/**
	 * Get the thickness of the underline.
	 * 
	 * @param size The size of the font to get thickness for.
	 * @return The thickness.
	 */
	public float getUnderlineThickness(float size) {
		return underlineThickness * size / 1000.0f;
	}

	/**
	 * Set the standard thickness of the underline.
	 * 
	 * @param size The size of the font to set thickness for.
	 * @param thickness The thickness of the underline.
	 */
	public void setUnderlineThickness(int size, float thickness) {
		this.underlineThickness = thickness * 1000.f / size;
	}

	/**
	 * Get the name of the font variant.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the font variant.
	 * 
	 * @param name The name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get bounding box of font.
	 * 
	 * @return The bounding box of the font.
	 */
	public Rect getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Set the bounding box of font.
	 * 
	 * @param boundingBox the bounding box of font.
	 */
	public void setBoundingBox(Rect boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
	 * Get the width of the String with the variant and the given size.
	 * 
	 * @param fontSize The size to get the width for.
	 * @param string The String to get the width for.
	 * @return The size of String.
	 */
	public float getWidth(Integer fontSize, String string) {
		float fullWidth = 0;
		Byte previousLetter = null;
		for(byte letter : string.getBytes()) {
			fullWidth += getWidth(letter);
			fullWidth += getKerning(previousLetter, letter);
			previousLetter = letter;
		}
		return Math.round(fullWidth * ((float) fontSize) / 1000.0f);
	}
	
	/**
	 * Get the height of font variant for the specified font size.
	 * 
	 * @param fontSize The size of font.
	 * @return The height of font based on its bounding box.
	 */
	public float getHeight(Integer fontSize) {
		return getBaseLine(fontSize) - getDistanceFromBottom(fontSize);
	}
	
	/**
	 * Get baseline position.
	 * 
	 * @param fontSize The font size to calculate base line for.
	 * @return The Y position of the baseline.
	 */
	public float getBaseLine(Integer fontSize) {
		return fontSize - (((1000.0f - boundingBox.getTop()) / 1000.0f) * fontSize);
	}
	
	/**
	 * Get distance from bottom of the bounding box to text.
	 * 
	 * @param fontSize The size of font to calculate distance for.
	 * @return The distance in PDF unit.
	 */
	public float getDistanceFromBottom(int fontSize) {
		return (boundingBox.getBottom() / 1000.0f) * fontSize;
	}
	
	/**
	 * Get distance from top of the bounding box to text.
	 * 
	 * @param fontSize The size of font to calculate distance for.
	 * @return The distance in PDF unit.
	 */
	public float getDistanceFromTop(int fontSize) {
		return (boundingBox.getTop() / 1000.0f) * fontSize;
	}

	/**
	 * Get the width of a byte.
	 * 
	 * @param character The character to get width for.
	 * @return THe size of the byte.
	 */
	protected int getWidth(Byte character) {
		int result = 0;
		
		Integer width = widths.get(character);
		if(width != null) {
			result = width;
		}
		
		return result;
	}
	
	/**
	 * Get the kerning between two characters.
	 * 
	 * @param previous The first character to get kerning from.
	 * @param current The second character to get kerning from.
	 * @return The kerning found or 0 if none.
	 */
	protected float getKerning (Byte previous, Byte current) {
		float kerning = 0;
		
		if(previous != null) {
			Map<Byte, Float> kerns = kernings.get(previous);
			if(kerns != null) {
				Float kern = kerns.get(current);
				if(kern != null) {
					kerning = kern;
				}
			}
		}
		
		return kerning;
	}
}
