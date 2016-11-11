package com.web4enterprise.pdf.core;

public class Catalog implements PDFObject {
	@Override
	public String asString() {
		return "1 0 obj" + LINE_SEPARATOR
		 		+ "<< /Type /Catalog" + LINE_SEPARATOR
				+ "    /Pages 2 0 R" + LINE_SEPARATOR
				+ ">>" + LINE_SEPARATOR
				+ "endobj" + LINE_SEPARATOR;	
	}
	
	@Override
	public int getId() {
		return 1;
	}
}
