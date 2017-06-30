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
package com.web4enterprise.pdf.core.document;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Locale;

import org.junit.Test;

import com.web4enterprise.pdf.core.image.PdfImage;
import com.web4enterprise.pdf.core.page.PdfRootPageTree;
import com.web4enterprise.report.commons.exception.DocumentGenerationException;

public class PdfTest {
	/**
	 * A new PDf must instantiate a catalog, root page tree and document meta-data.
	 */
	@Test
	public void testConstructor() {
		Pdf pdf = new Pdf();
		assertEquals("Indirect objects length", 3, pdf.indirectsObjects.size());
		assertTrue("Catalog", pdf.indirectsObjects.get(0) instanceof PdfCatalog);
		assertEquals("Catalog id", 1, pdf.indirectsObjects.get(0).getId());
		assertTrue("Root page tree", pdf.indirectsObjects.get(1) instanceof PdfRootPageTree);
		assertEquals("Root page tree id", 2, pdf.indirectsObjects.get(1).getId());
		assertTrue("Document meta-data", pdf.indirectsObjects.get(2) instanceof PdfMetaData);
		assertEquals("Document meta-data id", 3, pdf.indirectsObjects.get(2).getId());
	}
	
	/**
	 * All meta-data are wrapped in a PDF object.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testSetMetaData() throws Exception {
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();
			
			pdf.write(outputStream);
			String actual = outputStream.toString();
			
			//Only presence of field is tested, content is tested in DocumentMetaDataTest.
			assertThat(actual, containsString("/Creator "));
			assertThat(actual, containsString("/CreationDate (D:"));
		}
		
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();
			pdf.setTitle("simplyPDF-core documentation");
			pdf.setAuthor("Regis Ramillien");
			pdf.setSubject("documentation for simplyPDF-core library");
			pdf.setCreator("http://simplypdf-core.web4enterprise.com - tests");
			pdf.setProducer("web4enterprise");
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.ENGLISH);
			pdf.setCreationDate(dateFormat.parse("Monday, December 25, 2016 10:39:45 PM CET"));
			pdf.setModificationDate(dateFormat.parse("Monday, December 26, 2016 10:39:46 PM CET"));
			
			pdf.write(outputStream);
			String actual = outputStream.toString();
			
			assertThat(actual, containsString("/Title (simplyPDF-core documentation)"));
			assertThat(actual, containsString("/Author (Regis Ramillien)"));
			assertThat(actual, containsString("/Subject (documentation for simplyPDF-core library)"));
			assertThat(actual, containsString("/Creator (http://simplypdf-core.web4enterprise.com - tests)"));
			assertThat(actual, containsString("/Producer (web4enterprise)"));
			assertThat(actual, containsString("/CreationDate (D:20161225"));
			assertThat(actual, containsString("/ModDate (D:20161226"));
		}
	}
	
	/**
	 * PDF must allow to create keywords.
	 * 
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testAddKeyword() throws Exception {
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();

			pdf.addKeyword("http://web4enterprise.com");
			pdf.addKeyword("simplyPDF-core");
			pdf.addKeyword("Documentation");
			
			pdf.write(outputStream);
			String actual = outputStream.toString();

			assertThat(actual, containsString("/Keywords (http://web4enterprise.com simplyPDF-core Documentation)"));
		}
	}
	
	/**
	 * PDF must allow to add meta-data.
	 * 
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testAddMetadata() throws Exception {
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();

			pdf.addMetaData("Customer-specific", "meta-data");
			
			pdf.write(outputStream);
			String actual = outputStream.toString();

			assertThat(actual, containsString("/Customer-specific (meta-data)"));
		}
	}
	
	/**
	 * When PDF cannot be written, a PDFGenerationException should be thrown.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test(expected = DocumentGenerationException.class)
	public void testWriteWithIOException() throws Exception {
		Pdf pdf = new Pdf();
		try(ByteArrayOutputStream outputStream = mock(ByteArrayOutputStream.class)) {			
			doThrow(IOException.class).when(outputStream).write(any(byte[].class));
			
			pdf.write(outputStream);
		}
	}
	
	/**
	 * PDF must allow to create new pages.
	 * 
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testCreatePage() throws Exception {
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();
			pdf.createPage(100, 80);
			pdf.createPage(180, 120);
			
			pdf.write(outputStream);
			String actual = outputStream.toString();

			assertThat(actual, containsString("/MediaBox [0 0 100 80]"));
			assertThat(actual, containsString("/MediaBox [0 0 180 120]"));
			assertThat(actual, containsString("/Type /Page"));
		}
	}

	/**
	 * PDF must allow to create images.
	 * 
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testCreateImage() throws Exception {
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			Pdf pdf = new Pdf();
			pdf.createPage(100, 80);
			pdf.createImage(getClass().getResourceAsStream("/logo.png"));
			
			pdf.write(outputStream);
			String actual = outputStream.toString();

			assertThat(actual, containsString("/Length 9737"));
			assertThat(actual, containsString("/Type /XObject"));
			assertThat(actual, containsString("/Subtype /Image"));
			assertThat(actual, containsString("/Filter /FlateDecode"));
			assertThat(actual, containsString("/BitsPerComponent 8"));
			assertThat(actual, containsString("/Width 744"));
			assertThat(actual, containsString("/Height 1052"));
			assertThat(actual, containsString("/ColorSpace /DeviceRGB"));
		}
	}
	
	/**
	 * When image cannot be created, a PDFGenerationException should be thrown.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test(expected = DocumentGenerationException.class)
	public void testCreateImageWithIOException() throws Exception {
		try(InputStream inputStream = this.getClass().getResourceAsStream("/logo.png")) {
			Pdf pdf = new Pdf();
			pdf.createPage(100, 80);
			
			//It could be better to mock InputStream, but can't make it throw exception...
			pdf.rootPageTree = mock(PdfRootPageTree.class);
			doThrow(IOException.class).when(pdf.rootPageTree).addImage(any(PdfImage.class));
			
			pdf.createImage(inputStream);
		}
	}
	
	/**
	 * Clear must reset indirectObjets and but not catalog, root page tree or document meta-data.
	 * After a clear, an image should be re-bound.
	 * 
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testClearAndRebind() throws Exception {
		Pdf pdf = new Pdf();
		PdfImage image = pdf.createImage(this.getClass().getResourceAsStream("/logo.png"));
		assertTrue("Image should be present", pdf.indirectsObjects.get(3) instanceof PdfImage);
		
		pdf.clear();
		assertTrue("Catalog should be present", pdf.indirectsObjects.get(0) instanceof PdfCatalog);
		assertTrue("Root page tree should be present", pdf.indirectsObjects.get(1) instanceof PdfRootPageTree);
		assertTrue("Document meta-data should be present", pdf.indirectsObjects.get(2) instanceof PdfMetaData);
		assertEquals("pdf must be re-set", 3, pdf.indirectsObjects.size());
		
		pdf.rebindImage(image);
		assertEquals("Image must be re-bound", 4, pdf.indirectsObjects.size());
		assertTrue("Image should be present", pdf.indirectsObjects.get(3) instanceof PdfImage);
	}
}
