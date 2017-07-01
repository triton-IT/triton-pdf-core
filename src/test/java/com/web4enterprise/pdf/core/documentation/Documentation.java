package com.web4enterprise.pdf.core.documentation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.junit.Test;

import com.web4enterprise.pdf.core.document.Pdf;
import com.web4enterprise.pdf.core.document.PdfElementFactory;
import com.web4enterprise.pdf.core.image.PdfImage;
import com.web4enterprise.pdf.core.page.PdfPage;
import com.web4enterprise.report.commons.exception.DocumentGenerationException;
import com.web4enterprise.report.commons.font.FontCache;
import com.web4enterprise.report.commons.font.FontVariant;
import com.web4enterprise.report.commons.geometry.Point;
import com.web4enterprise.report.commons.image.Image;
import com.web4enterprise.report.commons.page.Page;
import com.web4enterprise.report.commons.path.BezierPath;
import com.web4enterprise.report.commons.path.BezierPoint;
import com.web4enterprise.report.commons.path.StraightPath;
import com.web4enterprise.report.commons.style.Color;
import com.web4enterprise.report.commons.text.Text;
import com.web4enterprise.report.commons.text.TextScript;

public class Documentation {
	@Test
	public void generateDocumentation() throws IOException, DocumentGenerationException {
		Color purpleColor = new Color(128, 128, 80);
		Color yellowColor = new Color(128, 80, 128);
		Color waterColor = new Color(80, 128, 128);
		
		try(OutputStream out = new FileOutputStream("documentation.pdf")) {
			PdfElementFactory factory = new PdfElementFactory();

			Pdf pdf = factory.createDocument();
			
			pdf.setAuthor("Regis Ramillien");
			pdf.setModificationDate(new Date());
			pdf.setProducer("web4enterprise");
			pdf.setSubject("documentation for simplyPDF-core library");
			pdf.setTitle("simplyPDF-core documentation");
			pdf.addKeyword("http://web4enterprise.com");
			pdf.addKeyword("simplyPDF-core");
			pdf.addKeyword("Documentation");
			pdf.addMetaData("Customer-specific", "meta-data");
			
			Page page1 = factory.createPage(595, 842);
			pdf.addPage(page1);
			
			page1.add(factory.createText(20, 820, 12, "A PDF is created with:"));
			page1.add(factory.createText(20, 800, 8, "Pdf pdf = new Pdf();"));
			page1.add(factory.createText(20, 780, 12, "A PDF is written with:"));
			page1.add(factory.createText(20, 760, 8, "pdf.write(outputStream);"));
			
			page1.add(factory.createText(20, 720, 12, "A page is created with:"));
			page1.add(factory.createText(20, 700, 8, "Page page1 = pdf.createPage(pageWidth, pageHeight);"));
			page1.add(factory.createText(20, 680, 12, "A text with font times-roman (default font) is added with:"));
			page1.add(factory.createText(20, 660, 8, "page1.add(positionX, positionY, fontSize, \"The text to display\");"));
			page1.add(factory.createText(20, 640,  12, "A text width can be calculated with:"));
			page1.add(factory.createText(20, 620, 8, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, fontSize, \"The text to search width for.\");"));
			float textWidth = FontCache.TIMES_ROMAN.getWidth(FontVariant.PLAIN, 8.0f, "Font.TIMES_ROMAN.getWidth(FontsVariant.PLAIN, 12, \"The text to search width for.\");");
			StraightPath startPath = factory.createStraightPath(new Point(20, 628), new Point(20, 620));
			startPath.setStrokeColor(yellowColor);
			startPath.setLineWidth(0.6f);
			StraightPath endPath = factory.createStraightPath(new Point(22 + textWidth, 628), new Point(22 + textWidth, 620));
			endPath.setStrokeColor(yellowColor);
			endPath.setLineWidth(0.6f);
			page1.add(startPath);
			page1.add(endPath);
			
			page1.add(factory.createText(20, 600, 12, "A text with another font and font variant is added with:"));
			page1.add(factory.createText(20, 580, 8, "page1.add(20, 700, 12, Font.COURIER.getVariant(FontStyle.BOLD), \"The text to display\");"));
			page1.add(factory.createText(20, 560, 18, "Below are texts with different fonts, colors, sizes and underlines:"));
			page1.add(factory.createText(20, 540, 18, "Times-Roman"));
			page1.add(factory.createText(20, 520, 12, FontCache.TIMES_ROMAN.getVariant(FontVariant.BOLD), waterColor, "Times-Roman Bold"));
			page1.add(factory.createText(20, 500, 8, FontCache.TIMES_ROMAN.getVariant(FontVariant.ITALIC), purpleColor, "Times-Roman Italic"));
			page1.add(factory.createText(20, 480, 6, FontCache.TIMES_ROMAN.getVariant(FontVariant.BOLD_ITALIC), yellowColor, "Times-Roman Bold Italic"));
			page1.add(factory.createText(20, 460, 18, FontCache.COURIER.getVariant(FontVariant.PLAIN), "Courier"));
			page1.add(factory.createText(20, 440, 12, FontCache.COURIER.getVariant(FontVariant.BOLD), waterColor, "Courier Bold"));
			page1.add(factory.createText(20, 420, 8, FontCache.COURIER.getVariant(FontVariant.ITALIC), purpleColor, "Courier Italic"));
			Text courierBolditalic = factory.createText(20, 400, 6, FontCache.COURIER.getVariant(FontVariant.BOLD_ITALIC), yellowColor, "Courier Bold Italic");
			courierBolditalic.setUnderlined(true);
			courierBolditalic.setUnderlineColor(yellowColor);
			page1.add(courierBolditalic);
			page1.add(factory.createText(20, 380, 18, FontCache.HELVTICA.getVariant(FontVariant.PLAIN), "Helvetica"));
			page1.add(factory.createText(20, 360, 12, FontCache.HELVTICA.getVariant(FontVariant.BOLD), waterColor, "Helvetica Bold"));
			page1.add(factory.createText(20, 340, 8, FontCache.HELVTICA.getVariant(FontVariant.ITALIC), purpleColor, "Helvetica Italic"));
			page1.add(factory.createText(20, 320, 6, FontCache.HELVTICA.getVariant(FontVariant.BOLD_ITALIC), yellowColor, "Helvetica Bold Italic"));
			page1.add(factory.createText(20, 300, 12, FontCache.SYMBOL.getVariant(FontVariant.PLAIN), "Symbol"));
			Text zapfDingbats = factory.createText(20, 280, 12, FontCache.ZAPF_DINGBATS.getVariant(FontVariant.PLAIN), waterColor, "Zapf-Dingbats");
			zapfDingbats.setUnderlined(true);
			zapfDingbats.setUnderlineColor(purpleColor);
			page1.add(zapfDingbats);

			page1.add(factory.createText(20, 220, 12, "An image is created with:"));
			page1.add(factory.createText(20, 200, 8, "Image image = pdf.createImage(this.getClass().getResourceAsStream(\"/logo.png\"));"));
			page1.add(factory.createText(20, 180, 12, "An image is sized and positioned with:"));
			page1.add(factory.createText(20, 160, 8, "image.setX(20);"));
			page1.add(factory.createText(20, 140, 8, "image.setY(70);"));
			page1.add(factory.createText(20, 120, 8, "image.setWidth(60);"));
			page1.add(factory.createText(20, 100, 8, "image.setHeight(30);"));
			page1.add(factory.createText(20, 80, 12, "An image is added with:"));
			page1.add(factory.createText(20, 60, 8, "page1.addImage(image);"));
			PdfImage image = factory.createImage(this.getClass().getResourceAsStream("/logo.png"));
			image.setX(20);
			image.setY(20);
			image.setWidth(19);
			image.setHeight(26);
			pdf.registerImage(image);
			page1.add(image);

			PdfPage page2 = factory.createPage(595, 842);
			pdf.addPage(page2);
			
			page2.add(factory.createText(20, 820, 12, "When an image is used multiple times, no need to re-create its data."));
			page2.add(factory.createText(20, 800, 12, "Just save space in PDF by cloning it's references using:"));
			page2.add(factory.createText(20, 780, 8, "Image image2 = image.cloneReference();"));
			page2.add(factory.createText(20, 760, 12, "Change its size, position, etc and draw it again."));
			Image image2 = image.cloneMetaData();
			image2.setX(20);
			image2.setY(690);
			image2.setWidth(37);
			image2.setHeight(53);
			page2.add(image2);
			page2.add(factory.createText(20, 650, 12, "A straight line can be added with:"));
			page2.add(factory.createText(20, 630, 8, "page2.addPath(new StraightPath(new Point(20, 640), new Point(150, 650)));"));			
			page2.add(factory.createStraightPath(new Point(20, 600), new Point(30, 610)));

			page2.add(factory.createText(20, 580, 12, "A line can be added with more points using:"));
			page2.add(factory.createText(20, 560, 8, "StraightPath straightPath = new StraightPath(new Point(20, 510), new Point(20, 500), new Point(150, 500));"));
			page2.add(factory.createText(20, 540, 8, "straightPath.setClosed(true);"));
			page2.add(factory.createText(20, 520, 8, "page2.addPath(straightPath);"));
			StraightPath straightPath = factory.createStraightPath(new Point(20, 500), new Point(30, 510), new Point(40, 500));
			straightPath.setClosed(true);
			page2.add(straightPath);

			page2.add(factory.createText(20, 480, 12, "A Bezier line can be added with:"));
			page2.add(factory.createText(20, 460, 8, "page2.addPath(new BezierPath(new Point(20, 440), new BezierPoint(50, 440, 30, 432, 40, 432))"));
			BezierPath bezierPath = factory.createBezierPath(new Point(20, 440), new BezierPoint(50, 440, 30, 432, 40, 432));
			page2.add(bezierPath);

			page2.add(factory.createText(20, 420, 12, "Straight and Bezier lines can be filled/stroked/closed with:"));
			page2.add(factory.createText(20, 400, 8, "BezierPath bezierPath2 = new BezierPath(new Point(20, 400), new BezierPoint(50, 440, 30, 432, 40, 432));"));
			page2.add(factory.createText(20, 380, 8, "bezierPath2.setFillColor(new Color(80, 128, 128));"));
			page2.add(factory.createText(20, 360, 8, "bezierPath2.setFilled(true);"));
			page2.add(factory.createText(20, 340, 8, "bezierPath2.setStrokeColor(navyColor);"));
			page2.add(factory.createText(20, 320, 8, "bezierPath2.setLineWidth(4.0f);"));
			page2.add(factory.createText(20, 300, 8, "bezierPath2.setClosed(true);"));
			page2.add(factory.createText(20, 280, 8, "page2.addPath(bezierPath2);"));
			BezierPath bezierPath2 = factory.createBezierPath(new Point(20, 260), new BezierPoint(50, 260, 30, 252, 40, 252));
			bezierPath2.setClosed(true);
			page2.add(bezierPath2);
			BezierPath bezierPath3 = factory.createBezierPath(new Point(60, 260), new BezierPoint(90, 260, 70, 252, 80, 252));
			bezierPath3.setFilled(true);
			bezierPath3.setStroked(false);
			page2.add(bezierPath3);
			BezierPath bezierPath4 = factory.createBezierPath(new Point(100, 260), new BezierPoint(130, 260, 110, 252, 120, 252));
			bezierPath4.setFillColor(purpleColor);
			bezierPath4.setFilled(true);
			bezierPath4.setStrokeColor(yellowColor);
			bezierPath4.setLineWidth(4.0f);
			page2.add(bezierPath4);

			Text linkedText = factory.createText(20, 220, 12, "click on this text to see internal link in action.");
			linkedText.setLink(image);
			page2.add(linkedText);
			page2.add(factory.createText(20, 200, 8, "Text linkedText = new Text(20, 200, 12, \"click on this text to see internal link in action.\");"));
			page2.add(factory.createText(20, 180, 8, "linkedText.setLink(image);"));
			image.setLink(linkedText);

			Text superScriptedText = factory.createText(20, 140, 12, "Super");
			superScriptedText.setScript(TextScript.SUPER);
			page2.add(superScriptedText);
			page2.add(factory.createText(40, 140, 12, "and"));
			Text subScriptedText = factory.createText(60, 140, 12, "sub");
			subScriptedText.setScript(TextScript.SUB);
			page2.add(subScriptedText);
			page2.add(factory.createText(70, 140, 12, "-scriptedtext"));
			page2.add(superScriptedText);
			page2.add(factory.createText(20, 120, 8, "text.setScript(TextScript.SUPER);"));
			page2.add(factory.createText(20, 100, 8, "text.setScript(TextScript.SUB);"));

			page2.add(factory.createText(20, 80, 8, "A character not supported (like this one: ഷ) does not compute width correctly and logs a warning."));
			
			Color color = new Color(0, 0, 0);
			page2.add(factory.createText(20,  60, 8, FontCache.TIMES_ROMAN.getVariant(FontVariant.PLAIN), color, "Color is computed not when text is added (or this text should be black) but when pdf is written."));
			page2.add(factory.createText(20,  40, 8, "So be sure to control your object references."));
			color.setRed(80);
			color.setGreen(128);
			color.setBlue(128);

			Page page3 = factory.createPage(595, 842);
			pdf.addPage(page3);
			
			page3.add(factory.createText(20, 820, 12, "Document meta-data can be added with setter on document (like document.setAuthor(\"author\"))."));
			page3.add(factory.createText(20, 800, 12, "Or specific meta-data can be added with document.addMetaData(name, value)."));
			page3.add(factory.createText(20, 780, 12, "Key words are added with pdf.addKeyword(keyword)"));
			
			pdf.write(out);
		}
	}
}
