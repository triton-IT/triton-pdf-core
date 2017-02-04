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
package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.document.Renderable;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;

/**
 * Internal class to write data to the content-stream section of PDF.
 * 
 * @author Régis Ramillien
 */
public class ContentStream implements PdfObject {
	/**
	 * The identifier of this object in the document.
	 */
	protected int id;
	/**
	 * The stream to output to PDF.
	 */
	protected StringBuilder streamBuilder = new StringBuilder();
	
	/**
	 * Creates a content stream with the given identifier.
	 * 
	 * @param id The identifier of content stream.
	 */
	public ContentStream(int id) {
		this.id = id;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {		
		streamBuilder.insert(0, id + " 0 obj <<" + LINE_SEPARATOR
				+ "/Length " + streamBuilder.length() + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "stream" + LINE_SEPARATOR);

		streamBuilder.append("endstream" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR);
		
		String asString = streamBuilder.toString();
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
	 * Add a renderable to content stream.
	 * 
	 * @param renderable The renderable object to add.
	 */
	public void add(Renderable renderable) {
		renderable.render(streamBuilder);
	}
}
