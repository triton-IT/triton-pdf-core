/*
 * Copyright 2017 web4enterprise.
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
	/**
	 * The Courier font from base 14 AFM Fonts.
	 */
	public static final Font COURIER;
	/**
	 * The Helvetica font from base 14 AFM Fonts.
	 */
	public static final Font HELVTICA;
	/**
	 * The Symbol font from base 14 AFM Fonts.
	 */
	public static final Font SYMBOL;
	/**
	 * The Times-Roman font from base 14 AFM Fonts.
	 */
	public static final Font TIMES_ROMAN;
	/**
	 * The Zapf-Dingbats font from base 14 AFM Fonts.
	 */
	public static final Font ZAPF_DINGBATS;
	
	/**
	 * Initializer for fonts.
	 */
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
	
	/**
	 * Map of loaded fonts by name.
	 */
	protected static Map<String, Font> fonts = new HashMap<>();
	
	static {
		fonts.put("Courier", COURIER);
		fonts.put("Helvetica", HELVTICA);
		fonts.put("Symbol", SYMBOL);
		fonts.put("Times-Roman", TIMES_ROMAN);
		fonts.put("ZapfDingbats", ZAPF_DINGBATS);
	}

	/**
	 * The "plain" variant for this font.
	 */
	protected FontVariant plain;
	/**
	 * The "bold" variant for this font.
	 */
	protected FontVariant bold;
	/**
	 * The "italic" variant for this font.
	 */
	protected FontVariant italic;
	/**
	 * The "bold and italic" variant for this font.
	 */
	protected FontVariant boldItalic;
	
	/**
	 * Create a font with only the "plain" variant.
	 * @param plain The plain variant of font.
	 */
	public Font(FontVariant plain) {
		this.plain = plain;
	}
	
	/**
	 * Construct a font with all variants.
	 * 
	 * @param plain The plain variant of font.
	 * @param bold The bold variant of font.
	 * @param italic The italic variant of font.
	 * @param boldItalic The bold and italic variant of font.
	 */
	public Font(FontVariant plain, FontVariant bold, FontVariant italic, FontVariant boldItalic) {
		this.plain = plain;
		this.bold = bold;
		this.italic = italic;
		this.boldItalic = boldItalic;
	}
	
	/**
	 * Add a font to document.
	 * 
	 * @param font The font to add.
	 */
	public static void addFont(Font font) {
		fonts.put(font.getVariant(FontsVariant.PLAIN).getName(), font);
	}
	
	/**
	 * Get font by name.
	 * @param name The name of the font.
	 * @return The font.
	 */
	public static Font getFont(String name) {
		return fonts.get(name);
	}
	
	/**
	 * Get the width of a String with the given font, variant and size.
	 * 
	 * @param variant The variant to calculate width for.
	 * @param size The size to calculate width for.
	 * @param string The String to calculate width for.
	 * @return The length of String.
	 */
	public float getWidth(FontsVariant variant, Float size, String string) {
		return getVariant(variant).getWidth(size, string);
	}
	
	/**
	 * Get a variant of the font.
	 * 
	 * @param variant The variant to get.
	 * @return The font variant found or null.
	 */
	public FontVariant getVariant(FontsVariant variant) {
		switch (variant) {
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
