/*
 * Copyright 2021 tritonit.tech.
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
package tech.tritonit.pdf.core.document;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;
import tech.tritonit.pdf.core.font.FontsVariant;
import tech.tritonit.pdf.core.geometry.Point;
import tech.tritonit.pdf.core.image.Image;
import tech.tritonit.pdf.core.image.ImageData;
import tech.tritonit.pdf.core.image.ImageLoader;
import tech.tritonit.pdf.core.page.Page;
import tech.tritonit.pdf.core.path.BezierPath;
import tech.tritonit.pdf.core.path.BezierPoint;
import tech.tritonit.pdf.core.path.StraightPath;
import tech.tritonit.pdf.core.styling.Color;
import tech.tritonit.pdf.core.text.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static tech.tritonit.pdf.core.font.Font.*;

class PdfTest {
	//Create an image loader to load images using BufferedImage.
	private final ImageLoader imageLoader = imageStream -> {
		BufferedImage bufferedImage = ImageIO.read(imageStream);

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		//Read image data.
		int[] lineBuffer = new int[width * height];

		byte[] pixels = new byte[width * height * 3];
		byte[] alpha = new byte[width * height];
		boolean hasAlpha = false;

		int[] sourcePixels = bufferedImage.getRGB(0, 0, width, height, lineBuffer, 0, width);
		int componentId = 0;
		for (int imageY = 0; imageY < height; imageY++) {
			for (int imageX = 0; imageX < width; imageX++, componentId++) {
				int rgb = sourcePixels[imageX + imageY * width];
				pixels[componentId * 3] = (byte) (rgb >> 16);
				pixels[componentId * 3 + 1] = (byte) (rgb >> 8);
				pixels[componentId * 3 + 2] = (byte) rgb;

				alpha[componentId] = (byte) ((rgb >> 24) & 0xFF);
				if (alpha[componentId] != 0) {
					hasAlpha = true;
				}
			}
		}

		ImageData imageData;
		//Compress and set image data.
		if (hasAlpha) {
			imageData = new ImageData(width, height, pixels, alpha);
		} else {
			imageData = new ImageData(width, height, pixels);
		}

		return imageData;
	};

	@Test
	void testWrite() throws IOException, PdfGenerationException {
		try (OutputStream out = new FileOutputStream("documentation.pdf")) {
			float y = 277.0f;

			Pdf pdf = new Pdf();
			Page page1 = pdf.createPage(210.0f, 297.0f);

			Image logoTrITon = pdf.createImage(this.getClass().getResourceAsStream("/logo-trITon_320.png"), imageLoader);
			logoTrITon.setX(20.0f);
			logoTrITon.setY(y - 20.0f);
			logoTrITon.setWidth(20.0f);
			logoTrITon.setHeight(20.0f);
			page1.addImage(logoTrITon);
			y -= 25.0f;

			page1.addText(20, y, 12, "A PDF is created with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "Pdf pdf = new Pdf();");
			y -= 5.0f;
			page1.addText(20, y, 10, "or");
			y -= 5.0f;
			page1.addText(20, y, 10, "Pdf pdf = new Pdf(<Unit>);");
			y -= 5.0f;
			page1.addText(20, y, 10, "where <Unit> is an enum and can be mm, cm, inch or PDf native. Default unit is mm.");
			y -= 5.0f;
			page1.addText(20, y, 10, "For instance, you can specify unit as inch like this:");
			y -= 5.0f;
			page1.addText(20, y, 10, "Pdf pdf = new Pdf(Unit.INCH);");
			y -= 5.0f;
			page1.addText(20, y, 12, "A PDF is written with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "pdf.write(out);");
			y -= 5.0f;
			page1.addText(20, y, 10, "where out is the output stream to write pdf to.");

			y -= 8.0f;
			page1.addText(20, y, 12, "A page is created with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "Page page1 = pdf.createPage(<width>, <height>);");
			y -= 5.0f;
			page1.addText(20, y, 10, "where <width> and <height> are expressed in <unit>.");

			y -= 8.0f;
			page1.addText(20, y, 12, "A text with default font (times-roman) is added with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "page1.addText(<x>, <y>, <font size>, <text>);");
			y -= 5.0f;
			page1.addText(20, y, 12, "A text width can be calculated with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 12, \"The text to search width for.\");");

			float textWidth = pdf.getFont(TIMES_ROMAN).getWidth(FontsVariant.PLAIN, 10, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 12, \"The text to search width for.\");");
			Color widthDecoratorColor = new Color(128, 80, 128);
			float widthDecoratorSize = 0.6f;
			StraightPath startPath = new StraightPath(new Point(20.0f, y - 0.1f), new Point(20.0f, y - 1.0f));
			startPath.setStrokeColor(widthDecoratorColor);
			startPath.setLineWidth(widthDecoratorSize);
			page1.addPath(startPath);
			StraightPath endPath = new StraightPath(new Point(20.0f + textWidth, y - 0.1f), new Point(20.0f + textWidth, y - 1.0f));
			endPath.setStrokeColor(widthDecoratorColor);
			endPath.setLineWidth(widthDecoratorSize);
			page1.addPath(endPath);
			StraightPath widthPath = new StraightPath(new Point(20.0f, y - 1.0f), new Point(20.0f + textWidth, y - 1.0f));
			widthPath.setStrokeColor(widthDecoratorColor);
			widthPath.setLineWidth(widthDecoratorSize);
			page1.addPath(widthPath);
			y -= 5.0f;

			page1.addText(20, y, 12, "A text with another font is added with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "page1.addText(20, 700, 12, Font.COURIER.getVariant(FontStyle.BOLD), \"The text to display\");");
			y -= 5.0f;
			page1.addText(20, y, 12, "Below are texts with different fonts, colors, sizes and underlines:");
			y -= 5.0f;
			page1.addText(20, y, 12, "Times-Roman");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(TIMES_ROMAN).getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Times-Roman Bold");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(TIMES_ROMAN).getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Times-Roman Italic");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(TIMES_ROMAN).getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Times-Roman Bold Italic");
			y -= 5.0f;
			page1.addText(20, y, 12, pdf.getFont(COURIER).getVariant(FontsVariant.PLAIN), "Courier");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(COURIER).getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Courier Bold");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(COURIER).getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Courier Italic");
			y -= 5.0f;
			Text courierBoldItalic = new Text(20, y, 10, pdf.getFont(COURIER).getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Courier Bold Italic");
			courierBoldItalic.setUnderlined(true);
			courierBoldItalic.setUnderlineColor(new Color(128, 80, 128));
			page1.addText(courierBoldItalic);
			y -= 5.0f;
			page1.addText(20, y, 12, pdf.getFont(HELVETICA).getVariant(FontsVariant.PLAIN), "Helvetica");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(HELVETICA).getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Helvetica Bold");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(HELVETICA).getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Helvetica Italic");
			y -= 5.0f;
			page1.addText(20, y, 10, pdf.getFont(HELVETICA).getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Helvetica Bold Italic");
			y -= 5.0f;
			page1.addText(20, y, 12, pdf.getFont(SYMBOL).getVariant(FontsVariant.PLAIN), "Symbol");
			y -= 5.0f;
			Text zapfDingbats = page1.addText(20, y, 10, pdf.getFont(ZAPF_DINGBATS).getVariant(FontsVariant.PLAIN), new Color(128, 128, 80), "Zapf-Dingbats");
			zapfDingbats.setUnderlined(true);
			zapfDingbats.setUnderlineColor(new Color(80, 128, 128));
			y -= 8.0f;

			page1.addText(20, y, 12, "An image is created with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "Image image = pdf.createImage(this.getClass().getResourceAsStream(\"/test.png\"));");
			y -= 5.0f;
			page1.addText(20, y, 12, "An image is sized and positioned with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "image.setX(20);");
			y -= 5.0f;
			page1.addText(20, y, 10, "image.setY(70);");
			y -= 5.0f;
			page1.addText(20, y, 10, "image.setWidth(60);");
			y -= 5.0f;
			page1.addText(20, y, 10, "image.setHeight(30);");
			y -= 5.0f;
			page1.addText(20, y, 12, "An image is added with:");
			y -= 5.0f;
			page1.addText(20, y, 10, "page1.addImage(image);");

			Page page2 = pdf.createPage(210, 297);
			y = 277.0f;

			page2.addText(20, y, 12, "When an image is used multiple times, no need to re-create its data.");
			y -= 5.0f;
			page2.addText(20, y, 12, "Just save space in PDF and memory by cloning it's references using:");
			y -= 5.0f;
			page2.addText(20, y, 8, "Image image2 = image.cloneReference();");
			y -= 5.0f;
			page2.addText(20, y, 12, "Change its size, position, etc and draw it again.");
			y -= 50.0f;
			Image image2 = logoTrITon.cloneReference();
			image2.setX(20);
			image2.setY(y);
			image2.setWidth(45.0f);
			image2.setHeight(45.0f);
			page2.addImage(image2);
			y -= 5.0f;

			page2.addText(20, y, 12, "A straight line can be added with:");
			y -= 5.0f;
			page2.addText(20, y, 8, "page2.addPath(new StraightPath(new Point(20, 640), new Point(15.0f0, 65.0f0)));");
			y -= 5.0f;
			page2.addPath(new StraightPath(new Point(20, y), new Point(30, y + 3)));

			y -= 5.0f;
			page2.addText(20, y, 12, "A line can be added with more points using:");
			y -= 5.0f;
			page2.addText(20, y, 8, "StraightPath straightPath = new StraightPath(new Point(20, 5.0f), new Point(20, 5.0f), new Point(15.0f, 5.0f));");
			y -= 5.0f;
			page2.addText(20, y, 8, "straightPath.setClosed(true);");
			y -= 5.0f;
			page2.addText(20, y, 8, "page2.addPath(straightPath);");
			y -= 5.0f;
			StraightPath straightPath = new StraightPath(new Point(20, y), new Point(30, y + 3), new Point(40, y));
			straightPath.setClosed(true);
			page2.addPath(straightPath);

			y -= 5.0f;
			page2.addText(20, y, 12, "A Bezier line can be added with:");
			y -= 5.0f;
			page2.addText(20, y, 8, "page2.addPath(new BezierPath(new Point(20, 440), new BezierPoint(5.0f0, 440, 30, 432, 40, 432))");
			y -= 5.0f;
			BezierPath bezierPath = new BezierPath(new Point(20, y), new BezierPoint(50, y, 30, y + 2, 40, y + 2));
			page2.addPath(bezierPath);

			y -= 5.0f;
			page2.addText(20, y, 12, "Straight and Bezier lines can be filled/stroked/closed with:");
			y -= 5.0f;
			page2.addText(20, y, 8, "BezierPath bezierPath2 = new BezierPath(new Point(20, 400), new BezierPoint(5.0f0, 440, 30, 432, 40, 432));");
			y -= 5.0f;
			page2.addText(20, y, 8, "bezierPath2.setFillColor(new Color(80, 128, 128));");
			y -= 5.0f;
			page2.addText(20, y, 8, "bezierPath2.setFilled(true);");
			y -= 5.0f;
			page2.addText(20, y, 8, "bezierPath2.setStrokeColor(new Color(128, 80, 128));");
			y -= 5.0f;
			page2.addText(20, y, 8, "bezierPath2.setLineWidth(4.0f);");
			y -= 5.0f;
			page2.addText(20, y, 8, "bezierPath2.setClosed(true);");
			y -= 5.0f;
			page2.addText(20, y, 8, "page2.addPath(bezierPath2);");
			y -= 5.0f;
			BezierPath bezierPath2 = new BezierPath(new Point(20, y), new BezierPoint(50, y, 30, y - 5.0f, 40, y - 5.0f));
			bezierPath2.setClosed(true);
			page2.addPath(bezierPath2);

			BezierPath bezierPath3 = new BezierPath(new Point(60, y), new BezierPoint(90, y, 70, y - 5.0f, 80, y - 5.0f));
			bezierPath3.setFilled(true);
			bezierPath3.setStroked(false);
			page2.addPath(bezierPath3);

			BezierPath bezierPath4 = new BezierPath(new Point(100, y), new BezierPoint(130, y, 110, y - 5.0f, 120, y - 5.0f));
			bezierPath4.setFillColor(new Color(80, 128, 128));
			bezierPath4.setFilled(true);
			bezierPath4.setStrokeColor(new Color(128, 80, 128));
			bezierPath4.setLineWidth(4.0f);
			page2.addPath(bezierPath4);

			pdf.write(out);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			pdf.write(byteArrayOutputStream);

			try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				byte[] actualBytes = byteArrayOutputStream.toByteArray();
				byte[] actualHash = messageDigest.digest(actualBytes);

				byte[] expectedHash = {-57, -106, 89, -101, 6, 123, -56, -87, -71, 29, 59, 27, -21, -121, 43, -79, 44, 95, 22, -103, -19, 83, -9, 78, -16, -73, -62, 109, 41, 58, 30, 101};
				Assertions.assertArrayEquals(expectedHash, actualHash);

			} catch (NoSuchAlgorithmException e) {
				Assertions.fail("Cannot hash PDF");
			}
		}
	}
}
