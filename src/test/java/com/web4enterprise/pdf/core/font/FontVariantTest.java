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

import org.junit.Assert;
import org.junit.Test;

public class FontVariantTest {
	
	@Test
	public void testGetWidthForSingleCharacter() {
		float actual = Font.TIMES_ROMAN.plain.getWidth(12.0f, "a");
		//Gotten from Times-Roman.afm, the width of letter 'a' should be (444 / 1000) * font size.
		float expected = (444.0f * 12.0f) / 1000.0f;
		
		Assert.assertEquals("String widths should be equal.", expected, actual, 0.0f);
	}
	
	@Test
	public void testGetWidthForMultipleCharacters() {
		float actual = Font.TIMES_ROMAN.plain.getWidth(12.0f, "ab");
		//Gotten from Times-Roman.afm, the width of letters 'ab' should be ((444 + 500) / 1000) * font size.
		float expected = ((444.0f + 500.0f) / 1000.0f) * 12.0f;
		
		Assert.assertEquals("String widths should be equal.", expected, actual, 0.0f);
	}
	
	@Test
	public void testGetWidthForMultipleCharactersWithKerning() {
		float actual = Font.TIMES_ROMAN.plain.getWidth(12.0f, "V,");
		//Gotten from Times-Roman.afm, the width of letters 'V,' should be ((722 + 500 - 129) / 1000) * font size.
		float expected = ((722.0f + 250.0f - 129.0f) * 12.0f) / 1000.0f;
		
		Assert.assertEquals("String widths should be equal.", expected, actual, 0.0f);
	}
	
	@Test
	public void testGetUsnderlineThickness() {
		FontVariant variant = Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN);
		float thickness = variant.getUnderlineThickness(1);
		Assert.assertEquals("Thickness should not be lower or greater than 1", 0.05f, thickness, 0.01f);

		thickness = variant.getUnderlineThickness(1000);
		Assert.assertEquals("Thickness", 50.0f, thickness, 0.0f);
	}
}
