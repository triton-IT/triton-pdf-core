package com.web4enterprise.pdf.core.document;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test class for Catalog.
 * 
 * 
 * @author Régis Ramillien
 */
public class MetaDataTest {
	/**
	 * Constructor sets a key and a value.
	 */
	@Test
	public void testConstructor() {
		MetaData metaData = new MetaData("key", "value");
		assertEquals("key", "key", metaData.key);
		assertEquals("value", "value", metaData.value);
	}
}
