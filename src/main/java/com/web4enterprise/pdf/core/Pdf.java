package com.web4enterprise.pdf.core;

import static com.web4enterprise.pdf.core.PDFObject.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Pdf {
	List<PDFObject> indirectsObjects = new ArrayList<>();
	List<Integer> indirectsPositions = new ArrayList<>();
	Catalog catalog = new Catalog();
	RootPageTree rootPageTree;
	
	public Pdf() {
		indirectsObjects.add(catalog);
		rootPageTree = new RootPageTree(indirectsObjects.size() + 1);
		indirectsObjects.add(rootPageTree);
	}
	
	public void write(OutputStream out) throws PdfGenerationException {
		try (OutputStreamWriter writer = new OutputStreamWriter(out, Charset.forName("UTF-8").newEncoder())) {
			String pdf = asString();
			writer.write(pdf);
		} catch(IOException e) {
			throw new PdfGenerationException("Cannot write PDF.", e);
		}
	}
	
	public String asString() throws PdfGenerationException {
		String asString = getHeader();
		
		try {
			int bodyPosition = asString.getBytes("UTF-8").length;
			asString += getBody(bodyPosition);
			int xRefPosition = asString.getBytes("UTF-8").length;
			asString += getXRef() + getTrailer() + getStartXRef(xRefPosition) + getEOF();
		} catch (UnsupportedEncodingException e) {
			throw new PdfGenerationException("Cannot encode to UTF-8", e);
		}
		
		return asString;
	}
	
	public Page createPage(int width, int height) {
		PageTree pageTree = new PageTree(rootPageTree.getId(), indirectsObjects.size() + 1, width, height);
		indirectsObjects.add(pageTree);
		
		ContentStream contentStream = new ContentStream(indirectsObjects.size() + 1);
		indirectsObjects.add(contentStream);
		
		Page page = new Page(pageTree.getId(), indirectsObjects.size() + 1, contentStream, width, height);
		indirectsObjects.add(page);
		
		pageTree.addPageNode(page);
		rootPageTree.addPageNode(pageTree);
		
		return page;
	}
	
	protected String getHeader() {
		return "%PDF-1.7" + LINE_SEPARATOR //This line is a PDF convention one.
				+ "%€¥﷼₯" + LINE_SEPARATOR; //This line is a PDF convention one.
	}
	
	protected String getBody(int position) throws PdfGenerationException {
		String body = "";
		
		try {
			for(int i = 0; i < indirectsObjects.size(); i++) {
				indirectsPositions.add(position);
				//Identifiers start at 1 and not 0.
				String asString = indirectsObjects.get(i).asString();
				
				position += asString.getBytes("UTF-8").length;
				
				body += asString;
			}
		} catch(UnsupportedEncodingException e) {
			throw new PdfGenerationException("Cannot get PDF body", e);
		}
		
		return body;
	}
	
	protected String getXRef() {
		String xref = "xref" + LINE_SEPARATOR
				+ getXRefId() + " " + (indirectsObjects.size() + 1) + LINE_SEPARATOR
				+ "0000000000 65535 f" + LINE_SEPARATOR; //This line is a PDF convention one.
		
		for(int i = 0; i < indirectsObjects.size(); i++) {
			String position = String.format("%010d", indirectsPositions.get(i));
			xref += position + " 00000 n" + LINE_SEPARATOR;
		}
		
		return xref;
	}
	
	protected int getXRefId() {
		return 0;
	}
	
	protected String getTrailer() {
		return "trailer" + LINE_SEPARATOR
				+ "  <<  /Root 1 0 R" + LINE_SEPARATOR //Catalog is always at 1, so let it hard-coded.
				+ "      /Size " + (indirectsObjects.size() + 1) + LINE_SEPARATOR
				+ "  >>" + LINE_SEPARATOR;
	}
	
	protected String getStartXRef(int xRefPosition) {
		return "startxref" + LINE_SEPARATOR
				+ xRefPosition + LINE_SEPARATOR;
	}
	
	protected String getEOF() {
		return "%%EOF";
	}
}
