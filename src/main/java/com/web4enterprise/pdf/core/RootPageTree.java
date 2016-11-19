package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.font.TimesRomanBold;
import com.web4enterprise.pdf.core.font.TimesRomanBoldItalic;
import com.web4enterprise.pdf.core.font.TimesRomanItalic;
import com.web4enterprise.pdf.core.font.TimesRomanPlain;

public class RootPageTree implements PDFObject, PageNode {
	/**
	 * The pages in the PDF.
	 */
	protected List<PageNode> pageNodes = new ArrayList<>();
	/**
	 * The definition of images in the PDF.
	 */
	protected List<Image> images = new ArrayList<>();
	/**
	 * The identifier of the object.
	 */
	protected int id;
	
	/**
	 * Construct a RootPageTree with the given identifier.
	 * @param id The identifier this object.
	 */
	public RootPageTree(int id) {
		this.id = id;
	}

	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String asString = id + " 0 obj <<" + LINE_SEPARATOR
				+ "  /Type /Pages" + LINE_SEPARATOR
				+ "  /Kids [" + LINE_SEPARATOR;
		
		for(PageNode pageNode : pageNodes) {
			asString += "    " + pageNode.getId() + " 0 R" + LINE_SEPARATOR;
		}
		
		asString += "  ]" + LINE_SEPARATOR
				+ "  /Count " + pageNodes.size() + LINE_SEPARATOR
				+ "  /MediaBox [0 0 0 0]" + LINE_SEPARATOR
				+ "  /Resources <<" + LINE_SEPARATOR
				+ "    /Font <<" + LINE_SEPARATOR
				+ "      /" + TimesRomanPlain.NAME + " <<" + LINE_SEPARATOR
				+ "        /Type /Font" + LINE_SEPARATOR
				+ "        /Subtype /Type1" + LINE_SEPARATOR
				+ "        /BaseFont /Times-Roman" + LINE_SEPARATOR
				+ "      >>" + LINE_SEPARATOR
				+ "      /" + TimesRomanBold.NAME + " <<" + LINE_SEPARATOR
				+ "        /Type /Font" + LINE_SEPARATOR
				+ "        /Subtype /Type1" + LINE_SEPARATOR
				+ "        /BaseFont /Times-Bold" + LINE_SEPARATOR
				+ "      >>" + LINE_SEPARATOR
				+ "      /" + TimesRomanItalic.NAME + " <<" + LINE_SEPARATOR
				+ "        /Type /Font" + LINE_SEPARATOR
				+ "        /Subtype /Type1" + LINE_SEPARATOR
				+ "        /BaseFont /Times-Italic" + LINE_SEPARATOR
				+ "      >>" + LINE_SEPARATOR
				+ "      /" + TimesRomanBoldItalic.NAME + " <<" + LINE_SEPARATOR
				+ "        /Type /Font" + LINE_SEPARATOR
				+ "        /Subtype /Type1" + LINE_SEPARATOR
				+ "        /BaseFont /Times-BoldItalic" + LINE_SEPARATOR
				+ "      >>" + LINE_SEPARATOR
				+ "    >>" + LINE_SEPARATOR;
		if(!images.isEmpty()) {
			for(Image image : images) {
				asString += "    /XObject <<" + LINE_SEPARATOR
					+ "     /image" + image.id + " " + image.id + " 0 R" + LINE_SEPARATOR
					+ "    >>" + LINE_SEPARATOR;
			}
		}
		asString += " >>" + LINE_SEPARATOR
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
	 * Attach a page node to this object.
	 * 
	 * @param pageNode The page node to attach.
	 */
	protected void addPageNode(PageNode pageNode) {
		pageNodes.add(pageNode);
	}
	
	/**
	 * Attach a page node to this object.
	 * 
	 * @param pageNode The page node to attach.
	 */
	protected void addImage(Image image) {
		images.add(image);
	}
}
