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
	protected Map<Byte, Integer> widths = new HashMap<>();
	protected Map<Byte, BoundingBox> boxes = new HashMap<>();
	protected Map<Byte, Map<Byte, Integer>> kernings = new HashMap<>();
	protected int underlinePosition;
	protected int underlineThickness;
	protected String name;

	public void addWidth(Byte character, Integer width) {
		this.widths.put(character, width);
	}

	public void addBox(Byte character, BoundingBox box) {
		this.boxes.put(character, box);
	}

	public void addKerning(Byte source, Byte destination, Integer kerning) {
		Map<Byte, Integer> kernMap = this.kernings.get(source);
		if(kernMap == null) {
			kernMap = new HashMap<>();
			this.kernings.put(source, kernMap);
		}
		kernMap.put(destination, kerning);
	}

	public int getUnderlinePosition() {
		return underlinePosition;
	}

	public void setUnderlinePosition(int underlinePosition) {
		this.underlinePosition = underlinePosition;
	}

	public int getUnderlineThickness() {
		return underlineThickness;
	}

	public void setUnderlineThickness(int underlineThickness) {
		this.underlineThickness = underlineThickness;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	private int getWidth(Byte letter) {
		int result = 0;
		
		Integer width = widths.get(letter);
		if(width != null) {
			result = width;
		}
		
		return result;
	}
	
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
