package com.web4enterprise.pdf.writer;

public class Page implements PDFObject, PageNode {	
	/**
	 * This properties are read-only and defined internally of library.
	 */
	protected ContentStream contentStream;
	protected int id;
	protected int parentId;
	protected int width;
	protected int height;
	
	public Page(int parentId, int id, ContentStream contentStream, int width, int height) {
		this.parentId = parentId;
		this.id = id;
		this.contentStream = contentStream;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String asString() {
		return id + " 0 obj" + LINE_SEPARATOR
				+ "<<  /Type /Page" + LINE_SEPARATOR
				+ "    /Parent " + parentId + " 0 R" + LINE_SEPARATOR
				+ "    /Resources" + LINE_SEPARATOR
				+ "    << /Font" + LINE_SEPARATOR
				+ "        << /F1" + LINE_SEPARATOR
				+ "            << /Type /Font" + LINE_SEPARATOR
				+ "                /Subtype /Type1" + LINE_SEPARATOR
				+ "                /BaseFont /Times-Roman" + LINE_SEPARATOR
				+ "            >>" + LINE_SEPARATOR
				+ "        >>" + LINE_SEPARATOR
				+ "    >>" + LINE_SEPARATOR
			    + "    /Contents " + contentStream.getId() + " 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void addText(int x, int y, int size, String text) {
		contentStream.addText(new Text(x, y, size, text));
	}
	
	public void addPath(StraightPath path) {
		contentStream.addPath(path);
	}
	
	public void addPath(BezierPath path) {
		contentStream.addPath(path);
	}
	
	protected ContentStream getContentStream() {
		return contentStream;
	}
	
	protected int getParentId() {
		return parentId;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
