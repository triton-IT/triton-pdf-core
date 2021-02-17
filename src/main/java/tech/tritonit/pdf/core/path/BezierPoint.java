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
package tech.tritonit.pdf.core.path;

import tech.tritonit.pdf.core.geometry.Point;

/**
 * Class extending a simple point to add controls necessary for Bezier curves.
 * 
 * @author Régis Ramillien
 */
public class BezierPoint extends Point {
	/**
	 * The x position of first control point.
	 */
	protected float x1;
	/**
	 * The y position of first control point.
	 */
	protected float y1;
	/**
	 * The x position of second control point.
	 */
	protected float x2;
	/**
	 * The y position of second control point.
	 */
	protected float y2;
	
	/**
	 * Create a Bezier point with control points over the main none.
	 * 
	 * @param x The x position of the point.
	 * @param y The y position of the point.
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
	 * @param x The x position of the point.
	 * @param y The y position of the point.
	 * @param x1 The x position of the first control point.
	 * @param y1 The y position of the first control point.
	 * @param x2 The x position of the second control point.
	 * @param y2 The y position of the second control point.
	 */
	public BezierPoint(float x, float y, float x1, float y1, float x2, float y2) {
		super(x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Get the x position of first control point.
	 * 
	 * @return The x coordinate of first control point.
	 */
	public float getX1() {
		return x1;
	}

	/**
	 * Set the x position of first control point.
	 * 
	 * @param x1 The x coordinate of first control point.
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}

	/**
	 * Get the y position of first control point.
	 * 
	 * @return The y coordinate of first control point.
	 */
	public float getY1() {
		return y1;
	}

	/**
	 * Set the y position of first control point.
	 * 
	 * @param y1 The y coordinate of first control point.
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}

	/**
	 * Get the x position of second control point.
	 * 
	 * @return The x coordinate of second control point.
	 */
	public float getX2() {
		return x2;
	}

	/**
	 * Set the x position of second control point.
	 * 
	 * @param x2 The x coordinate of second control point.
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}

	/**
	 * Get the y position of second control point.
	 * 
	 * @return The y coordinate of second control point.
	 */
	public float getY2() {
		return y2;
	}

	/**
	 * Set the y position of second control point.
	 * 
	 * @param y2 The y coordinate of second control point.
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
}
