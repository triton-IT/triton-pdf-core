/*
 * Copyright 2016 web4enterprise.
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
package com.web4enterprise.pdf.core.geometry;

/**
 * Class representing the bounding box of a 2 dimensional object.
 * 
 * @author Régis Ramillien
 */
public class BoundingBox {
	/**
	 * The left position of the box.
	 */
	protected int left;
	/**
	 * The bottom position of the box.
	 */
	protected int bottom;
	/**
	 * The right position of the box.
	 */
	protected int right;
	/**
	 * The top position of the box.
	 */
	protected int top;

	/**
	 * Create a bounding box.
	 * 
	 * @param left The left position of the box.
	 * @param bottom The bottom position of the box.
	 * @param right The right position of the box.
	 * @param top The top position of the box.
	 */
	public BoundingBox(int left, int bottom, int right,	int top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}
}
