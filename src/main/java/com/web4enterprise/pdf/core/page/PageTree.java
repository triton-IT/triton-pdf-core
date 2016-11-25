package com.web4enterprise.pdf.core.page;

import static com.web4enterprise.pdf.core.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;



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
		StringBuilder builder = new StringBuilder();
		
		builder.append(id + " 0 obj <<").append(LINE_SEPARATOR)
		.append("/Type /Pages").append(LINE_SEPARATOR)
		.append("/Parent 2 0 R").append(LINE_SEPARATOR)
		.append("/Kids [").append(LINE_SEPARATOR);
		
		for(PageNode pageNode : pageNodes) {
			builder.append(pageNode.getId()).append(" 0 R").append(LINE_SEPARATOR);
		}
		
		builder.append("]").append(LINE_SEPARATOR)
		.append("/Count ").append(pageNodes.size()).append(LINE_SEPARATOR)
		.append("/MediaBox [0 0 ").append(width).append(" ").append(height+ "]").append(LINE_SEPARATOR)
		.append(">>").append(LINE_SEPARATOR)
		.append("endobj").append(LINE_SEPARATOR);
		
		String asString = builder.toString();
		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}
		
		return asString.length();
	}
}
