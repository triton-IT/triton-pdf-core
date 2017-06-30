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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.web4enterprise.report.commons.document.MetaData;

/**
 * Unit test class for Catalog.
 * 
 * 
 * @author Régis Ramillien
 */
@RunWith(MockitoJUnitRunner.class)
public class PdfMetaDataTest {
	/**
	 * Constructor sets an id accessible through a getter.
	 */
	@Test
	public void testConstructor() {
		PdfMetaData metaData = new PdfMetaData(99);
		assertEquals("id", 99, metaData.getId());
	}

	/**
	 * Test write of default meta-data.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testWriteDefaultMetadata() throws Exception {
		PdfMetaData metaData = new PdfMetaData(0);

		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			int length = metaData.write(outputStream);
			
			//Compute date format.
			String date = new SimpleDateFormat("YYYYMMddHHmmssXXX").format(metaData.creationDate);
			date = date.replace(':', '\'');
			date = date + "'";
			date = "D:" + date;
			
			byte[] expected = ("0 0 obj <<\n"
			+ "/Creator (http://simplypdf-core.web4enterprise.com)\n"
			+ "/CreationDate (" + date + ")\n"
			+ ">>\n"
			+ "endobj\n").getBytes();

			assertArrayEquals("DocumentMetaData output stream",  expected, outputStream.toByteArray());
			assertEquals("DocumentMetaData length", expected.length, length);
		}
	}

	/**
	 * Test write of custom meta-data.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test
	public void testWriteAllMetaData() throws Exception {
		PdfMetaData metaData = new PdfMetaData(0);
		metaData.title = "titleMetaData";
		metaData.author = "authorMetaData";
		metaData.subject = "subjectMetaData";
		metaData.keyWords = Arrays.asList("keyWord1", "keyWord2");
		metaData.creator = "creatorMetaData";
		metaData.producer = "producerMetaData";
		Date creationDate = new Date(0L);
		metaData.creationDate = creationDate;
		Date modificationDate = new Date(1L);
		metaData.modificationDate = modificationDate;
		metaData.customs = Arrays.asList(new MetaData("metaData1", "metaData1value"), new MetaData("metaData2", "metaData2value"));

		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			int length = metaData.write(outputStream);
			
			byte[] expected = ("0 0 obj <<\n"
			+ "/Title (titleMetaData)\n"
			+ "/Author (authorMetaData)\n"
			+ "/Subject (subjectMetaData)\n"
			+ "/Keywords (keyWord1 keyWord2)\n"
			+ "/Creator (creatorMetaData)\n"
			+ "/Producer (producerMetaData)\n"
			+ "/CreationDate (D:19700101010000+01'00')\n"
			+ "/ModDate (D:19700101010000+01'00')\n"
			+ "/metaData1 (metaData1value)\n"
			+ "/metaData2 (metaData2value)\n"
			+ ">>\n"
			+ "endobj\n").getBytes();

			assertArrayEquals("DocumentMetaData output stream",  expected, outputStream.toByteArray());
			assertEquals("DocumentMetaData length", expected.length, length);
		}
	}
	
	/**
	 * When document meta-data cannot be written, a PDFGenerationException should be thrown.
	 * 
	 * @throws Exception When something goes wrong.
	 */
	@Test(expected = IOException.class)
	public void testWriteWithIOException() throws Exception {
		PdfMetaData metaData = new PdfMetaData(0);
		try(ByteArrayOutputStream outputStream = mock(ByteArrayOutputStream.class)) {			
			doThrow(IOException.class).when(outputStream).write(any(byte[].class));
			
			metaData.write(outputStream);
		}
	}
}
