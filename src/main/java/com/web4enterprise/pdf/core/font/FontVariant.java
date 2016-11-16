package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.BoundingBox;

public abstract class FontVariant {	
	protected static Map<Byte, Integer> widths = new HashMap<>();
	protected static Map<Byte, BoundingBox> boxes = new HashMap<>();
	
	public int getWidth(Integer size, String string) {
		int fullWidth = 0;
		for(byte letter : string.getBytes()) {
			Integer width = widths.get(letter);
			if(width != null) {
				fullWidth += width;
			}
		}
		return (int) Math.round(fullWidth * size / 1000.0f);
	}
	
	public int getHeight(Integer size, String string) {
		int greaterHeight = 0;
		for(byte letter : string.getBytes()) {
			BoundingBox letterBox = boxes.get(letter);
			int height = letterBox.getHeight();
			if(height > greaterHeight) {
				greaterHeight = height;
			}
		}
		return (int) Math.round(greaterHeight * size / 1000.0f);
	}
	
	public abstract String getName();
}
