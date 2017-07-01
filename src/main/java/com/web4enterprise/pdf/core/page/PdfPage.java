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
import com.web4enterprise.report.commons.document.Renderable;
import com.web4enterprise.report.commons.link.Linkable;
import com.web4enterprise.report.commons.page.Page;

/**
 * Class representing a page and its content in PDF.
 * 
 * @author Régis Ramillien
 */
public class PdfPage extends Page implements PdfObject, PdfPageNode {
	/**
	 * The no zoom factor.
	 */
	private static final float NO_ZOOM = 0.0f;
	
	/**
	 * The content stream needed for PDF format.
	 */
	protected PdfContentStream contentStream;
	/**
	 * The identifier of page in PDF.
	 */
	protected int id;
	/**
	 * The identifier of parent in PDF. 
	 */
	protected int parentId;
	
	/**
	 * Create a page in PDF.
	 * 
	 * @param parentId The parent object identifier of the page.
	 * @param id The page object identifier.
	 * @param contentStream The content stream of the page.
	 * @param width The width of the page.
	 * @param height The height of the page.
	 */
	public PdfPage(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int write(OutputStream stream) throws IOException {
		List<PdfLinkAnnotation> links = new ArrayList<>();
		for(Renderable renderable : renderables) {
			contentStream.add(renderable);
			
			Linkable destination = renderable.getLink();
			if(destination != null) {
				links.add(new PdfLinkAnnotation(destination.getPage(), destination.getLinkX(), destination.getLinkY(), NO_ZOOM, renderable.getBoundingBox()));
			}
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR)
				.append("/Type /Page").append(LINE_SEPARATOR)
				.append("/Parent ").append(parentId).append(" 0 R").append(LINE_SEPARATOR)
				.append("/Contents ").append(contentStream.getId()).append(" 0 R").append(LINE_SEPARATOR)
				.append("/Annots [").append(LINE_SEPARATOR);
		
		for(PdfLinkAnnotation link : links) {
			stringBuilder.append("<<").append(LINE_SEPARATOR)
				.append("/Type /Annot").append(LINE_SEPARATOR)
			    .append("/Subtype /Link").append(LINE_SEPARATOR)
			    .append("/Border [0 0 0]").append(LINE_SEPARATOR)
				.append("/Rect [").append(link.getSourceRect().getLeft()).append(" ")
				.append(link.getSourceRect().getTop()).append(" ")
				.append(link.getSourceRect().getRight()).append(" ")
				.append(link.getSourceRect().getBottom()).append("]").append(LINE_SEPARATOR)
				.append("/A [").append(((PdfPage) link.getDestinationPage()).getId()).append(" 0 R /XYZ ")
				.append(link.getDestinationX()).append(" ")
				.append(link.getDestinationY()).append(" ")
				.append(link.getDestinationZ()).append("]").append(LINE_SEPARATOR)
				.append(">>").append(LINE_SEPARATOR);
		}
		stringBuilder.append("]")
				.append(">>").append(LINE_SEPARATOR)
			    .append("endobj").append(LINE_SEPARATOR);
		
		String asString = stringBuilder.toString();
		stream.write(asString.getBytes());
		
		return asString.length();
	}
	
	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PdfContentStream getContentStream() {
		return contentStream;
	}

	public void setContentStream(PdfContentStream contentStream) {
		this.contentStream = contentStream;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
