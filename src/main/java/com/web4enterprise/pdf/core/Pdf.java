package com.web4enterprise.pdf.core;

import static com.web4enterprise.pdf.core.PDFObject.LINE_SEPARATOR;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import javax.imageio.ImageIO;

public class Pdf{
	/**
	 * The list of indirect objects.
	 */
	List<PDFObject> indirectsObjects = new ArrayList<>();
	
	/**
	 * The list of bytes written before each indirect object.
	 */
	List<Integer> indirectsPositions = new ArrayList<>();
	
	/**
	 * The catalog of PDF (root page tree for now only).
	 */
	Catalog catalog = new Catalog();
	
	/**
	 * The root of pages tree.
	 */
	RootPageTree rootPageTree;
	
	/**
	 * Instantiate a new empty PDF.
	 */
	public Pdf() {
		indirectsObjects.add(catalog);
		rootPageTree = new RootPageTree(indirectsObjects.size() + 1);
		indirectsObjects.add(rootPageTree);
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
		} catch (UnsupportedEncodingException e) {
			throw new PdfGenerationException("Cannot encode to UTF-8", e);
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
		PageTree pageTree = new PageTree(rootPageTree.getId(), indirectsObjects.size() + 1, width, height);
		indirectsObjects.add(pageTree);
		
		ContentStream contentStream = new ContentStream(indirectsObjects.size() + 1);
		indirectsObjects.add(contentStream);
		
		Page page = new Page(pageTree.getId(), indirectsObjects.size() + 1, contentStream, width, height);
		indirectsObjects.add(page);
		
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
			int[] lineBuffer = new int[width];
			
			int dataLength = width * height * 3;
			byte[] data = new byte[dataLength];
	
			int componentId = 0;
			for (int y = 0; y < height; ++y) {
				for (int x : bufferedImage.getRGB(0, y, width, 1, lineBuffer, 0, width)) {
					data[componentId++] = (byte) ((x >> 16) & 0xFF);
					data[componentId++] = (byte) ((x >> 8) & 0xFF);
					data[componentId++] = (byte) (x & 0xFF);
				}
			}
	
			//Compress image data.
			try (ByteArrayOutputStream compressedOutpuStream = new ByteArrayOutputStream(dataLength)) {
				try(DeflaterOutputStream deflater = new DeflaterOutputStream(compressedOutpuStream)) {
					deflater.write(data, 0, dataLength);
					
					//Need to close because flushing is not enough...
					deflater.close();
		
					image.setData(compressedOutpuStream.toByteArray());
				}
			}
			
			indirectsObjects.add(image);
			rootPageTree.addImage(image);
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot create image.", e);
		}
		
		return image;
	}
	
	/**
	 * Write the header of the PDF to output stream.
	 * 
	 * @param The stream to write header.
	 * @return The header in PDF format.
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
	 * Get the body of the PDF.
	 * 
	 * @param stream The output stream where object will be rendered.
	 * @param position The number of bytes written before the body in PDF.
	 * @throws PdfGenerationException When PDF cannot be generated.
	 */
	protected int writeBody(OutputStream stream, int position) throws PdfGenerationException {
		int length = 0;
		
		for(int i = 0; i < indirectsObjects.size(); i++) {
			indirectsPositions.add(position);
			//Identifiers start at 1 and not 0.
			length += indirectsObjects.get(i).write(stream);
			position += length;
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
		String asString = "xref" + LINE_SEPARATOR
				// cross-reference identifier is 0 because we always generate only one XRef.
				+ "0 " + (indirectsObjects.size() + 1) + LINE_SEPARATOR
				//This line is a PDF convention one.
				+ "0000000000 65535 f" + LINE_SEPARATOR;
		
		for(int i = 0; i < indirectsObjects.size(); i++) {
			String position = String.format("%010d", indirectsPositions.get(i));
			//We generate object only once, so generation number is always 00000, and object is in use, so use 'n' tag.
			asString += position + " 00000 n" + LINE_SEPARATOR;
		}
		
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
				+ "  /Root 1 0 R" + LINE_SEPARATOR
				+ "  /Size " + (indirectsObjects.size() + 1) + LINE_SEPARATOR
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
}
