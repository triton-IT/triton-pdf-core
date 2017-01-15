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
package com.web4enterprise.pdf.core.text;

import org.junit.Assert;
import org.junit.Test;

import com.web4enterprise.pdf.core.styling.Color;

public class TextTest {
	@Test
	public void testGetUnderlineColorTestShouldEqualsTextColor() {
		Text text = new Text(0, 0, 12, "text");
		Color expectedColor = new Color(128, 80, 120);
		text.setColor(expectedColor);
		Color actualColor = text.getUnderlineColor();
		
		Assert.assertEquals("Text color and underline color should be the sames", expectedColor, actualColor);
	}
	
	@Test
	public void testGetUnderlineColor() {
		Text text = new Text(0, 0, 12, "text");
		
		Color textColor = new Color(128, 80, 120);
		text.setColor(textColor);
		
		Color expectedColor = new Color(128, 80, 120);
		text.setUnderlineColor(expectedColor);
		
		Color actualColor = text.getUnderlineColor();
		
		Assert.assertEquals("Underline color should not be the same as text color", expectedColor, actualColor);
	}
}
