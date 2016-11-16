package com.web4enterprise.pdf.core;

import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.font.TimesRomanBold;
import com.web4enterprise.pdf.core.font.TimesRomanBoldItalic;
import com.web4enterprise.pdf.core.font.TimesRomanItalic;
import com.web4enterprise.pdf.core.font.TimesRomanPlain;

public class RootPageTree implements PDFObject, PageNode {
	protected List<PageNode> pageNodes = new ArrayList<>();
	protected int id;
	
	public RootPageTree(int id) {
		this.id = id;
	}

	@Override
	public String asString() throws PdfGenerationException {
		String asString = id + " 0 obj" + LINE_SEPARATOR
				+ "<< /Type /Pages" + LINE_SEPARATOR
				+ "    /Kids [" + LINE_SEPARATOR;
		
		for(PageNode pageNode : pageNodes) {
			asString += "        " + pageNode.getId() + " 0 R" + LINE_SEPARATOR;
		}
		
		asString += "    ]" + LINE_SEPARATOR
				+ "    /Count " + pageNodes.size() + LINE_SEPARATOR
				+ "    /MediaBox [0 0 0 0]" + LINE_SEPARATOR
				+ "    /Resources" + LINE_SEPARATOR
				+ "    << /Font" + LINE_SEPARATOR
				+ "        << /" + TimesRomanPlain.NAME + LINE_SEPARATOR
				+ "            << /Type /Font" + LINE_SEPARATOR
				+ "                /Subtype /Type1" + LINE_SEPARATOR
				+ "                /BaseFont /Times-Roman" + LINE_SEPARATOR
				+ "            >>" + LINE_SEPARATOR
				+ "           /" + TimesRomanBold.NAME + LINE_SEPARATOR
				+ "            << /Type /Font" + LINE_SEPARATOR
				+ "                /Subtype /Type1" + LINE_SEPARATOR
				+ "                /BaseFont /Times-Bold" + LINE_SEPARATOR
				+ "            >>" + LINE_SEPARATOR
				+ "           /" + TimesRomanItalic.NAME + LINE_SEPARATOR
				+ "            << /Type /Font" + LINE_SEPARATOR
				+ "                /Subtype /Type1" + LINE_SEPARATOR
				+ "                /BaseFont /Times-Italic" + LINE_SEPARATOR
				+ "            >>" + LINE_SEPARATOR
				+ "           /" + TimesRomanBoldItalic.NAME + LINE_SEPARATOR
				+ "            << /Type /Font" + LINE_SEPARATOR
				+ "                /Subtype /Type1" + LINE_SEPARATOR
				+ "                /BaseFont /Times-BoldItalic" + LINE_SEPARATOR
				+ "            >>" + LINE_SEPARATOR
				+ "        >>" + LINE_SEPARATOR
				+ "    >>" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
		return asString;
	}

	@Override
	public int getId() {
		return id;
	}
	
	protected void addPageNode(PageNode pageNode) {
		pageNodes.add(pageNode);
	}
}
