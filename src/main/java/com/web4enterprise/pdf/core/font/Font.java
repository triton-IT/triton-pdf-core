package com.web4enterprise.pdf.core.font;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.web4enterprise.pdf.core.exceptions.ConfigurationException;

/**
 * Class representing a font with its variants (plain, bold, italic and bold/italic).
 * Contains also the 14 base fonts that must be present in every PDF readers.
 * 
 * @author Régis Ramillien
 */
public class Font {	
	public static final Font COURIER;
	public static final Font HELVTICA;
	public static final Font SYMBOL;
	public static final Font TIMES_ROMAN;
	public static final Font ZAPF_DINGBATS;
	static {
		try {
			COURIER = new Font(AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Courier.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Courier-Bold.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Courier-Oblique.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Courier-BoldOblique.afm")));
		} catch (IOException e) {
			throw new ConfigurationException("Unable to load font Courier.", e);
		}
		try {
			HELVTICA = new Font(AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Helvetica.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Helvetica-Bold.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Helvetica-Oblique.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Helvetica-BoldOblique.afm")));
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Unable to load font Helvetica.", e);
		}
		try {
			SYMBOL = new Font(AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Symbol.afm")));
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Unable to load font Symbol.", e);
		}
		try {
			TIMES_ROMAN = new Font(AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Times-Roman.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Times-Bold.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Times-Italic.afm")), 
					AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/Times-BoldItalic.afm")));
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Unable to load font Times-Roman.", e);
		}
		try {
			ZAPF_DINGBATS = new Font(AfmLoader.load(Font.class.getResourceAsStream("/fonts/base14/ZapfDingbats.afm")));
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Unable to load font Zapf-Dingbats.", e);
		}
	}
	
	protected static Map<String, Font> fonts = new HashMap<>();
	
	static {
		fonts.put("Times-Roman", TIMES_ROMAN);
	}

	protected FontVariant plain;
	protected FontVariant bold;
	protected FontVariant italic;
	protected FontVariant boldItalic;
	
	public Font(FontVariant plain) {
		this.plain = plain;
	}
	
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
