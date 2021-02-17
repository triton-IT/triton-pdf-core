/*
 * Copyright 2021 tritonit.tech.
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
package tech.tritonit.pdf.core.font;

import tech.tritonit.pdf.core.document.PdfContext;

/**
 * Class representing a font with its variants (plain, bold, italic and bold/italic).
 * Contains also the 14 base fonts that must be present in every PDF readers.
 * 
 * @author Régis Ramillien
 */
public class Font {

	public static final String COURIER = "courier";
	public static final String HELVETICA = "helvetica";
	public static final String SYMBOL = "symbol";
	public static final String TIMES_ROMAN = "times-roman";
	public static final String ZAPF_DINGBATS = "zapf-Dingbats";

	/**
	 * The context of the PDF document this font belongs to.
	 */
	protected final PdfContext context;

	/**
	 * The "plain" variant for this font.
	 */
	protected final FontVariant plain;
	/**
	 * The "bold" variant for this font.
	 */
	protected final FontVariant bold;
	/**
	 * The "italic" variant for this font.
	 */
	protected final FontVariant italic;
	/**
	 * The "bold and italic" variant for this font.
	 */
	protected final FontVariant boldItalic;
	
	/**
	 * Create a font with only the "plain" variant.
	 * @param plain The plain variant of font.
	 */
	public Font(PdfContext context, FontVariant plain) {
		this(context, plain, null, null, null);
	}
	
	/**
	 * Construct a font with all variants.
	 * 
	 * @param plain The plain variant of font.
	 * @param bold The bold variant of font.
	 * @param italic The italic variant of font.
	 * @param boldItalic The bold and italic variant of font.
	 */
	public Font(PdfContext context, FontVariant plain, FontVariant bold, FontVariant italic, FontVariant boldItalic) {
		this.context = context;

		this.plain = plain;
		this.bold = bold;
		this.italic = italic;
		this.boldItalic = boldItalic;
	}
	
	/**
	 * Get the width of a String with the given font, variant and size.
	 * 
	 * @param variant The variant to calculate width for.
	 * @param size The size to calculate width for.
	 * @param string The String to calculate width for.
	 * @return The length of String.
	 */
	public float getWidth(FontsVariant variant, Integer size, String string) {
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
