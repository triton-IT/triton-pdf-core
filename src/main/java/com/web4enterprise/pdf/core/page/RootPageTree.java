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
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontsVariant;
import com.web4enterprise.pdf.core.image.Image;

/**
 * Represent the root page of a PDF document.
 * 
 * @author Régis Ramillien
 */
public class RootPageTree implements PdfObject, PageNode {
	/**
	 * The pages in the PDF.
	 */
	protected List<PageNode> pageNodes = new ArrayList<>();
	/**
	 * The definition of images in the PDF.
	 */
	protected List<Image> images = new ArrayList<>();
	/**
	 * The identifier of the object.
	 */
	protected int id;
	
	/**
	 * Construct a RootPageTree with the given identifier.
	 * 
	 * @param id The identifier this object.
	 */
	public RootPageTree(int id) {
		this.id = id;
	}

	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR)
		.append("/Type /Pages").append(LINE_SEPARATOR)
		.append("/Kids [").append(LINE_SEPARATOR);

		for(PageNode pageNode : pageNodes) {
			builder.append(pageNode.getId()).append(" 0 R").append(LINE_SEPARATOR);
		}
		
		builder.append("]").append(LINE_SEPARATOR)
		.append("/Count ").append(pageNodes.size()).append(LINE_SEPARATOR)
		.append("/MediaBox [0 0 595 842]").append(LINE_SEPARATOR)
		.append("/Resources <<").append(LINE_SEPARATOR)
		.append("/Font <<").append(LINE_SEPARATOR)
		.append(embedFontVariant(Font.COURIER.getVariant(FontsVariant.PLAIN).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontsVariant.BOLD).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontsVariant.ITALIC).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontsVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontsVariant.PLAIN).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontsVariant.BOLD).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontsVariant.ITALIC).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontsVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.SYMBOL.getVariant(FontsVariant.PLAIN).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontsVariant.PLAIN).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontsVariant.BOLD).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontsVariant.ITALIC).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontsVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.ZAPF_DINGBATS.getVariant(FontsVariant.PLAIN).getName()))
		.append(">>").append(LINE_SEPARATOR);
		if(!images.isEmpty()) {
			for(Image image : images) {
				builder.append("/XObject <<").append(LINE_SEPARATOR)
				.append("/image").append(image.getId()).append(" ").append(image.getId()).append(" 0 R").append(LINE_SEPARATOR)
				.append(">>").append(LINE_SEPARATOR);
			}
		}
		builder.append(">>").append(LINE_SEPARATOR)
		.append(">>").append(LINE_SEPARATOR)
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
	 * Attach a page node to this object.
	 * 
	 * @param pageNode The page node to attach.
	 */
	public void addPageNode(PageNode pageNode) {
		pageNodes.add(pageNode);
	}
	
	/**
	 * Attach a page node to this object.
	 * 
	 * @param image The image to attach.
	 */
	public void addImage(Image image) {
		images.add(image);
	}
	
	/**
	 * Write the definition item of a font variant in PDF format to a String.
	 * 
	 * @param fontVariant The font variant to write as String.
	 * @return A String representing a font definition item in PDF format.
	 */
	private String embedFontVariant(String fontVariant) {
		return "/" + fontVariant + " <<" + LINE_SEPARATOR
				+ "/Type /Font" + LINE_SEPARATOR
				+ "/Subtype /Type1" + LINE_SEPARATOR
				+ "/BaseFont /" + fontVariant + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR;
	}
}
