/*
 * Copyright 2021 tritonit.tech.
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
package tech.tritonit.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import tech.tritonit.pdf.core.document.PdfContext;
import tech.tritonit.pdf.core.geometry.BoundingBox;

/**
 * Class representing a font variant.
 * A font variant is a "styling" (plain, bold, italic) of a base font.
 * 
 * @author Régis Ramillien
 */
public class FontVariant {
	/**
	 * Context of the PDF owner of this instance.
	 */
	protected final PdfContext context;
	/**
	 * The width of each byte described in the font.
	 */
	protected final Map<Byte, Integer> widths = new HashMap<>();
	/**
	 * The bounding box of each byte described in the font.
	 */
	protected final Map<Byte, BoundingBox> boxes = new HashMap<>();
	/**
	 * The kerning between two bytes.
	 */
	protected final Map<Byte, Map<Byte, Integer>> kernings = new HashMap<>();
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

	FontVariant(PdfContext context) {
		this.context = context;
	}

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
	public void addBox(Byte character, BoundingBox box) {
		this.boxes.put(character, box);
	}

	/**
	 * Add a kerning between two bytes.
	 * 
	 * @param source The source byte.
	 * @param destination The "bound" byte.
	 * @param kerning The kerning between the two bytes.
	 */
	public void addKerning(Byte source, Byte destination, Integer kerning) {
		Map<Byte, Integer> kernMap = this.kernings.computeIfAbsent(source, k -> new HashMap<>());
		kernMap.put(destination, kerning);
	}

	/**
	 * Get the position of underline.
	 * 
	 * @return The position.
	 */
	public int getUnderlinePosition() {
		return (int) underlinePosition;
	}

	/**
	 * Set the standard position of the underline.
	 * 
	 * @param position The position of the underline.
	 */
	public void setUnderlinePosition(int position) {
		this.underlinePosition = position;
	}

	/**
	 * Get the position of underline.
	 * 
	 * @param size The size of the font to get position for.
	 * @return The position.
	 */
	public float getUnderlinePosition(int size) {
		return underlinePosition * size / 1000.0f;
	}

	/**
	 * Set the standard position of the underline.
	 * 
	 * @param size The size of the font to set position for.
	 * @param position The position of the underline.
	 */
	public void setUnderlinePosition(int size, float position) {
		this.underlinePosition = (int) (position * 1000.f / size);
	}

	/**
	 * Get the thickness of the underline.
	 * 
	 * @return The thickness.
	 */
	public int getUnderlineThickness() {
		return (int) underlineThickness;
	}

	/**
	 * Set the standard thickness of the underline.
	 * 
	 * @param thickness The thickness of the underline.
	 */
	public void setUnderlineThickness(int thickness) {
		this.underlineThickness = thickness;
	}

	/**
	 * Get the thickness of the underline.
	 * 
	 * @param size The size of the font to get thickness for.
	 * @return The thickness.
	 */
	public float getUnderlineThickness(int size) {
		return underlineThickness * size / 1000.0f;
	}

	/**
	 * Set the standard thickness of the underline.
	 * 
	 * @param size The size of the font to set thickness for.
	 * @param thickness The thickness of the underline.
	 */
	public void setUnderlineThickness(int size, float thickness) {
		this.underlineThickness = (int) (thickness * 1000.f / size);
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
	 * Get the width of the String with the variant and the given size.
	 * 
	 * @param size The size to get the width for.
	 * @param string The String to get the width for.
	 * @return The size of String.
	 */
	public float getWidth(Integer size, String string) {
		int fullWidth = 0;
		Byte previousLetter = null;
		for(byte letter : string.getBytes()) {
			fullWidth += getWidth(letter);
			fullWidth += getKerning(previousLetter, letter);
			previousLetter = letter;
		}
		return context.getUnit().toUnit((fullWidth * ((float) size)) / 1000.0f);
	}
	
	/**
	 * Get the width of a byte.
	 * 
	 * @param character The character to get width for.
	 * @return THe size of the byte.
	 */
	private int getWidth(Byte character) {
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
	private int getKerning (Byte previous, Byte current) {
		int kerning = 0;
		
		if(previous != null) {
			Map<Byte, Integer> previousLetterKernings = kernings.get(previous);
			if(previousLetterKernings != null) {
				Integer kerningFound = previousLetterKernings.get(current);
				if(kerningFound != null) {
					kerning = kerningFound;
				}
			}
		}
		
		return kerning;
	}
}
