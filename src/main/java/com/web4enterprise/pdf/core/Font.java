package com.web4enterprise.pdf.core;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

public class Font {
	protected static final FontRenderContext FONT_RENDER_CONTEXT = new FontRenderContext(new AffineTransform(), true, true);
	
	protected static Map<String, Map<Integer, java.awt.Font>> fontsByName = new HashMap<>();
	
	public static int calculateWidth(String name, Integer size, String text) {
		java.awt.Font font = getFont(name, size);
		return (int) Math.ceil(font.getStringBounds(text, FONT_RENDER_CONTEXT).getWidth());
	}
	
	public static int calculateHeight(String name, Integer size, String text) {	
		java.awt.Font font = getFont(name, size);
		return (int) Math.ceil(font.getStringBounds(text, FONT_RENDER_CONTEXT).getHeight());
	}
	
	protected static java.awt.Font getFont(String name, Integer size) {
		java.awt.Font font = null;
		
		Map<Integer, java.awt.Font> fontsBySize = fontsByName.get(name);
		
		if(fontsBySize == null) {
			fontsBySize = new HashMap<Integer, java.awt.Font>();
			font = new java.awt.Font(name, java.awt.Font.PLAIN, size);
			fontsBySize.put(size, font);
			fontsByName.put(name, fontsBySize);
		} else {
			font = fontsBySize.get(size);
			if(font == null) {
				font = new java.awt.Font(name, java.awt.Font.PLAIN, size);
				fontsBySize.put(size, font);
			}
		}
		
		return font;
	}
}
