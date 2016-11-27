package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.geometry.BoundingBox;

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
	protected Map<Byte, BoundingBox> boxes = new HashMap<>();
	/**
	 * The kerning between two bytes.
	 */
	protected Map<Byte, Map<Byte, Integer>> kernings = new HashMap<>();
	/**
	 * The position of underline.
	 */
	protected int underlinePosition;
	/**
	 * The thickness of underline.
	 */
	protected int underlineThickness;
	/**
	 * The name of font variant.
	 */
	protected String name;

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
		Map<Byte, Integer> kernMap = this.kernings.get(source);
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
	public int getUnderlinePosition() {
		return underlinePosition;
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
	public int getUnderlinePosition(int size) {
		return (int) (underlinePosition * size / 1000.0f);
	}

	/**
	 * Set the standard position of the underline.
	 * 
	 * @param size The size of the font to set position for.
	 * @param position The position of the underline.
	 */
	public void setUnderlinePosition(int size, int position) {
		this.underlinePosition = (int) (position * 1000.f / size);
	}

	/**
	 * Get the thickness of the underline.
	 * 
	 * @return The thickness.
	 */
	public int getUnderlineThickness() {
		return underlineThickness;
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
	public int getUnderlineThickness(int size) {
		return (int) (underlineThickness * size / 1000.0f);
	}

	/**
	 * Set the standard thickness of the underline.
	 * 
	 * @param size The size of the font to set thickness for.
	 * @param thickness The thickness of the underline.
	 */
	public void setUnderlineThickness(int size, int thickness) {
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
	public int getWidth(Integer size, String string) {
		int fullWidth = 0;
		Byte previousLetter = null;
		for(byte letter : string.getBytes()) {
			fullWidth += getWidth(letter);
			fullWidth += getKerning(previousLetter, letter);
			previousLetter = letter;
		}
		return Math.round(fullWidth * ((float) size) / 1000.0f);
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
			Map<Byte, Integer> kerns = kernings.get(previous);
			if(kerns != null) {
				Integer kern = kerns.get(current);
				if(kern != null) {
					kerning = kern;
				}
			}
		}
		
		return kerning;
	}
}
