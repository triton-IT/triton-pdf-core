package com.web4enterprise.pdf.core.font;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class FontVariantTest {
	@After
	public void tearDown() {
		//Because underlineThickness is modified during tests, set it to correct value after.
		Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN).setUnderlineThickness(1000, 50);
	}
	
	@Test
	public void testGetWidthForSingleCharacter() {
		int actual = Font.TIMES_ROMAN.plain.getWidth(12, "a");
		//Gotten from Times-Roman.afm, the width of letter 'a' should be (444 / 1000) * font size.
		int expected = (int) ((444.0f / 1000.0f) * 12.0f);
		
		Assert.assertEquals("String widths should be equal.", expected, actual);
	}
	
	@Test
	public void testGetWidthForMultipleCharacters() {
		int actual = Font.TIMES_ROMAN.plain.getWidth(12, "ab");
		//Gotten from Times-Roman.afm, the width of letters 'ab' should be ((444 + 500) / 1000) * font size.
		int expected = (int) (((444.0f + 500.0f) / 1000.0f) * 12.0f);
		
		Assert.assertEquals("String widths should be equal.", expected, actual);
	}
	
	@Test
	public void testGetWidthForMultipleCharactersWithKerning() {
		int actual = Font.TIMES_ROMAN.plain.getWidth(12, "V,");
		//Gotten from Times-Roman.afm, the width of letters 'V,' should be ((444 + 500 - 129) / 1000) * font size.
		int expected = Math.round(((444.0f + 500.0f - 129.0f) / 1000.0f) * 12.0f);
		
		Assert.assertEquals("String widths should be equal.", expected, actual);
	}
	
	@Test
	public void testGetUnderlineThickness() {
		FontVariant variant = Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN);
		float thickness = variant.getUnderlineThickness(1);
		Assert.assertEquals("Thickness should not be lower or greater than 1", 0.05f, thickness, 0.01f);

		thickness = variant.getUnderlineThickness(1000);
		Assert.assertEquals("Thickness", 50.0f, thickness, 0.0f);
	}
	
	@Test
	public void testSetUnderlineThickness() {
		FontVariant variant = Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN);
		
		variant.setUnderlineThickness(12, 50);
		float thickness = variant.getUnderlineThickness(12);
		
		Assert.assertEquals("Thickness", 50.0f, thickness, 0.01f);
	}
}
