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
package tech.tritonit.pdf.core.text;

import org.junit.jupiter.api.Test;
import tech.tritonit.pdf.core.document.Pdf;
import tech.tritonit.pdf.core.font.FontVariant;
import tech.tritonit.pdf.core.styling.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tech.tritonit.pdf.core.font.Font.TIMES_ROMAN;
import static tech.tritonit.pdf.core.font.FontsVariant.PLAIN;

class TextTest {
	@Test
	void testGetUnderlineColorTestShouldEqualsTextColor() {
		Pdf pdf = new Pdf();

		FontVariant variant = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN);
		Text text = new Text(0, 0, 12, variant, "text");
		Color expectedColor = new Color(128, 80, 120);
		text.setColor(expectedColor);
		Color actualColor = text.getUnderlineColor();
		
		assertEquals(expectedColor, actualColor, "Text color and underline color should be the sames");
	}
	
	@Test
	void testGetUnderlineColor() {
		Pdf pdf = new Pdf();

		FontVariant variant = pdf.getFont(TIMES_ROMAN).getVariant(PLAIN);
		Text text = new Text(0, 0, 12, variant, "text");
		
		Color textColor = new Color(128, 80, 120);
		text.setColor(textColor);
		
		Color expectedColor = new Color(128, 80, 120);
		text.setUnderlineColor(expectedColor);
		
		Color actualColor = text.getUnderlineColor();
		
		assertEquals(expectedColor, actualColor, "Underline color should not be the same as text color");
	}
}
