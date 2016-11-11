package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.BoundingBox;

public abstract class Font {	
	protected static Map<String, Font> fonts = new HashMap<>();
	protected static Map<Byte, Integer> widths = new HashMap<>();
	protected static Map<Byte, BoundingBox> boxes = new HashMap<>();
	
	static {
		fonts.put("Times-Roman", new TimesRoman());
	}
	
	public int getWidth(Integer size, String text) {
		int fullWidth = 0;
		for(byte letter : text.getBytes()) {
			fullWidth += widths.get(letter);
		}
		return (int) Math.round(fullWidth * size / 1000.0f);
	}
	
	public int getHeight(Integer size, String text) {
		int greaterHeight = 0;
		for(byte letter : text.getBytes()) {
			BoundingBox letterBox = boxes.get(letter);
			int height = letterBox.getHeight();
			if(height > greaterHeight) {
				greaterHeight = height;
			}
		}
		return (int) Math.round(greaterHeight * size / 1000.0f);
	}
	
	public static Font getFont(String name) {
		return fonts.get(name);
	}
}
