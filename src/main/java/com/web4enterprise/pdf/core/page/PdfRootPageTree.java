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
import com.web4enterprise.pdf.core.image.PdfImage;
import com.web4enterprise.report.commons.font.FontCache;
import com.web4enterprise.report.commons.font.FontVariant;

/**
 * Represent the root page of a PDF document.
 * 
 * @author Régis Ramillien
 */
public class PdfRootPageTree implements PdfObject, PdfPageNode {
	/**
	 * The pages in the PDF.
	 */
	protected List<PdfPageNode> pageNodes = new ArrayList<>();
	/**
	 * The definition of images in the PDF.
	 */
	protected List<PdfImage> images = new ArrayList<>();
	/**
	 * The identifier of the object.
	 */
	protected int id;
	
	/**
	 * Construct a RootPageTree with the given identifier.
	 * 
	 * @param id The identifier this object.
	 */
	public PdfRootPageTree(int id) {
		this.id = id;
	}

	@Override
	public int write(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR)
		.append("/Type /Pages").append(LINE_SEPARATOR)
		.append("/Kids [").append(LINE_SEPARATOR);

		for(PdfPageNode pageNode : pageNodes) {
			builder.append(pageNode.getId()).append(" 0 R").append(LINE_SEPARATOR);
		}
		
		builder.append("]").append(LINE_SEPARATOR)
		.append("/Count ").append(pageNodes.size()).append(LINE_SEPARATOR)
		.append("/MediaBox [0 0 595 842]").append(LINE_SEPARATOR)
		.append("/Resources <<").append(LINE_SEPARATOR)
		.append("/Font <<").append(LINE_SEPARATOR)
		.append(embedFontVariant(FontCache.COURIER.getVariant(FontVariant.PLAIN).getName()))
		.append(embedFontVariant(FontCache.COURIER.getVariant(FontVariant.BOLD).getName()))
		.append(embedFontVariant(FontCache.COURIER.getVariant(FontVariant.ITALIC).getName()))
		.append(embedFontVariant(FontCache.COURIER.getVariant(FontVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(FontCache.HELVTICA.getVariant(FontVariant.PLAIN).getName()))
		.append(embedFontVariant(FontCache.HELVTICA.getVariant(FontVariant.BOLD).getName()))
		.append(embedFontVariant(FontCache.HELVTICA.getVariant(FontVariant.ITALIC).getName()))
		.append(embedFontVariant(FontCache.HELVTICA.getVariant(FontVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(FontCache.SYMBOL.getVariant(FontVariant.PLAIN).getName()))
		.append(embedFontVariant(FontCache.TIMES_ROMAN.getVariant(FontVariant.PLAIN).getName()))
		.append(embedFontVariant(FontCache.TIMES_ROMAN.getVariant(FontVariant.BOLD).getName()))
		.append(embedFontVariant(FontCache.TIMES_ROMAN.getVariant(FontVariant.ITALIC).getName()))
		.append(embedFontVariant(FontCache.TIMES_ROMAN.getVariant(FontVariant.BOLD_ITALIC).getName()))
		.append(embedFontVariant(FontCache.ZAPF_DINGBATS.getVariant(FontVariant.PLAIN).getName()))
		.append(">>").append(LINE_SEPARATOR);
		if(!images.isEmpty()) {
			for(PdfImage image : images) {
				builder.append("/XObject <<").append(LINE_SEPARATOR)
				.append("/image").append(image.getId()).append(" ").append(image.getId()).append(" 0 R").append(LINE_SEPARATOR)
				.append(">>").append(LINE_SEPARATOR);
			}
		}
		builder.append(">>").append(LINE_SEPARATOR)
		.append(">>").append(LINE_SEPARATOR)
		.append("endobj").append(LINE_SEPARATOR);
		
		String asString = builder.toString();
		stream.write(asString.getBytes());
		
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
	public void addPageNode(PdfPageNode pageNode) {
		pageNodes.add(pageNode);
	}
	
	/**
	 * Attach a page node to this object.
	 * 
	 * @param image The image to attach.
	 */
	public void addImage(PdfImage image) {
		images.add(image);
	}
	
	/**
	 * Remove all pages from this object.
	 */
	public void clearPages() {
		pageNodes.clear();
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