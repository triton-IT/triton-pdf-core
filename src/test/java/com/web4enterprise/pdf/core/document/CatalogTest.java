package com.web4enterprise.pdf.core.document;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

/**
 * Unit test class for Catalog.
 * 
 * 
 * @author Régis Ramillien
 */
@RunWith(MockitoJUnitRunner.class)
public class CatalogTest {	
	/**
	 * Catalog is a constant string and text should always be the same.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testWrite() throws Exception {
		Catalog catalog = new Catalog();
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			int length = catalog.write(outputStream);
			
			byte[] expected = ("1 0 obj <<\n"
					+ "/Type /Catalog\n"
					+ "/Pages 2 0 R\n"
					+ ">>\n"
					+ "endobj\n").getBytes();
			assertArrayEquals("Catalog output stream",  expected, outputStream.toByteArray());
			assertEquals("Catalog length", expected.length, length);
		}
	}
	
	/**
	 * Catalog is always first object and id musts be 1.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testGetId() throws IOException, PdfGenerationException {
		Catalog catalog = new Catalog();
		int id = catalog.getId();
		assertEquals("Catalog id", 1, id);
	}
	
	/**
	 * When catalog cannot be written, a PDFGenerationException should be thrown.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test(expected = IOException.class)
	public void testWriteWithIOException() throws Exception {
		Catalog catalog = new Catalog();
		try(ByteArrayOutputStream outputStream = mock(ByteArrayOutputStream.class)) {			
			doThrow(IOException.class).when(outputStream).write(any(byte[].class));
			
			catalog.write(outputStream);
		}
	}
}
