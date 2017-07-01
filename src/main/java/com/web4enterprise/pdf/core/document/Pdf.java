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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web4enterprise.pdf.core.image.PdfImage;
import com.web4enterprise.pdf.core.page.PdfContentStream;
import com.web4enterprise.pdf.core.page.PdfPage;
import com.web4enterprise.pdf.core.page.PdfPageTree;
import com.web4enterprise.pdf.core.page.PdfRootPageTree;
import com.web4enterprise.report.commons.document.Document;
import com.web4enterprise.report.commons.document.MetaData;
import com.web4enterprise.report.commons.exception.BadOperationException;
import com.web4enterprise.report.commons.exception.DocumentGenerationException;
import com.web4enterprise.report.commons.image.Image;
import com.web4enterprise.report.commons.page.Page;

/**
 * The PDF document.
 * 
 * 
 * @author Régis Ramillien
 */
public class Pdf implements Document {
	/**
	 * The line separator that will be used in this PDF.
	 */
	public static final String LINE_SEPARATOR = "\n";
	/**
	 * The list of indirect objects.
	 */
	protected List<PdfObject> indirectsObjects = new ArrayList<>();

	/**
	 * The list of bytes written before each indirect object.
	 */
	protected List<Integer> indirectsPositions = new ArrayList<>();

	/**
	 * The catalog of PDF (root page tree for now only).
	 */
	protected PdfCatalog catalog = new PdfCatalog();

	/**
	 * The meta-data of document.
	 */
	protected PdfMetaData documentMetaData;

	/**
	 * The root of pages tree.
	 */
	protected PdfRootPageTree rootPageTree;

	/**
	 * Instantiate a new empty PDF.
	 * 
	 * Please use {@link PdfElementFactory.class} to instantiate an new PDF.
	 */
	protected Pdf() {
		indirectsObjects.add(catalog);
		rootPageTree = new PdfRootPageTree(indirectsObjects.size() + 1);
		indirectsObjects.add(rootPageTree);
		documentMetaData = new PdfMetaData(indirectsObjects.size() + 1);
		indirectsObjects.add(documentMetaData);
	}

	/**
	 * Set the title of document.
	 * 
	 * @param title
	 *            The title of document meta-data.
	 */
	public void setTitle(String title) {
		documentMetaData.title = title;
	}

	/**
	 * Set the author of document.
	 * 
	 * @param author
	 *            The author of document meta-data.
	 */
	public void setAuthor(String author) {
		documentMetaData.author = author;
	}

	/**
	 * Set the subject of document.
	 * 
	 * @param subject
	 *            The subject of document meta-data.
	 */
	public void setSubject(String subject) {
		documentMetaData.subject = subject;
	}

	/**
	 * Add a keyword to document meta-data.
	 * 
	 * @param keyword
	 *            The keyword to add to document meta-data.
	 */
	public void addKeyword(String keyword) {
		documentMetaData.keyWords.add(keyword);
	}

	/**
	 * Set the creator of document.
	 * 
	 * @param creator
	 *            The creator of document meta-data.
	 */
	public void setCreator(String creator) {
		documentMetaData.creator = creator;
	}

	/**
	 * Set the producer of document.
	 * 
	 * @param producer
	 *            The producer of this document to set to meta-data.
	 */
	public void setProducer(String producer) {
		documentMetaData.producer = producer;
	}

	/**
	 * Set the create date of document.
	 * 
	 * @param creationDate
	 *            The date of creation of this document to set to meta-data.
	 */
	public void setCreationDate(Date creationDate) {
		documentMetaData.creationDate = creationDate;
	}

	/**
	 * Set the modification date of document.
	 * 
	 * @param modificationDate
	 *            The date of modification of this document to set to meta-data.
	 */
	public void setModificationDate(Date modificationDate) {
		documentMetaData.modificationDate = modificationDate;
	}

	/**
	 * Add a custom meta-data to document.
	 * 
	 * @param key
	 *            The key of custom meta-data to add to document.
	 * @param value
	 *            The value of custom meta-data to add to document.
	 */
	public void addMetaData(String key, String value) {
		documentMetaData.customs.add(new MetaData(key, value));
	}

	/**
	 * Write PDF to stream.
	 * 
	 * @param stream
	 *            The stream to write PDF to.
	 * @throws PdfGenerationException
	 *             When PDF cannot be generated.
	 */
	public void write(OutputStream stream) throws DocumentGenerationException {
		try {
			int position = writeHeader(stream);
			position += writeBody(stream, position);
			writeXRef(stream);
			writeTrailer(stream);
			writeStartXRef(stream, position);
			stream.write("%%EOF".getBytes());
		} catch (IOException e) {
			throw new DocumentGenerationException("Cannot write PDF.", e);
		}
	}

	@Override
	public void addPage(Page page) {
		if (!(page instanceof PdfPage)) {
			throw new BadOperationException("You must add an embeddable useable by this API.");
		}
		int pageTreeId = indirectsObjects.size() + 1;
		int pageId = pageTreeId + 1;
		int contentStreamId = pageId + 1;

		PdfPageTree pageTree = new PdfPageTree(rootPageTree.getId(), pageTreeId, page.getWidth(), page.getHeight());
		PdfContentStream contentStream = new PdfContentStream(contentStreamId);
		((PdfPage) page).setId(pageId);
		((PdfPage) page).setParentId(pageTree.getId());
		((PdfPage) page).setContentStream(contentStream);

		indirectsObjects.add(pageTree);
		indirectsObjects.add((PdfPage) page);
		indirectsObjects.add(contentStream);

		pageTree.addPageNode((PdfPage) page);
		rootPageTree.addPageNode(pageTree);
	}

