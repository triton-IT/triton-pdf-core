package com.web4enterprise.pdf.core.font;

import java.util.HashMap;
import java.util.Map;

public class Font {
	public static Font TIMES_ROMAN = new TimesRoman();
	
	protected static Map<String, Font> fonts = new HashMap<>();
	
	static {
		fonts.put("Times-Roman", TIMES_ROMAN);
	}

	protected FontVariant plain;
	protected FontVariant bold;
	protected FontVariant italic;
	protected FontVariant boldItalic;
	
	public Font(FontVariant plain, FontVariant bold, FontVariant italic, FontVariant boldItalic) {
		this.plain = plain;
		this.bold = bold;
		this.italic = italic;
		this.boldItalic = boldItalic;
	}
	
	public static Font getFont(String name) {
		return fonts.get(name);
	}
	
	public int getWidth(FontStyle style, Integer size, String string) {
		return getVariant(style).getWidth(size, string);
	}
	
	public int getHeight(FontStyle style, Integer size, String string) {
		return getVariant(style).getHeight(size, string);
	}
	
	public FontVariant getVariant(FontStyle style) {
		switch (style) {
		case BOLD:			
			return bold;
		case ITALIC:
			return italic;
		case BOLD_ITALIC:
			return boldItalic;
		default:
			return plain;
		}
	}
}
