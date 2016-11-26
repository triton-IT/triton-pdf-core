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
	 * This properties are read-only and defined internally of library.
	 */
	protected ContentStream contentStream;
	protected int id;
	protected int parentId;
	protected int width;
	protected int height;
	
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
	
	public void addText(int x, int y, int size, String text) {
		contentStream.addText(new Text(x, y, size, text));
	}
	
	public void addText(int x, int y, int size, FontVariant fontVariant, String text) {
		contentStream.addText(new Text(x, y, size, fontVariant, text));
	}
	
	public void addText(int x, int y, int size, FontVariant fontVariant, Color color, String text) {
		contentStream.addText(new Text(x, y, size, fontVariant, color, text));
	}
	
	public void addPath(StraightPath path) {
		contentStream.addPath(path);
	}
	
	public void addPath(BezierPath path) {
		contentStream.addPath(path);
	}
	
	public void addImage(Image image) {
		contentStream.addImage(image);
	}
	
	protected ContentStream getContentStream() {
		return contentStream;
	}
	
	protected int getParentId() {
		return parentId;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
