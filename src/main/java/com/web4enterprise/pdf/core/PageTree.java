package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;



public class PageTree extends RootPageTree {
	protected int parent;
	protected int width;
	protected int height;
	
	public PageTree(int parent, int id, int width, int height) {
		super(id);
		this.parent = parent;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String asString = id + " 0 obj" + LINE_SEPARATOR
				+ "<< /Type /Pages" + LINE_SEPARATOR
				+ "    /Parent 2 0 R" + LINE_SEPARATOR
				+ "    /Kids [" + LINE_SEPARATOR;
		
		for(PageNode pageNode : pageNodes) {
			asString += "        " + pageNode.getId() + " 0 R" + LINE_SEPARATOR;
		}
		
		asString += "    ]" + LINE_SEPARATOR
				+ "    /Count " + pageNodes.size() + LINE_SEPARATOR
				+ "    /MediaBox [0 0 " + width + " " + height+ "]" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
		
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}
		
		return asString.length();
	}
}