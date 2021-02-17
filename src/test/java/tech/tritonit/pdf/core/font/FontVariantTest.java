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

import org.junit.jupiter.api.Test;
import tech.tritonit.pdf.core.document.Pdf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.tritonit.pdf.core.font.Font.TIMES_ROMAN;
import static tech.tritonit.pdf.core.font.FontsVariant.PLAIN;

class FontVariantTest {
	@Test
	void testGetWidthForSingleCharacter() {
		Pdf pdf = new Pdf();

		float actual = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN).getWidth(12, "a");
		//Gotten from Times-Roman.afm, the width of letter 'a' should be ((444 / 1000) * font size) / (Unit ratio).
		float expected = ((444.0f / 1000.0f) * 12.0f) / (72.0f / 25.4f);
		
		assertEquals(expected, actual, 0.0f, "String widths should be equal.");
	}
	
	@Test
	void testGetWidthForMultipleCharacters() {
		Pdf pdf = new Pdf();

		float actual = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN).getWidth(12, "ab");
		//Gotten from Times-Roman.afm, the width of letters 'ab' should be (((444 + 500) / 1000) * font size) / (Unit ratio).
		float expected = (((444.0f + 500.0f) * 12.0f) / 1000.0f) / (72.0f / 25.4f);
		
		assertEquals(expected, actual, 0.0f, "String widths should be equal.");
	}
	
	@Test
	void testGetWidthForMultipleCharactersWithKerning() {
		Pdf pdf = new Pdf();

		float actual = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN).getWidth(12, "V,");
		//Gotten from Times-Roman.afm, the width of letters 'V,' should be (((722 + 250 - 129) / 1000) * font size) / (Unit ratio).
		float expected = (((722.0f + 250.0f - 129.0f) * 12.0f) / 1000.0f) / (72.0f / 25.4f);

		assertEquals(expected, actual, 0.0f, "String widths should be equal.");
	}
	
	@Test
	void testGetUnderlineThickness() {
		Pdf pdf = new Pdf();

		FontVariant variant = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN);
		float thickness = variant.getUnderlineThickness(1);
		assertEquals(0.05f, thickness, 0.01f, "Thickness should not be lower or greater than 1");

		thickness = variant.getUnderlineThickness(1000);
		assertEquals(50.0f, thickness, 0.0f, "Thickness");
	}
	
	@Test
	void testSetUnderlineThickness() {
		Pdf pdf = new Pdf();

		FontVariant variant = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN);
		
		variant.setUnderlineThickness(12, 50);
		float thickness = variant.getUnderlineThickness(12);
		
		assertEquals(50.0f, thickness, 0.01f, "Thickness");
	}
}
