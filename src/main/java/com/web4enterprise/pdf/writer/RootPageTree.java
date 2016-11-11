package com.web4enterprise.pdf.writer;

import java.util.ArrayList;
import java.util.List;

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
