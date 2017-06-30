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

/**
 * Represent a page tree in a PDF.
 * The page tree default styling for future pages. 
 * 
 * @author Régis Ramillien
 */
public class PdfPageTree extends PdfRootPageTree {
	/**
	 * Identifier of the parent of this page tree.
	 */
	protected int parent;
	/**
	 * Width of this page tree.
	 */
	protected int width;
	/**
	 * Height of this page tree.
	 */
	protected int height;
	
	/**
	 * Create a page tree in PDF.
	 * 
	 * @param parent The parent identifier of page tree object.
	 * @param id The page tre object identifier.
	 * @param width The width of the page tree.
	 * @param height The height of page tree.
	 */
	public PdfPageTree(int parent, int id, int width, int height) {
		super(id);
		this.parent = parent;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int write(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		builder.append(id + " 0 obj <<").append(LINE_SEPARATOR)
		.append("/Type /Pages").append(LINE_SEPARATOR)
		.append("/Parent 2 0 R").append(LINE_SEPARATOR)
		.append("/Kids [").append(LINE_SEPARATOR);
		
		for(PdfPageNode pageNode : pageNodes) {
			builder.append(pageNode.getId()).append(" 0 R").append(LINE_SEPARATOR);
		}
		
		builder.append("]").append(LINE_SEPARATOR)
		.append("/Count ").append(pageNodes.size()).append(LINE_SEPARATOR)
		.append("/MediaBox [0 0 ").append(width).append(" ").append(height+ "]").append(LINE_SEPARATOR)
		.append(">>").append(LINE_SEPARATOR)
		.append("endobj").append(LINE_SEPARATOR);
		
		String asString = builder.toString();
		stream.write(asString.getBytes());
		
		return asString.length();
	}
}
