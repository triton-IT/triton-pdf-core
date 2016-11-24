package com.web4enterprise.pdf.core;

import static com.web4enterprise.pdf.core.Pdf.LINE_SEPARATOR;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.web4enterprise.pdf.core.exceptions.PdfGenerationException;
import com.web4enterprise.pdf.core.font.Font;
import com.web4enterprise.pdf.core.font.FontStyle;

public class RootPageTree implements PdfObject, PageNode {
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
		StringBuilder builder = new StringBuilder();
		
		builder.append(id).append(" 0 obj <<").append(LINE_SEPARATOR)
		.append("  /Type /Pages").append(LINE_SEPARATOR)
		.append("  /Kids [").append(LINE_SEPARATOR);

		for(PageNode pageNode : pageNodes) {
			builder.append("    ").append(pageNode.getId()).append(" 0 R").append(LINE_SEPARATOR);
		}
		
		builder.append("  ]").append(LINE_SEPARATOR)
		.append("  /Count ").append(pageNodes.size()).append(LINE_SEPARATOR)
		.append("  /MediaBox [0 0 0 0]").append(LINE_SEPARATOR)
		.append("  /Resources <<").append(LINE_SEPARATOR)
		.append("    /Font <<").append(LINE_SEPARATOR)
		.append(embedFontVariant(Font.COURIER.getVariant(FontStyle.PLAIN).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontStyle.BOLD).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontStyle.ITALIC).getName()))
		.append(embedFontVariant(Font.COURIER.getVariant(FontStyle.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontStyle.PLAIN).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontStyle.BOLD).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontStyle.ITALIC).getName()))
		.append(embedFontVariant(Font.HELVTICA.getVariant(FontStyle.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.SYMBOL.getVariant(FontStyle.PLAIN).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.PLAIN).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.BOLD).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.ITALIC).getName()))
		.append(embedFontVariant(Font.TIMES_ROMAN.getVariant(FontStyle.BOLD_ITALIC).getName()))
		.append(embedFontVariant(Font.ZAPF_DINGBATS.getVariant(FontStyle.PLAIN).getName()))
		.append("    >>").append(LINE_SEPARATOR);
		if(!images.isEmpty()) {
			for(Image image : images) {
				builder.append("    /XObject <<").append(LINE_SEPARATOR)
				.append("     /image").append(image.id).append(" ").append(image.id).append(" 0 R").append(LINE_SEPARATOR)
				.append("    >>").append(LINE_SEPARATOR);
			}
		}
		builder.append(" >>").append(LINE_SEPARATOR)
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
