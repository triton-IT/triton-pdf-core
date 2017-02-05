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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import javax.imageio.ImageIO;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.image.Image;
import com.web4enterprise.pdf.core.page.ContentStream;
import com.web4enterprise.pdf.core.page.Page;
import com.web4enterprise.pdf.core.page.PageTree;
import com.web4enterprise.pdf.core.page.RootPageTree;

/**
 * The PDF document.
 * 
 * 
 * @author Régis Ramillien
 */
public class Pdf {
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
	protected Catalog catalog = new Catalog();
	
	/**
	 * The meta-data of document.
	 */
	protected DocumentMetaData documentMetaData;
	
	/**
	 * The root of pages tree.
	 */
	protected RootPageTree rootPageTree;	
	
	/**
	 * Instantiate a new empty PDF.
	 */
	public Pdf() {
		indirectsObjects.add(catalog);
		rootPageTree = new RootPageTree(indirectsObjects.size() + 1);
		indirectsObjects.add(rootPageTree);
		documentMetaData = new DocumentMetaData(indirectsObjects.size() + 1);
		indirectsObjects.add(documentMetaData);
	}
	
	/**
	 * Set the title of document.
	 * 
	 * @param title The title of document meta-data.
	 */
	public void setTitle(String title) {
		documentMetaData.title = title;
	}
	
	/**
	 * Set the author of document.
	 * 
	 * @param author The author of document meta-data.
	 */
	public void setAuthor(String author) {
		documentMetaData.author = author;
	}
	
	/**
	 * Set the subject of document.
	 * 
	 * @param subject The subject of document meta-data.
	 */
	public void setSubject(String subject) {
		documentMetaData.subject = subject;
	}
	
	/**
	 * Add a keyword to document meta-data.
	 * 
	 * @param keyword The keyword to add to document meta-data.
	 */
	public void addKeyword(String keyword) {
		documentMetaData.keyWords.add(keyword);
	}

	/**
	 * Set the creator of document.
	 * 
	 * @param creator The creator of document meta-data.
	 */
	public void setCreator(String creator) {
		documentMetaData.creator = creator;
	}

	/**
	 * Set the producer of document.
	 * 
	 * @param producer The producer of this document to set to meta-data.
	 */
	public void setProducer(String producer) {
		documentMetaData.producer = producer;
	}

	/**
	 * Set the create date of document.
	 * 
	 * @param creationDate The date of creation of this document to set to meta-data.
	 */
	public void setCreationDate(Date creationDate) {
		documentMetaData.creationDate = creationDate;
	}

	/**
	 * Set the modification date of document.
	 * 
	 * @param modificationDate The date of modification of this document to set to meta-data.
	 */
	public void setModificationDate(Date modificationDate) {
		documentMetaData.modificationDate = modificationDate;
	}
	
	/**
	 * Add a custom meta-data to document.
	 * 
	 * @param key The key of custom meta-data to add to document.
	 * @param value The value of custom meta-data to add to document.
	 */
	public void addMetaData(String key, String value) {
		documentMetaData.customs.add(new MetaData(key, value));
	}
	
	/**
	 * Write PDF to stream.
	 * 
	 * @param stream The stream to write PDF to.
	 * @throws PdfGenerationException When PDF cannot be generated.
	 */
	public void write(OutputStream stream) throws PdfGenerationException {
		try {
			int position = writeHeader(stream);
			position += writeBody(stream, position);
			writeXRef(stream);
			writeTrailer(stream);
			writeStartXRef(stream, position);
			stream.write("%%EOF".getBytes());
		} catch(IOException e) {
			throw new PdfGenerationException("Cannot write PDF.", e);
		}
	}
	
	/**
	 * Create a page.
	 * 
	 * @param width The width of the page.
	 * @param height The height of the page.
	 * @return The page reference.
	 */
	public Page createPage(int width, int height) {
		int pageTreeId = indirectsObjects.size() + 1;
		int pageId = pageTreeId + 1;
		int contentStreamId = pageId + 1;
		
		PageTree pageTree = new PageTree(rootPageTree.getId(), pageTreeId, width, height);
		ContentStream contentStream = new ContentStream(contentStreamId);		
		Page page = new Page(pageTree.getId(), pageId, contentStream, width, height);
		
		indirectsObjects.add(pageTree);
		indirectsObjects.add(page);
		indirectsObjects.add(contentStream);
		
		pageTree.addPageNode(page);
		rootPageTree.addPageNode(pageTree);
		
		return page;
	}

	/**
	 * Create an image.
	 * 
	 * @param imageStream The stream to get the image data from.
	 * @return The image reference.
	 * @throws PdfGenerationException When PDfd cannot be compressed.
	 */
	public Image createImage(InputStream imageStream) throws PdfGenerationException {
		Image image = new Image(indirectsObjects.size() + 1);
		
		try {
			BufferedImage bufferedImage = ImageIO.read(imageStream);
	
			int width = bufferedImage.getWidth();
			image.setWidth(width);
			image.setOriginalWidth(width);
			
			int height = bufferedImage.getHeight();
			image.setHeight(height);
			image.setOriginalHeight(height);
			
			//Read image data.
			int[] lineBuffer = new int[width * height];
			
			byte[] data = new byte[width * height * 3];
	
			int[] rgbs = bufferedImage.getRGB(0, 0, width, height, lineBuffer, 0, width);
			int componentId = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int rgb = rgbs[x + y * width];
					data[componentId++] = (byte) ((rgb >> 16) & 0xFF);
					data[componentId++] = (byte) ((rgb >> 8) & 0xFF);
					data[componentId++] = (byte) (rgb & 0xFF);
				}
			}
	