	@Override
	public void registerImage(Image image) {
		if (!(image instanceof PdfImage)) {
			throw new BadOperationException("You must add an embeddable useable by this API.");
		}
		((PdfImage) image).setId(indirectsObjects.size() + 1);

		indirectsObjects.add((PdfImage) image);
		rootPageTree.addImage((PdfImage) image);
	}

	/**
	 * Remove all pages from document and keep other data.
	 */
	public void clear() {
		indirectsObjects.clear();
		indirectsPositions.clear();

		indirectsObjects.add(catalog);
		rootPageTree.clearPages();
		indirectsObjects.add(rootPageTree);
		indirectsObjects.add(documentMetaData);
	}

	/**
	 * Use an existing image and rebind it to PDF. The image gets a new
	 * identifier and is put again in document.
	 * 
	 * @param image
	 *            The image to set new valid identifier to.
	 */
	public void rebindImage(PdfImage image) {
		image.setId(indirectsObjects.size() + 1);
		indirectsObjects.add(image);
		rootPageTree.addImage(image);
	}

	/**
	 * Write the header of the PDF to output stream.
	 * 
	 * @param stream
	 *            The stream to write header.
	 * @return The number of bytes written.
	 * @throws IOException
	 *             When header cannot be encoded to UTF-8.
	 */
	protected int writeHeader(OutputStream stream) throws IOException {
		String asString = "%PDF-1.7" + LINE_SEPARATOR // This line is a PDF
														// convention one.
				+ "%€¥﷼₯" + LINE_SEPARATOR; // This line is a PDF convention
											// one.

		byte[] bytes = asString.getBytes(Charset.forName("UTF-8"));
		stream.write(bytes);

		return bytes.length;
	}

	/**
	 * Write the meta-data section in PDF format to stream.
	 * 
	 * @param stream
	 *            The stream to write trailer to.
	 * @throws IOException
	 *             When trailer cannot be written to stream.
	 */
	protected void writeMetaData(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();

		String asString = builder.toString();

		stream.write(asString.getBytes());
	}

	/**
	 * Get the body of the PDF.
	 * 
	 * @param stream
	 *            The output stream where object will be rendered.
	 * @param position
	 *            The number of bytes written before the body in PDF.
	 * @return The number of bytes written.
	 * @throws IOException
	 *             When object cannot be write to stream.
	 */
	protected int writeBody(OutputStream stream, int position) throws IOException {
		int length = 0;

		int newPosition = position;
		for (PdfObject indirectObject : indirectsObjects) {
			indirectsPositions.add(newPosition);
			length += indirectObject.write(stream);
			newPosition = length + position;
		}

		return length;
	}

	/**
	 * Write the cross-reference section in PDF format to stream. The
	 * cross-reference section contains the list and position of each indirect
	 * object in PDF.
	 * 
	 * @param stream
	 *            The stream to write trailer to.
	 * @throws IOException
	 *             When trailer cannot be written to stream.
	 */
	protected void writeXRef(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();

		builder.append("xref" + LINE_SEPARATOR)
				// cross-reference identifier is 0 because we always generate
				// only one XRef.
				.append("0 ").append(indirectsObjects.size() + 1).append(LINE_SEPARATOR)
				// This line is a PDF convention one.
				.append("0000000000 65535 f").append(LINE_SEPARATOR);

		for (int i = 0; i < indirectsObjects.size(); i++) {
			String position = String.format("%010d", indirectsPositions.get(i));
			// We generate object only once, so generation number is always
			// 00000, and object is in use, so use 'n' tag.
			builder.append(position).append(" 00000 n").append(LINE_SEPARATOR);
		}
		String asString = builder.toString();

		stream.write(asString.getBytes());
	}

	/**
	 * Write the trailer in PDF format to stream. Trailer contains the
	 * identifier of root indirect object and number of indirect elements.
	 * 
	 * @param stream
	 *            The stream to write trailer to.
	 * @throws IOException
	 *             When trailer cannot be written to stream.
	 */
	protected void writeTrailer(OutputStream stream) throws IOException {
		String asString = "trailer <<" + LINE_SEPARATOR
		// Catalog is always at 1, so let it hard-coded.
				+ "/Root 1 0 R" + LINE_SEPARATOR + "/Size " + (indirectsObjects.size() + 1) + LINE_SEPARATOR + "/Info "
				+ documentMetaData.getId() + " 0 R" + LINE_SEPARATOR + ">>" + LINE_SEPARATOR;

		stream.write(asString.getBytes());
	}

	/**
	 * Write the startXRef in PDF format to stream. The startXRef indicates the
	 * number of bytes written to PDF before the cross-reference section.
	 * 
	 * @param stream
	 *            The stream to write start cross reference to.
	 * @param xRefPosition
	 *            The XRef position in PDF.
	 * @throws IOException
	 *             When trailer cannot be written to stream.
	 */
	protected void writeStartXRef(OutputStream stream, int xRefPosition) throws IOException {
		String asString = "startxref" + LINE_SEPARATOR + xRefPosition + LINE_SEPARATOR;

		stream.write(asString.getBytes());
	}
}
