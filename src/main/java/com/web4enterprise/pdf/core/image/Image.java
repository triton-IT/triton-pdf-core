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
package com.web4enterprise.pdf.core.image;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.Renderable;
import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.geometry.Rect;
import com.web4enterprise.pdf.core.link.Anchor;
import com.web4enterprise.pdf.core.link.Linkable;

/**
 * Class representing an image that must be embeded and rendered into a PDF document.
 * The same class is used to both represent the image data and image positioning (position, size, skew, etc).
 * The image data must be added only once to PDF to save space while it can be positioned many times without adding its data.
 * 
 * @author Régis Ramillien
 */
public class Image implements PdfObject, Anchor, Renderable {
	/**
	 * The identifier of image in PDF.
	 */
	protected int id;
	/**
	 * The X position of the rendered image in page.
	 */
	protected float x;
	/**
	 * The Y position of the rendered image in page.
	 */
	protected float y;
	/**
	 * The width of the definition of the image in PDF.
	 */
	protected int originalWidth;
	/**
	 * The height of the definition of the image in PDF.
	 */
	protected int originalHeight;
	
	/**
	 * The width of the rendered image.
	 */
	protected int width;
	/**
	 * The height of the rendered image.
	 */
	protected int height;
	/**
	 * The skew on X axis of the rendered image.
	 */
	protected int skewX;
	/**
	 * The skew on Y axis of the rendered image.
	 */
	protected int skewY;
	/**
	 * The content data of the image.
	 */
	protected byte[] data;
	/**
	 * The identifier of the page where this image is contained to.
	 */
	protected int pageId;
	/**
	 * The {@link Linkable} where this Linkable is bound to.
	 */
	protected Linkable link;
	
	/**
	 * Creates an image with the given id.
	 * 
	 * @param id The identifier in image.
	 */
	public Image(int id) {
		this.id = id;
	}

	/**
	 * Get the X position in page of the rendered image.
	 * 
	 * @return The X position.
	 */
	public float getX() {
		return x;
	}
	/**
	 * Set the X position in page of the rendered image.
	 * 
	 * @param x The X position.
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * Get the Y position in page of the rendered image.
	 * 
	 * @return The Y position.
	 */
	public float getY() {
		return y;
	}
	/**
	 * Set the Y position in page of the rendered image.
	 * 
	 * @param y The Y position.
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Get the width of the definition of the image in PDF.
	 * 
	 * @return The width.
	 */
	public int getOriginalWidth() {
		return originalWidth;
	}
	/**
	 * Set the width of the definition of the image in PDF.
	 * 
	 * @param originalWidth the width.
	 */
	public void setOriginalWidth(int originalWidth) {
		this.originalWidth = originalWidth;
	}
	/**
	 * Get the height of the definition of the image in PDF.
	 * 
	 * @return The height.
	 */
	public int getOriginalHeight() {
		return originalHeight;
	}
	/**
	 * Set the height of the definition of the image in PDF.
	 * 
	 * @param originalHeight the height.
	 */
	public void setOriginalHeight(int originalHeight) {
		this.originalHeight = originalHeight;
	}
	/**
	 * Get the width of the rendered image.
	 * 
	 * @return The width of the rendered image.
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Set the width of the rendered image.
	 * 
	 * @param width The width of the rendered image.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * Get the height of the rendered image.
	 * 
	 * @return The height of the rendered image.
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Set the height of the rendered image.
	 * 
	 * @param height The height of the rendered image.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Get the horizontal skew of the rendered image.
	 * 
	 * @return The horizontal skew of the rendered image.
	 */
	public int getSkewX() {
		return skewX;
	}
	/**
	 * Set the horizontal skew of the rendered image.
	 * 
	 * @param skewX The horizontal skew of the rendered image.
	 */
	public void setSkewX(int skewX) {
		this.skewX = skewX;
	}
	/**
	 * Get the vertical skew of the rendered image.
	 * 
	 * @return The vertical skew of the rendered image.
	 */
	public int getSkewY() {
		return skewY;
	}
	/**
	 * Set the vertical skew of the rendered image.
	 * 
	 * @param skewY The vertical skew of the rendered image.
	 */
	public void setSkewY(int skewY) {
		this.skewY = skewY;
	}
	/**
	 * Get the data of the definition of the image.
	 * The data is represented as 3 bytes per pixel (RGB).
	 * 
	 * @return The data of the image.
	 */
	public byte[] getData() {
		return data;
	}
	/**
	 * Set the data of the definition of the image.
	 * The data is represented as 3 bytes per pixel (RGB).
	 * 
	 * @param data The data of the image.
	 */
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
	 * 
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
	
	@Override
	public void setLink(Linkable destination) {
		this.link = destination;
	}
	
	@Override
	public Linkable getLink() {
		return link;
	}
	
	@Override
	public void setPage(int pageId) {
		this.pageId = pageId;
	}
	
	@Override
	public int getPage() {
		return pageId;
	}
	
	@Override
	public float getLinkX() {
		return getX();
	}
	
	@Override
	public float getLinkY() {
		return getY() + getHeight();
	}
	
	@Override
	public void render(StringBuilder builder) {
		builder.append("q").append(LINE_SEPARATOR)
			.append(getWidth()).append(" ")
			.append(getSkewY()).append(" ")
			.append(getSkewX()).append(" ")
			.append(getHeight()).append(" ")
			.append(getX()).append(" ")
			.append(getY()).append(" cm").append(LINE_SEPARATOR)
			.append("/image").append(getId()).append(" Do").append(LINE_SEPARATOR)
			.append("Q").append(LINE_SEPARATOR);
	}
	
	@Override
	public Rect getBoundingBox() {
		return new Rect(getY() + getHeight(),
				getX(), 
				getY(), 
				getX() + getWidth());
	}
}
