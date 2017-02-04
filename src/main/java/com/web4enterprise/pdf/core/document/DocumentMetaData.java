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

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

/**
 * The meta-data available for PDF.
 * 
 * 
 * @author Régis Ramillien
 */
public class DocumentMetaData implements PdfObject {
	/**
	 * The title of document.
	 */
	protected String title;
	/**
	 * The author of document.
	 */
	protected String author;
	/**
	 * The subject of document.
	 */
	protected String subject;
	/**
	 * The keywords of document.
	 */
	protected List<String> keywords = new ArrayList<>();
	/**
	 * The creator of document.
	 */
	protected String creator = "http://simplypdf-core.web4enterprise.com";
	/**
	 * The producer of document.
	 */
	protected String producer;
	/**
	 * The creation date of document.
	 */
	protected Date creationDate = new Date();
	/**
	 * The modification date of document.
	 */
	protected Date modificationDate;
	/**
	 * The custom meta-data to add to document
	 */
	protected List<MetaData> customs = new ArrayList<>();
	
	/**
	 * The date format in PDF document.
	 */
	protected DateFormat dateFormat = new SimpleDateFormat("'D':YYYYMMddHHmmssZ");
	
	/**
	 * The identifier of the object.
	 */
	protected int id;
	
	/**
	 * Construct a DocumentMetaData with the given identifier.
	 * 
	 * @param id The identifier this object.
	 */
	public DocumentMetaData(int id) {
		this.id = id;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR);

		writeMetadata(builder, "Title", title);
		writeMetadata(builder, "Author", author);
		writeMetadata(builder, "Subject", subject);
		
		if(!keywords.isEmpty()) {
			builder.append("/Keywords (");
			for(String keyword : keywords) {
				builder.append(keyword + " ");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(")");
			builder.append(LINE_SEPARATOR);
		}

		writeMetadata(builder, "Creator", creator);
		writeMetadata(builder, "Producer", producer);

		if(creationDate != null) {
			String date = dateFormat.format(creationDate);
			if(!date.endsWith("Z")) {
				//Correct format
				date = date.substring(0, 19) + "'" + date.substring(19, date.length());
				date = date.substring(0, 22) + "'" + date.substring(22, date.length());
			}
			writeMetadata(builder, "CreationDate", date);
		}
		
		if(modificationDate != null) {
			String date = dateFormat.format(modificationDate);
			if(!date.endsWith("Z")) {
				//Correct format.
				date = date.substring(0, 19) + "'" + date.substring(19, date.length());
				date = date.substring(0, 22) + "'" + date.substring(22, date.length());
			}
			writeMetadata(builder, "ModDate", date);
		}
		
		if(!customs.isEmpty()) {
			for(MetaData metaData : customs) {
				writeMetadata(builder, metaData.key, metaData.value);
			}
		}
		
		builder.append(">>").append(LINE_SEPARATOR)
		.append("endobj").append(LINE_SEPARATOR);
		
		String asString = builder.toString();
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}
		
		return asString.length();
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	/**
	 * Append a meta-date to builder.
	 * 
	 * @param builder The builder to add meta-data to.
	 * @param name The name of meta-data.
	 * @param value The value of meta-data.
	 */
	protected void writeMetadata(StringBuilder builder, String name, String value) {
		if(value != null) {
			builder.append("/").append(name).append(" (").append(value).append(")").append(LINE_SEPARATOR);
		}
	}
}