			//Compress and set image data.			
			image.setData(deflate(data));
			
			indirectsObjects.add(image);
			rootPageTree.addImage(image);
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot create image.", e);
		}
		
		return image;
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
	 * Use an existing image and rebind it to PDF.
	 * The image gets a new identifier and is put again in document.
	 * 
	 * @param image The image to set new valid identifier to.
	 */
	public void rebindImage(Image image) {
		image.setId(indirectsObjects.size() + 1);
		indirectsObjects.add(image);
		rootPageTree.addImage(image);
	}
	
	/**
	 * Write the header of the PDF to output stream.
	 * 
	 * @param stream The stream to write header.
	 * @return The number of bytes written.
	 * @throws IOException When header cannot be encoded to UTF-8.
	 */
	protected int writeHeader(OutputStream stream) throws IOException {
		String asString = "%PDF-1.7" + LINE_SEPARATOR //This line is a PDF convention one.
				+ "%€¥﷼₯" + LINE_SEPARATOR; //This line is a PDF convention one.
		
		byte[] bytes = asString.getBytes(Charset.forName("UTF-8"));
		stream.write(bytes);
		
		return bytes.length;
	}
	
	/**
	 * Write the meta-data section in PDF format to stream.
	 * 
	 * @param stream The stream to write trailer to.
	 * @throws IOException When trailer cannot be written to stream.
	 */
	protected void writeMetaData(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();

		String asString = builder.toString();
		
		stream.write(asString.getBytes());
	}
	
	/**
	 * Get the body of the PDF.
	 * 
	 * @param stream The output stream where object will be rendered.
	 * @param position The number of bytes written before the body in PDF.
	 * @return The number of bytes written.
	 * @throws PdfGenerationException When PDF cannot be generated.
	 */
	protected int writeBody(OutputStream stream, int position) throws PdfGenerationException {
		int length = 0;
		
		int newPosition = position;
		for(PdfObject indirectObject : indirectsObjects) {
			indirectsPositions.add(newPosition);
			length += indirectObject.write(stream);
			newPosition = length + position;
		}
		
		return length;
	}
	
	/**
	 * Write the cross-reference section in PDF format to stream.
	 * The cross-reference section contains the list and position of each indirect object in PDF.
	 * 
	 * @param stream The stream to write trailer to.
	 * @throws IOException When trailer cannot be written to stream.
	 */
	protected void writeXRef(OutputStream stream) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		builder.append("xref" + LINE_SEPARATOR)
		// cross-reference identifier is 0 because we always generate only one XRef.
		.append("0 ").append(indirectsObjects.size() + 1).append(LINE_SEPARATOR)
		//This line is a PDF convention one.
		.append("0000000000 65535 f").append(LINE_SEPARATOR);

		for(int i = 0; i < indirectsObjects.size(); i++) {
			String position = String.format("%010d", indirectsPositions.get(i));
			//We generate object only once, so generation number is always 00000, and object is in use, so use 'n' tag.
			builder.append(position).append(" 00000 n").append(LINE_SEPARATOR);
		}		
		String asString = builder.toString();
		
		stream.write(asString.getBytes());
	}
	
	/**
	 * Write the trailer in PDF format to stream.
	 * Trailer contains the identifier of root indirect object and number of indirect elements.
	 * 
	 * @param stream The stream to write trailer to.
	 * @throws IOException When trailer cannot be written to stream.
	 */
	protected void writeTrailer(OutputStream stream) throws IOException {
		String asString = "trailer <<" + LINE_SEPARATOR
				//Catalog is always at 1, so let it hard-coded.
				+ "/Root 1 0 R" + LINE_SEPARATOR
				+ "/Size " + (indirectsObjects.size() + 1) + LINE_SEPARATOR
				+ "/Info " + documentMetaData.getId() + " 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR;
		
		stream.write(asString.getBytes());
	}
	
	/**
	 * Write the startXRef in PDF format to stream.
	 * The startXRef indicates the number of bytes written to PDF before the cross-reference section.
	 * 
	 * @param stream The stream to write start cross reference to.
	 * @param xRefPosition The XRef position in PDF.
	 * @throws IOException When trailer cannot be written to stream.
	 */
	protected void writeStartXRef(OutputStream stream, int xRefPosition) throws IOException {
		String asString = "startxref" + LINE_SEPARATOR
				+ xRefPosition + LINE_SEPARATOR;
		
		stream.write(asString.getBytes());
	}

	/**
	 * Deflate a byte array.
	 * 
	 * @param data The data to deflate.
	 * @return The deflated data.
	 * @throws IOException When deflation cannot occur.
	 */
	@SuppressWarnings("squid:S2093") //Because Jacoco reports coverage false positives, we cannot use try-with-resource. So do it old school.
	protected byte[] deflate(byte[] data) throws IOException {
		ByteArrayOutputStream compressedOutpuStream = new ByteArrayOutputStream(data.length);
		DeflaterOutputStream deflaterOutputStream = null;
		try {
			deflaterOutputStream = new DeflaterOutputStream(compressedOutpuStream);
			deflaterOutputStream.write(data, 0, data.length);
		} finally {
			if(deflaterOutputStream != null) {
				deflaterOutputStream.close();
			}
			compressedOutpuStream.close();
		}
		return compressedOutpuStream.toByteArray();
	}
}
