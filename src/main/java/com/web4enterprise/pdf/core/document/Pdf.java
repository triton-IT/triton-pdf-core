package com.web4enterprise.pdf.core.document;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import javax.imageio.ImageIO;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.image.Image;
import com.web4enterprise.pdf.core.page.ContentStream;
import com.web4enterprise.pdf.core.page.Page;
import com.web4enterprise.pdf.core.page.PageTree;
import com.web4enterprise.pdf.core.page.RootPageTree;

public class Pdf{
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
			int[] lineBuffer = new int[width * height];
			
			byte[] data = new byte[width * height * 3];
	
			int rgbs[] = bufferedImage.getRGB(0, 0, width, height, lineBuffer, 0, width);
			int componentId = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int rgb = rgbs[x + y * width];
					data[componentId++] = (byte) ((rgb >> 16) & 0xFF);
					data[componentId++] = (byte) ((rgb >> 8) & 0xFF);
					data[componentId++] = (byte) (rgb & 0xFF);
				}
			}
	
			//Compress image data.			
			byte[] compressedImage = deflate(data);
			
			image.setData(compressedImage);
			
			indirectsObjects.add(image);
			rootPageTree.addImage(image);
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot create image.", e);
		}
		
		return image;
	}

	private byte[] deflate(byte[] data) throws IOException {
		ByteArrayOutputStream compressedOutpuStream = null;
		DeflaterOutputStream deflaterOutputStream = null;
		
		try {
			compressedOutpuStream = new ByteArrayOutputStream(data.length);
			deflaterOutputStream = new DeflaterOutputStream(compressedOutpuStream);
			
			deflaterOutputStream.write(data, 0, data.length);
		} finally {
			if(deflaterOutputStream != null) {
				deflaterOutputStream.close();
			}
			if(compressedOutpuStream != null) {
				compressedOutpuStream.close();
			}
		}
		
		return compressedOutpuStream.toByteArray();
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
		
		int newPosition = position;
		for(int i = 0; i < indirectsObjects.size(); i++) {
			indirectsPositions.add(newPosition);
			//Identifiers start at 1 and not 0.
			length += indirectsObjects.get(i).write(stream);
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
