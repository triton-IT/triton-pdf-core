package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.BoundingBox;

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
			Integer width = widths.get(letter);
			if(width != null) {
				fullWidth += width;
			}
			if(previousLetter != null) {
				Map<Byte, Integer> kerns = kernings.get(previousLetter);
				if(kerns != null) {
					Integer kern = kerns.get(letter);
					if(kern != null) {
						fullWidth += kern;
					}
				}
			}
			previousLetter = letter;
		}
		return Math.round(fullWidth * ((float) size) / 1000.0f);
	}
}
