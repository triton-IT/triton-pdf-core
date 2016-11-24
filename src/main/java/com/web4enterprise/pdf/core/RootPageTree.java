package com.web4enterprise.pdf.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontStyle;

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
				+ embedFontVariant(Font.COURIER.getVariant(FontStyle.PLAIN).getName())
				+ embedFontVariant(Font.COURIER.getVariant(FontStyle.BOLD).getName())
				+ embedFontVariant(Font.COURIER.getVariant(FontStyle.ITALIC).getName())
				+ embedFontVariant(Font.COURIER.getVariant(FontStyle.BOLD_ITALIC).getName())
				+ embedFontVariant(Font.HELVTICA.getVariant(FontStyle.PLAIN).getName())
				+ embedFontVariant(Font.HELVTICA.getVariant(FontStyle.BOLD).getName())
				+ embedFontVariant(Font.HELVTICA.getVariant(FontStyle.ITALIC).getName())
				+ embedFontVariant(Font.HELVTICA.getVariant(FontStyle.BOLD_ITALIC).getName())
				+ embedFontVariant(Font.SYMBOL.getVariant(FontStyle.PLAIN).getName())
				+ embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.PLAIN).getName())
				+ embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.BOLD).getName())
				+ embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.ITALIC).getName())
				+ embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.BOLD_ITALIC).getName())
				+ embedFontVariant(Font.ZAPF_DINGBATS.getVariant(FontStyle.PLAIN).getName())
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
	
	private String embedFontVariant(String fontVariant) {
		return "      /" + fontVariant + " <<" + LINE_SEPARATOR
				+ "        /Type /Font" + LINE_SEPARATOR
				+ "        /Subtype /Type1" + LINE_SEPARATOR
				+ "        /BaseFont /" + fontVariant + LINE_SEPARATOR
				+ "      >>" + LINE_SEPARATOR;
	}
}
