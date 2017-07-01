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

import com.web4enterprise.pdf.core.document.PdfObject;
import com.web4enterprise.report.commons.geometry.Rect;
import com.web4enterprise.report.commons.image.Image;

/**
 * Class representing an image that must be embedded and rendered into a PDF document.
 * The same class is used to both represent the image data and image positioning (position, size, skew, etc).
 * The image data must be added only once to PDF to save space while it can be positioned many times without adding its data.
 * 
 * @author Régis Ramillien
 */
public class PdfImage extends Image implements PdfObject {	
	/**
	 * The identifier of image in PDF.
	 */
	protected int id;
	
	/**
	 * Set the identifier of this image.
	 * 
	 * @param id The new identifier of image.
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int write(OutputStream stream) throws IOException {
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
		
		stream.write(asString.getBytes());
		stream.write(data);
		
		length += data.length;
		
		//Write image footer.
		asString = LINE_SEPARATOR		
			+ "endstream" + LINE_SEPARATOR
			+ "endobj" + LINE_SEPARATOR;
		
		length += asString.length();
		
		stream.write(asString.getBytes());
		
		return length;
	}

	@Override
	public int getId() {
		return id;
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
	public PdfImage cloneMetaData() {
		PdfImage clone = new PdfImage();
		
		clone.setId(id);
		clone.boundingBox = new Rect(boundingBox);
		clone.skewX = this.skewX;
		clone.skewY = this.skewY;
		
		return clone;
	}
}
