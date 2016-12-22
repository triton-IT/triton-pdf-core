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
package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.Renderable;
import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.styling.Color;
import com.web4enterprise.pdf.core.text.Text;

/**
 * Class representing a page and its content in PDF.
 * 
 * @author Régis Ramillien
 */
public class Page implements PdfObject, PageNode {	
	/**
	 * The content stream needed for PDF format.
	 */
	protected ContentStream contentStream;
	/**
	 * The identifier of page in PDF.
	 */
	protected int id;
	/**
	 * The identifier of parent in PDF. 
	 */
	protected int parentId;
	/**
	 * The width of the page.
	 */
	protected int width;
	/**
	 * The height of the page.
	 */
	protected int height;
	/**
	 * The list of Renderables to render.
	 */
	protected List<Renderable> renderables = new ArrayList<>();
	
	/**
	 * Create a page in PDF.
	 * 
	 * @param parentId The parent object identifier of the page.
	 * @param id The page object identifier.
	 * @param contentStream The content stream of the page.
	 * @param width The width of the page.
	 * @param height The height of the page.
	 */
	public Page(int parentId, int id, ContentStream contentStream, int width, int height) {
		this.parentId = parentId;
		this.id = id;
		this.contentStream = contentStream;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		List<LinkAnnotation> links = new ArrayList<>();
		for(Renderable renderable : renderables) {
			contentStream.add(renderable);
			
			Renderable destination = renderable.getLink();
			if(destination != null) {
				links.add(new LinkAnnotation(destination.getPage(), destination.getLinkX(), destination.getLinkY(), destination.getLinkZ(), renderable.getBoundingBox()));
			}
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR)
				.append("/Type /Page").append(LINE_SEPARATOR)
				.append("/Parent ").append(parentId).append(" 0 R").append(LINE_SEPARATOR)
				.append("/Contents ").append(contentStream.getId()).append(" 0 R").append(LINE_SEPARATOR)
				.append("/Annots [").append(LINE_SEPARATOR);
		
		for(LinkAnnotation link : links) {
			stringBuilder.append("<<").append(LINE_SEPARATOR)
				.append("/Type /Annot").append(LINE_SEPARATOR)
			    .append("/Subtype /Link").append(LINE_SEPARATOR)
			    .append("/Border [0 0 0]").append(LINE_SEPARATOR)
				.append("/Rect [").append(link.getSourceRect().getLeft()).append(" ")
				.append(link.getSourceRect().getTop()).append(" ")
				.append(link.getSourceRect().getRight()).append(" ")
				.append(link.getSourceRect().getBottom()).append("]").append(LINE_SEPARATOR)
				.append("/A [").append(link.getDestinationPage()).append(" 0 R /XYZ ")
				.append(link.getDestinationX()).append(" ")
				.append(link.getDestinationY()).append(" ")
				.append(link.getDestinationZ()).append("]").append(LINE_SEPARATOR)
				.append(">>").append(LINE_SEPARATOR);
		}
		stringBuilder.append("]")
				.append(">>").append(LINE_SEPARATOR)
			    .append("endobj").append(LINE_SEPARATOR);
		
		String asString = stringBuilder.toString();
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
	 * Add a text to the page.
	 * 
	 * @param x The X position of the text in the page.
	 * @param y The Y position of the text in the page.
	 * @param size The size of the text to add.
	 * @param text The text to add.
	 */
	public void addText(float x, float y, int size, String text) {
		add(new Text(x, y, size, text));
	}
	
	/**
	 * Add a text to the page.
	 * 
	 * @param x The X position of the text in the page.
	 * @param y The Y position of the text in the page.
	 * @param size The size of the text to add.
	 * @param fontVariant The font variant of the text.
	 * @param text The text to add.
	 */
	public void addText(float x, float y, int size, FontVariant fontVariant, String text) {
		add(new Text(x, y, size, fontVariant, text));
	}
	
	/**
	 * Add a text to the page.
	 * 
	 * @param x The X position of the text in the page.
	 * @param y The Y position of the text in the page.
	 * @param size The size of the text to add.
	 * @param fontVariant The font variant of the text.
	 * @param color The color of the text.
	 * @param text The text to add.
	 */
	public void addText(float x, float y, int size, FontVariant fontVariant, Color color, String text) {
		add(new Text(x, y, size, fontVariant, color, text));
	}
	
	/**
	 * Add a Renderable to the page.
	 * 
	 * @param renderable The addRenderable to add in the page.
	 */
	public void add(Renderable renderable) {
		renderables.add(renderable);
		renderable.setPage(getId());
	}

	/**
	 * Get the width of the page.
	 * 
	 * @return The width of the page.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of the page.
	 * 
	 * @return The height of the page.
	 */
	public int getHeight() {
		return height;
	}
}
