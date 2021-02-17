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
package tech.tritonit.pdf.core.document;

import tech.tritonit.pdf.core.exceptions.PdfGenerationException;

import java.io.IOException;
import java.io.OutputStream;

import static tech.tritonit.pdf.core.document.PdfSyntax.*;

public class Catalog implements PdfObject {
	/**
	 * The context of document.
	 */
	protected final PdfContext context;

	public Catalog(PdfContext context) {
		this.context = context;
	}

	@Override
	public int write(OutputStream stream) throws PdfGenerationException {
		String asString = ONE + BEGIN_INDIRECT_OBJECT + LINE_SEPARATOR
				+ TYPE + BEGIN_CATALOG + LINE_SEPARATOR
				+ BEGIN_PAGES + LINE_SEPARATOR
				+ END_DICTIONARY + LINE_SEPARATOR
				+ END_INDIRECT_OBJECT + LINE_SEPARATOR;

		try {
			stream.write(asString.getBytes());
		} catch (IOException e) {
			throw new PdfGenerationException("Cannot write to output stream", e);
		}

		return asString.length();
	}

	@Override
	public int getId() {
		return 1;
	}
}
