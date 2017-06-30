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
package com.web4enterprise.pdf.core.page;

import com.web4enterprise.report.commons.geometry.Rect;
import com.web4enterprise.report.commons.page.Page;

/**
 * Defines a link annotation as expected in PDF format.
 * 
 * @author Régis Ramillien
 */
public class PdfLinkAnnotation {
	/**
	 * The target page of this link.
	 */
	protected Page destinationPage;
	/**
	 * The target X coordinate of this link.
	 */
	protected float destinationX;
	/**
	 * The target Y coordinate of this link.
	 */
	protected float destinationY;
	/**
	 * The zoom factor of this link.
	 */
	protected float destinationZ;
	/**
	 * The source rectangle of this linK.
	 * This is generally the bounding-box of linked element.
	 */
	protected Rect sourceRect;
	
	/**
	 * Consntruct a link with required values.
	 * 
	 * @param destinationPage The target page of this link.
	 * @param destinationX The target X coordinate of this link.
	 * @param destinationY The target Y coordinate of this link.
	 * @param destinationZ The zoom factor of this link.
	 * @param sourceRect The source rectangle of this linK.
	 *  This is generally the bounding-box of linked element.
	 */
	public PdfLinkAnnotation(Page destinationPage, float destinationX, float destinationY, float destinationZ, Rect sourceRect) {
		this.destinationPage = destinationPage;
		this.destinationX = destinationX;
		this.destinationY = destinationY;
		this.destinationZ = destinationZ;
		this.sourceRect = sourceRect;
	}

	/**
	 * Get the target page of this link.
	 * 
	 * @return The target page.
	 */
	public Page getDestinationPage() {
		return destinationPage;
	}

	/**
	 * Get the X coordinate of target.
	 * 
	 * @return The X coordinate of target.
	 */
	public float getDestinationX() {
		return destinationX;
	}

	/**
	 * Get the Y coordinate of target.
	 * 
	 * @return The Y coordinate of target.
	 */
	public float getDestinationY() {
		return destinationY;
	}

	/**
	 * Get the Z coordinate of target.
	 * 
	 * @return The Z coordinate of target.
	 */
	public float getDestinationZ() {
		return destinationZ;
	}

	/**
	 * The source rectangle of this linK.
	 *  This is generally the bounding-box of linked element.
	 * 
	 * @return The bounding-box of link.
	 */
	public Rect getSourceRect() {
		return sourceRect;
	}
}
