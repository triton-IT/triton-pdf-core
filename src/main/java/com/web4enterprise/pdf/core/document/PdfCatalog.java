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

/**
 * Defines the list of PDF objects.
 * 
 * 
 * @author Régis Ramillien
 */
public class PdfCatalog implements PdfObject {
	/**
	 * The id of catalog as string.
	 */
	private String id = String.valueOf(getId());
	
	@Override
	public int write(OutputStream stream) throws IOException {
		String asString = id + " 0 obj <<" + LINE_SEPARATOR
		 		+ "/Type /Catalog" + LINE_SEPARATOR
				+ "/Pages 2 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
		stream.write(asString.getBytes());
		
		return asString.length();
	}
	
	@Override
	public int getId() {
		return 1;
	}
}
