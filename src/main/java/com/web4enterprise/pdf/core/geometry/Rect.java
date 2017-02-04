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
package com.web4enterprise.pdf.core.geometry;

/**
 * Class representing the bounding box of a 2 dimensional object.
 * 
 * @author Régis Ramillien
 */
public class Rect {
	/**
	 * The left position of the box.
	 */
	protected float left;
	/**
	 * The bottom position of the box.
	 */
	protected float bottom;
	/**
	 * The right position of the box.
	 */
	protected float right;
	/**
	 * The top position of the box.
	 */
	protected float top;

	/**
	 * Create a rectangle.
	 * 
	 * @param top The top position of the box.
	 * @param left The left position of the box.
	 * @param bottom The bottom position of the box.
	 * @param right The right position of the box.
	 */
	public Rect(float top, float left, float bottom, float right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}
	
	/**
	 * Re-copy constructor.
	 * 
	 * @param source The source rectangle to copy.
	 */
	public Rect(Rect source) {
		this.top = source.top;
		this.left = source.left;
		this.bottom = source.bottom;
		this.right = source.right;
	}
	
	/**
	 * Get the top coordinate.
	 * Top coordinate should be less than the bottom one or rectangle will be inverted.
	 * 
	 * @return The top coordinate of rectangle.
	 */
	public float getTop() {
		return top;
	}
	
	/**
	 * Set the top coordinate.
	 * Top coordinate should be less than the bottom one or rectangle will be inverted.
	 * 
	 * @param top The top coordinate of rectangle.
	 */
	public void setTop(float top) {
		this.top = top;
	}

	/**
	 * Set the top coordinate.
	 * Top coordinate should be less than the bottom one or rectangle will be inverted.
	 * If keepRatio is true, the bottom coordinate will be moved by the amount of movement of top.
	 * 
	 * @param top The top coordinate of rectangle.
	 * @param keepRatio true if bottom must be moved to. False otherwise.
	 */
	public void setTop(float top, boolean keepRatio) {
		if(keepRatio) {
			this.bottom = top + getHeight();
		}
		this.top = top;
	}
	
	/**
	 * Get the left coordinate.
	 * Left coordinate should be less than the right one or rectangle will be inverted.
	 * 
	 * @return The left coordinate of rectangle.
	 */
	public float getLeft() {
		return left;
	}
	
	/**
	 * Set the left coordinate.
	 * Left coordinate should be less than the right one or rectangle will be inverted.
	 * 
	 * @param left The left coordinate of rectangle.
	 */
	public void setLeft(float left) {
		this.left = left;
	}

	/**
	 * Set the left coordinate.
	 * Left coordinate should be less than the right one or rectangle will be inverted.
	 * If keepRatio is true, the right coordinate will be moved by the amount of movement of left.
	 * 
	 * @param left The left coordinate of rectangle.
	 * @param keepRatio true if bottom must be moved to. False otherwise.
	 */
	public void setLeft(float left, boolean keepRatio) {
		if(keepRatio) {
			this.right = left + getWidth();
		}
		this.left = left;
	}

	/**
	 * Get the bottom coordinate.
	 * Bottom coordinate should be greater than the top one or rectangle will be inverted.
	 * 
	 * @return The bottom coordinate of rectangle.
	 */
	public float getBottom() {
		return bottom;
	}
	
	/**
	 * Set the bottom coordinate.
	 * Bottom coordinate should be less than the right one or rectangle will be inverted.
	 * 
	 * @param bottom The bottom coordinate of rectangle.
	 */
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

	/**
	 * Get the right coordinate.
	 * Right coordinate should be greater than the left one or rectangle will be inverted.
	 * 
	 * @return The right coordinate of rectangle.
	 */
	public float getRight() {
		return right;
	}
	
	/**
	 * Set the right coordinate.
	 * Right coordinate should be greater than the left one or rectangle will be inverted.
	 * 
	 * @param right The right coordinate of rectangle.
	 */
	public void setRight(float right) {
		this.right = right;
	}

	/**
	 * Get the width of the rectangle.
	 * 
	 * @return The width of rectangle.
	 */
	public float getWidth() {
		return right - left;
	}
	
	/**
	 * Set the width of the rectangle.
	 * The right coordinate will be moved to left coordinates + width.
	 * 
	 * @param width The width to set.
	 */
	public void setWidth(float width) {
		right = left + width;
	}

	/**
	 * Get the height of the rectangle.
	 * 
	 * @return The height of rectangle.
	 */
	public float getHeight() {
		return bottom - top;
	}
	
	/**
	 * Set the height of the rectangle.
	 * The bottom coordinate will be moved to top coordinates + height.
	 * 
	 * @param height The height to set.
	 */
	public void setHeight(float height) {
		bottom = top + height;
	}
}
