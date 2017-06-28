/*
 * Copyright 2017 web4enterprise.
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
package com.web4enterprise.pdf.core.document;

import com.web4enterprise.pdf.core.link.Anchor;
import com.web4enterprise.pdf.core.link.Linkable;
import com.web4enterprise.report.commons.geometry.Rect;

/**
 * Defines a component renderable to a PDF.
 * 
 * 
 * @author Régis Ramillien
 */
public abstract class Renderable implements Anchor, Linkable {
	/**
	 * The identifier of the page where this text is contained to.
	 */
	protected int pageId;
	/**
	 * The bounding box of path.
	 */
	protected Rect boundingBox = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
	/**
	 * The {@link Linkable} where this Linkable is bound to.
	 */
	protected Linkable link;
	
	@Override
	public Integer getPage() {
		return pageId;
	}
	
	/**
	 * Set the page where this Renderable is positioned to.
	 * 
	 * @param pageId The id of the page to set to this element.
	 */
	public void setPage(int pageId) {
		this.pageId = pageId;
	}
	
	@Override
	public Linkable getLink() {
		return link;
	}
	
	@Override
	public void setLink(Linkable destination) {
		this.link = destination;
	}
	
	@Override
	public Float getLinkX() {
		return boundingBox.getLeft();
	}
	
	@Override
	public Float getLinkY() {
		return boundingBox.getTop();
	}

	/**
	 * Get the bounding box of renderable.
	 * 
	 * @return The bounding box.
	 */
	public Rect getBoundingBox() {
		return boundingBox;
	}
	
	/**
	 * Render object in PDF format. 
	 * 
	 * @param builder The String builder to render element to.
	 */
	public abstract void render(StringBuilder builder);
}
