package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.font.FontVariant;
import com.web4enterprise.pdf.core.image.Image;
import com.web4enterprise.pdf.core.path.BezierPath;
import com.web4enterprise.pdf.core.path.StraightPath;
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
		String asString = id + " 0 obj <<" + LINE_SEPARATOR
				+ "/Type /Page" + LINE_SEPARATOR
				+ "/Parent " + parentId + " 0 R" + LINE_SEPARATOR
			    + "/Contents " + contentStream.getId() + " 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
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
	public void addText(int x, int y, int size, String text) {
		contentStream.addText(new Text(x, y, size, text));
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
	public void addText(int x, int y, int size, FontVariant fontVariant, String text) {
		contentStream.addText(new Text(x, y, size, fontVariant, text));
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
	public void addText(int x, int y, int size, FontVariant fontVariant, Color color, String text) {
		contentStream.addText(new Text(x, y, size, fontVariant, color, text));
	}
	
	/**
	 * Add a path to page.
	 * 
	 * @param path The straight path to add.
	 */
	public void addPath(StraightPath path) {
		contentStream.addPath(path);
	}
	
	/**
	 * Add a path to page.
	 * 
	 * @param path The Bezier path to add.
	 */
	public void addPath(BezierPath path) {
		contentStream.addPath(path);
	}
	
	/**
	 * Add an image to the page.
	 * 
	 * @param image The image to add.
	 */
	public void addImage(Image image) {
		contentStream.addImage(image);
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
