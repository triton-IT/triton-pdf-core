package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;


public class Image implements PDFObject {
	protected int id;
	protected int x;
	protected int y;
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
		+ "  /Length " + data.length + LINE_SEPARATOR
		+ "  /Type /XObject" + LINE_SEPARATOR
		+ "  /Subtype /Image" + LINE_SEPARATOR
		+ "  /Filter /FlateDecode" + LINE_SEPARATOR
		+ "  /BitsPerComponent 8" + LINE_SEPARATOR
		+ "  /Width " + width + LINE_SEPARATOR
		+ "  /Height " + height + LINE_SEPARATOR
		+ "  /ColorSpace /DeviceRGB" + LINE_SEPARATOR
		+ ">>" + LINE_SEPARATOR
		+ "stream" + LINE_SEPARATOR;
		
		int length = asString.length();
		
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write root page tree to output stream", e);
		}
		
		//Write data.
		try {
			stream.write(data);
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write root page tree to output stream", e);
		}
		length += data.length;

		//Write image footer.
		asString = LINE_SEPARATOR		
			+ "endstream" + LINE_SEPARATOR
			+ "endobj" + LINE_SEPARATOR;
		
		length += asString.length();
		
		try {
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
}
