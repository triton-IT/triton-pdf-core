package com.web4enterprise.pdf.core.image;

import static com.web4enterprise.pdf.core.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;


public class Image implements PdfObject {
	protected int id;
	protected int x;
	protected int y;
	protected int originalWidth;
	protected int originalHeight;
	protected int width;
	protected int height;
	protected int skewX;
	protected int skewY;	
	protected byte[] data;
	
	public Image(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getOriginalWidth() {
		return originalWidth;
	}
	public void setOriginalWidth(int originalWidth) {
		this.originalWidth = originalWidth;
	}
	public int getOriginalHeight() {
		return originalHeight;
	}
	public void setOriginalHeight(int originalHeight) {
		this.originalHeight = originalHeight;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getSkewX() {
		return skewX;
	}
	public void setSkewX(int skewX) {
		this.skewX = skewX;
	}
	public int getSkewY() {
		return skewY;
	}
	public void setSkewY(int skewY) {
		this.skewY = skewY;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		//Write image header.
		String asString = id + " 0 obj <<" + LINE_SEPARATOR
		+ "/Length " + data.length + LINE_SEPARATOR
		+ "/Type /XObject" + LINE_SEPARATOR
		+ "/Subtype /Image" + LINE_SEPARATOR
		+ "/Filter /FlateDecode" + LINE_SEPARATOR
		+ "/BitsPerComponent 8" + LINE_SEPARATOR
		+ "/Width " + originalWidth + LINE_SEPARATOR
		+ "/Height " + originalHeight + LINE_SEPARATOR
		+ "/ColorSpace /DeviceRGB" + LINE_SEPARATOR
		+ ">>" + LINE_SEPARATOR
		+ "stream" + LINE_SEPARATOR;
		
		int length = asString.length();
		
		try {
			stream.write(asString.getBytes());
			stream.write(data);
			
			length += data.length;
			
			//Write image footer.
			asString = LINE_SEPARATOR		
				+ "endstream" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
			
			length += asString.length();
			
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write root page tree to output stream", e);
		}
		
		return length;
	}

	@Override
	public int getId() {
		return id;
	}
	
	/**
	 * Clone the image parameters but not its content.
	 * @return The image filled-in with parameters.
	 */
	public Image cloneReference() {
		Image clone = new Image(this.id);
		
		clone.x = this.x;
		clone.y = this.y;
		clone.width = this.width;
		clone.height = this.height;
		clone.skewX = this.skewX;
		clone.skewY = this.skewY;
		
		return clone;
	}
}
