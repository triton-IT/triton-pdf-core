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
package com.web4enterprise.pdf.core.path;

import com.web4enterprise.report.commons.geometry.Point;

/**
 * Class extending a simple {@link com.web4enterprise.pdf.core.geometry.Point} to add controls necessary for Bezier curves.
 * 
 * @author Régis Ramillien
 */
public class BezierPoint extends Point {
	/**
	 * The X position of first control point.
	 */
	protected float x1;
	/**
	 * The Y position of first control point.
	 */
	protected float y1;
	/**
	 * The X position of second control point.
	 */
	protected float x2;
	/**
	 * The Y position of second control point.
	 */
	protected float y2;
	
	/**
	 * Create a Bezier point with control points over the main none.
	 * 
	 * @param x The X position of the point.
	 * @param y The Y position of the point.
	 */
	public BezierPoint(float x, float y) {
		super(x, y);
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}
	
	/**
	 * Create a Bezier point.
	 * 
	 * @param x The X position of the point.
	 * @param y The Y position of the point.
	 * @param x1 The X position of the first control point.
	 * @param y1 The Y position of the first control point.
	 * @param x2 The X position of the second control point.
	 * @param y2 The Y position of the second control point.
	 */
	public BezierPoint(float x, float y, float x1, float y1, float x2, float y2) {
		super(x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Get the X position of first control point.
	 * 
	 * @return The X coordinate of first control point.
	 */
	public float getX1() {
		return x1;
	}

	/**
	 * Set the X position of first control point.
	 * 
	 * @param x1 The X coordinate of first control point.
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}

	/**
	 * Get the Y position of first control point.
	 * 
	 * @return The Y coordinate of first control point.
	 */
	public float getY1() {
		return y1;
	}

	/**
	 * Set the Y position of first control point.
	 * 
	 * @param y1 The X coordinate of first control point.
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}

	/**
	 * Get the X position of second control point.
	 * 
	 * @return The X coordinate of second control point.
	 */
	public float getX2() {
		return x2;
	}

	/**
	 * Set the X position of second control point.
	 * 
	 * @param x2 The X coordinate of second control point.
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}

	/**
	 * Get the Y position of second control point.
	 * 
	 * @return The Y coordinate of second control point.
	 */
	public float getY2() {
		return y2;
	}

	/**
	 * Set the Y position of second control point.
	 * 
	 * @param y2 The Y coordinate of second control point.
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
}
