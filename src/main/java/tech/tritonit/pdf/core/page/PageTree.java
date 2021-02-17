/*
 * Copyright 2021 tritonit.tech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.tritonit.pdf.core.page;

import tech.tritonit.pdf.core.document.PdfContext;
import tech.tritonit.pdf.core.exceptions.PdfGenerationException;

import java.io.IOException;
import java.io.OutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

/**
 * Represent a page tree in a PDF.
 * The page tree default styling for future pages.
 *
 * @author Régis Ramillien
 */
public class PageTree extends RootPageTree {
    /**
     * Identifier of the parent of this page tree.
     */
    protected final int parent;
    /**
     * Width of this page tree.
     */
    protected final float width;
    /**
     * Height of this page tree.
     */
    protected final float height;

    /**
     * Create a page tree in PDF.
     *
     * @param parent The parent identifier of page tree object.
     * @param id     The page tre object identifier.
     * @param width  The width of the page tree.
     * @param height The height of page tree.
     */
    public PageTree(PdfContext context, int parent, int id, float width, float height) {
        super(context, id);
        this.parent = parent;
        this.width = width;
        this.height = height;
    }

    @Override
    public int write(OutputStream stream) throws PdfGenerationException {
        StringBuilder builder = new StringBuilder();

        builder.append(id).append(BEGIN_INDIRECT_OBJECT).append(LINE_SEPARATOR)
                .append(TYPE).append(PAGES).append(LINE_SEPARATOR)
                .append(PARENT).append(REFERENCE_TO_PAGES).append(LINE_SEPARATOR)
                .append(BEGIN_KIDS).append(LINE_SEPARATOR);

        for (PageNode pageNode : pageNodes) {
            builder.append(pageNode.getId()).append(REFERENCE).append(LINE_SEPARATOR);
        }

        int nativeWidth = context.getUnit().toNative(width);
        int nativeHeight = context.getUnit().toNative(height);
        builder.append(END_ARRAY).append(LINE_SEPARATOR)
                .append(COUNT).append(pageNodes.size()).append(LINE_SEPARATOR)
				.append(MEDIA_BOX).append(BEGIN_ARRAY).append(ZERO_COORDINATES).append(SPACE).append(nativeWidth).append(SPACE).append(nativeHeight).append(END_ARRAY).append(LINE_SEPARATOR)
                .append(END_DICTIONARY).append(LINE_SEPARATOR)
                .append(END_INDIRECT_OBJECT).append(LINE_SEPARATOR);

        String asString = builder.toString();
        try {
            stream.write(asString.getBytes());
        } catch (IOException e) {
            throw new PdfGenerationException("Cannot write to output stream", e);
        }

        return asString.length();
    }
}
