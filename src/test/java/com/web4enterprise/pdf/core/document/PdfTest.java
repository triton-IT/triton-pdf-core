/*
 * Copyright 2016 web4enterprise.
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
package com.web4enterprise.pdf.core.document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import com.web4enterprise.pdf.core.document.Pdf;
import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontsVariant;
import com.web4enterprise.pdf.core.geometry.Point;
import com.web4enterprise.pdf.core.image.Image;
import com.web4enterprise.pdf.core.page.Page;
import com.web4enterprise.pdf.core.path.BezierPath;
import com.web4enterprise.pdf.core.path.BezierPoint;
import com.web4enterprise.pdf.core.path.StraightPath;
import com.web4enterprise.pdf.core.styling.Color;
import com.web4enterprise.pdf.core.text.Text;

public class PdfTest {
	@Test
	public void testWrite() throws IOException, PdfGenerationException {
		try(OutputStream out = new FileOutputStream("documentation.pdf")) {
			Pdf pdf = new Pdf();
			Page page1 = pdf.createPage(595, 842);
			
			page1.addText(20, 820, 12, "A PDF is created with:");
			page1.addText(20, 800, 8, "Pdf pdf = new Pdf();");
			page1.addText(20, 780, 12, "A PDF is written with:");
			page1.addText(20, 760, 8, "pdf.write(out);");
			
			page1.addText(20, 720, 12, "A page is created with:");
			page1.addText(20, 700, 8, "Page page1 = pdf.createPage(595, 842);");
			page1.addText(20, 680, 12, "A text with font times-roman is added with:");
			page1.addText(20, 660, 8, "page1.addText(20, 700, 12, \"The text to display\");");
			page1.addText(20, 640,  12, "A text width can be calculated with:");
			page1.addText(20, 620, 8, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 12, \"The text to search width for.\");");
			float textWidth = Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 8, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 12, \"The text to search width for.\");");
			StraightPath startPath = new StraightPath(new Point(20, 628), new Point(20, 620));
			startPath.setStrokeColor(new Color(128, 80, 128));
			startPath.setLineWidth(0.6f);
			StraightPath endPath = new StraightPath(new Point(22 + textWidth, 628), new Point(22 + textWidth, 620));
			endPath.setStrokeColor(new Color(128, 80, 128));
			endPath.setLineWidth(0.6f);
			page1.addPath(startPath);
			page1.addPath(endPath);
			
			page1.addText(20, 600, 12, "A text with another font is added with:");
			page1.addText(20, 580, 8, "page1.addText(20, 700, 12, Font.COURIER.getVariant(FontStyle.BOLD), \"The text to display\");");
			page1.addText(20, 560, 18, "Below are texts with different fonts, colors, sizes and underlines:");
			page1.addText(20, 540, 18, "Times-Roman");
			page1.addText(20, 520, 12, Font.TIMES_ROMAN.getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Times-Roman Bold");
			page1.addText(20, 500, 8, Font.TIMES_ROMAN.getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Times-Roman Italic");
			page1.addText(20, 480, 6, Font.TIMES_ROMAN.getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Times-Roman Bold Italic");
			page1.addText(20, 460, 18, Font.COURIER.getVariant(FontsVariant.PLAIN), "Courier");
			page1.addText(20, 440, 12, Font.COURIER.getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Courier Bold");
			page1.addText(20, 420, 8, Font.COURIER.getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Courier Italic");
			Text courierBolditalic = new Text(20, 400, 6, Font.COURIER.getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Courier Bold Italic");
			courierBolditalic.setUnderlined(true);
			courierBolditalic.setUnderlineColor(new Color(128, 80, 128));
			page1.addText(courierBolditalic);
			page1.addText(20, 380, 18, Font.HELVTICA.getVariant(FontsVariant.PLAIN), "Helvetica");
			page1.addText(20, 360, 12, Font.HELVTICA.getVariant(FontsVariant.BOLD), new Color(128, 128, 80), "Helvetica Bold");
			page1.addText(20, 340, 8, Font.HELVTICA.getVariant(FontsVariant.ITALIC), new Color(80, 128, 128), "Helvetica Italic");
			page1.addText(20, 320, 6, Font.HELVTICA.getVariant(FontsVariant.BOLD_ITALIC), new Color(128, 80, 128), "Helvetica Bold Italic");
			page1.addText(20, 300, 12, Font.SYMBOL.getVariant(FontsVariant.PLAIN), "Symbol");
			Text zapfDingbats = new Text(20, 280, 12, Font.ZAPF_DINGBATS.getVariant(FontsVariant.PLAIN), new Color(128, 128, 80), "Zapf-Dingbats");
			zapfDingbats.setUnderlined(true);
			zapfDingbats.setUnderlineColor(new Color(80, 128, 128));
			page1.addText(zapfDingbats);

			page1.addText(20, 220, 12, "An image is created with:");
			page1.addText(20, 200, 8, "Image image = pdf.createImage(this.getClass().getResourceAsStream(\"/logo.png\"));");
			page1.addText(20, 180, 12, "An image is sized and positioned with:");
			page1.addText(20, 160, 8, "image.setX(20);");
			page1.addText(20, 140, 8, "image.setY(70);");
			page1.addText(20, 120, 8, "image.setWidth(60);");
			page1.addText(20, 100, 8, "image.setHeight(30);");
			page1.addText(20, 80, 12, "An image is added with:");
			page1.addText(20, 60, 8, "page1.addImage(image);");
			Image image = pdf.createImage(this.getClass().getResourceAsStream("/logo.png"));
			image.setX(20);
			image.setY(20);
			image.setWidth(30);
			image.setHeight(30);
			page1.addImage(image);

			Page page2 = pdf.createPage(595, 842);
			page2.addText(20, 820, 12, "When an image is used multiple times, no need to re-create its data.");
			page2.addText(20, 800, 12, "Just save space in PDF by cloning it's references using:");
			page2.addText(20, 780, 8, "Image image2 = image.cloneReference();");
			page2.addText(20, 760, 12, "Change its size, position, etc and draw it again.");
			Image image2 = image.cloneReference();
			image2.setX(20);
			image2.setY(670);
			image2.setWidth(70);
			image2.setHeight(70);
			page2.addImage(image2);

			page2.addText(20, 650, 12, "A straight line can be added with:");
			page2.addText(20, 630, 8, "page2.addPath(new StraightPath(new Point(20, 640), new Point(150, 650)));");			
			page2.addPath(new StraightPath(new Point(20, 600), new Point(30, 610)));

			page2.addText(20, 580, 12, "A line can be added with more points using:");
			page2.addText(20, 560, 8, "StraightPath straightPath = new StraightPath(new Point(20, 510), new Point(20, 500), new Point(150, 500));");
			page2.addText(20, 540, 8, "straightPath.setClosed(true);");
			page2.addText(20, 520, 8, "page2.addPath(straightPath);");
			StraightPath straightPath = new StraightPath(new Point(20, 500), new Point(30, 510), new Point(40, 500));
			straightPath.setClosed(true);
			page2.addPath(straightPath);

			page2.addText(20, 480, 12, "A Bezier line can be added with:");
			page2.addText(20, 460, 8, "page2.addPath(new BezierPath(new Point(20, 440), new BezierPoint(50, 440, 30, 432, 40, 432))");
			BezierPath bezierPath = new BezierPath(new Point(20, 440), new BezierPoint(50, 440, 30, 432, 40, 432));
			page2.addPath(bezierPath);

			page2.addText(20, 420, 12, "Straight and Bezier lines can be filled/stroked/closed with:");
			page2.addText(20, 400, 8, "BezierPath bezierPath2 = new BezierPath(new Point(20, 400), new BezierPoint(50, 440, 30, 432, 40, 432));");
			page2.addText(20, 380, 8, "bezierPath2.setFillColor(new Color(80, 128, 128));");
			page2.addText(20, 360, 8, "bezierPath2.setFilled(true);");
			page2.addText(20, 340, 8, "bezierPath2.setStrokeColor(new Color(128, 80, 128));");
			page2.addText(20, 320, 8, "bezierPath2.setLineWidth(4.0f);");
			page2.addText(20, 300, 8, "bezierPath2.setClosed(true);");
			page2.addText(20, 280, 8, "page2.addPath(bezierPath2);");
			BezierPath bezierPath2 = new BezierPath(new Point(20, 260), new BezierPoint(50, 260, 30, 252, 40, 252));
			bezierPath2.setClosed(true);
			page2.addPath(bezierPath2);
			BezierPath bezierPath3 = new BezierPath(new Point(60, 260), new BezierPoint(90, 260, 70, 252, 80, 252));
			bezierPath3.setFilled(true);
			bezierPath3.setStroked(false);
			page2.addPath(bezierPath3);
			BezierPath bezierPath4 = new BezierPath(new Point(100, 260), new BezierPoint(130, 260, 110, 252, 120, 252));
			bezierPath4.setFillColor(new Color(80, 128, 128));
			bezierPath4.setFilled(true);
			bezierPath4.setStrokeColor(new Color(128, 80, 128));
			bezierPath4.setLineWidth(4.0f);
			page2.addPath(bezierPath4);
			
			pdf.write(out);
		}
	}
}
