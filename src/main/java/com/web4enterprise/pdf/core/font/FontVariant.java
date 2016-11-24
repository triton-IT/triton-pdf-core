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
	
	public Map<Byte, Integer> getWidths() {
		return widths;
	}

	public void addWidth(Byte character, Integer width) {
		this.widths.put(character, width);
	}

	public Map<Byte, BoundingBox> getBoxes() {
		return boxes;
	}

	public void addBox(Byte character, BoundingBox box) {
		this.boxes.put(character, box);
	}

	public Map<Byte, Map<Byte, Integer>> getKernings() {
		return kernings;
	}

	public void addKerning(Byte source, Byte destination, Integer kerning) {
		Map<Byte, Integer> kernMap = this.kernings.get(this.kernings);
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
		for(byte letter : string.getBytes()) {
			Integer width = widths.get(letter);
			if(width != null) {
				fullWidth += width;
			}
		}
		return Math.round(fullWidth * ((float) size) / 1000.0f);
	}
}
