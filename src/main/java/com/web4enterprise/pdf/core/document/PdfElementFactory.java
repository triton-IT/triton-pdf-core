package com.web4enterprise.pdf.core.document;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DeflaterOutputStream;

import javax.imageio.ImageIO;

import com.web4enterprise.pdf.core.image.PdfImage;
import com.web4enterprise.pdf.core.page.PdfPage;
import com.web4enterprise.pdf.core.path.PdfBezierPath;
import com.web4enterprise.pdf.core.path.PdfStraightPath;
import com.web4enterprise.pdf.core.text.PdfText;
import com.web4enterprise.report.commons.document.ElementFactory;
import com.web4enterprise.report.commons.exception.DocumentGenerationException;
import com.web4enterprise.report.commons.font.FontVariant;
import com.web4enterprise.report.commons.geometry.Point;
import com.web4enterprise.report.commons.path.BezierPoint;
import com.web4enterprise.report.commons.style.Color;

public class PdfElementFactory implements ElementFactory {	
	public String getMimeType() {
		return "application/pdf";
	}
	
	public Pdf createDocument() {
		return new Pdf();
	}
	
	/**
	 * Create a page.
	 * 
	 * @param width The width of the page.
	 * @param height The height of the page.
	 * @return The page reference.
	 */
	public PdfPage createPage(int width, int height) {
		return new PdfPage(width, height);
	}

	/**
	 * Create an image.
	 * 
	 * @param imageStream The stream to get the image data from.
	 * @return The image reference.
	 * @throws PdfGenerationException When PDfd cannot be compressed.
	 */
	public PdfImage createImage(InputStream imageStream) throws DocumentGenerationException {
		PdfImage image = new PdfImage();
		
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
		} catch (IOException e) {
			throw new DocumentGenerationException("Cannot create image.", e);
		}
		
		return image;
	}
	
	/**
	 * Create a text.
	 * 
	 * @param x The X position of the text in the page.
	 * @param y The Y position of the text in the page.
	 * @param size The size of the text to add.
	 * @param text The text to add.
	 */
	public PdfText createText(float x, float y, float size, String text) {
		return new PdfText(x, y, size, text);
	}
	
	/**
	 * Create a text.
	 * 
	 * @param x The X position of the text in the page.
	 * @param y The Y position of the text in the page.
	 * @param size The size of the text to add.
	 * @param fontVariant The font variant of the text.
	 * @param text The text to add.
	 */
	public PdfText createText(float x, float y, float size, FontVariant fontVariant, String text) {
		return new PdfText(x, y, size, fontVariant, text);
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
	public PdfText createText(float x, float y, float size, FontVariant fontVariant, Color color, String text) {
		return new PdfText(x, y, size, fontVariant, color, text);
	}
	
	public PdfStraightPath createStraightPath(Point startPoint, Point... points) {
		return new PdfStraightPath(startPoint, points);
	}
	
	public PdfStraightPath createStraightPath(float lineWidth, Color strokeColor, Point startPoint, Point... points) {
		return new PdfStraightPath(lineWidth, strokeColor, startPoint, points);
	}
	
	public PdfBezierPath createBezierPath(Point startPoint, BezierPoint... points) {
		return new PdfBezierPath(startPoint, points);
	}
	
	public PdfBezierPath createBezierPath(float lineWidth, Color strokeColor, Point startPoint, BezierPoint... points) {
		return new PdfBezierPath(lineWidth, strokeColor, startPoint, points);
	}

	/**
	 * Deflate a byte array.
	 * 
	 * @param data The data to deflate.
	 * @return The deflated data.
	 * @throws IOException When deflation cannot occur.
	 */
	protected byte[] deflate(byte[] data) throws IOException {
		try(ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream(data.length)) {
			try(DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(compressedOutputStream)) {
				deflaterOutputStream.write(data, 0, data.length);
			}
			
			return compressedOutputStream.toByteArray();
		}
	}
}
