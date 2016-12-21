/*
 * Copyright 2016 web4enterprise.
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
	protected DateFormat dateFormat = new SimpleDateFormat("'(D':YYYYMMddHHmmssZ')'");
	
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
		
		if(title != null) {
			builder.append("/Title (").append(title).append(")").append(LINE_SEPARATOR);
		}
		if(author != null) {
			builder.append("/Author (").append(author).append(")").append(LINE_SEPARATOR);
		}
		if(subject != null) {
			builder.append("/Subject (").append(subject).append(")").append(LINE_SEPARATOR);
		}
		if(keywords.size() > 0) {
			builder.append("/Keywords (");
			for(String keyword : keywords) {
				builder.append(keyword + " ");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(")");
			builder.append(LINE_SEPARATOR);
		}
		if(creator != null) {
			builder.append("/Creator (").append(creator).append(")").append(LINE_SEPARATOR);
		}
		if(producer != null) {
			builder.append("/Producer (").append(producer).append(")").append(LINE_SEPARATOR);
		}
		if(creationDate != null) {
			String date = dateFormat.format(creationDate);
			if(!date.endsWith("Z")) {
				//Correct format
				date = date.substring(0, 20) + "'" + date.substring(20, date.length());
				date = date.substring(0, 23) + "'" + date.substring(23, date.length());
			}
			builder.append("/CreationDate ").append(date).append(LINE_SEPARATOR);
		}
		if(modificationDate != null) {
			String date = dateFormat.format(modificationDate);
			if(!date.endsWith("Z")) {
				//Correct format
				date = date.substring(0, 20) + "'" + date.substring(20, date.length());
				date = date.substring(0, 23) + "'" + date.substring(23, date.length());
			}
			builder.append("/ModDate ").append(date).append(LINE_SEPARATOR);
		}
		if(customs.size() > 0) {
			for(MetaData metaData : customs) {
				builder.append("/").append(metaData.key).append(" (").append(metaData.value).append(")").append(LINE_SEPARATOR);
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
}
