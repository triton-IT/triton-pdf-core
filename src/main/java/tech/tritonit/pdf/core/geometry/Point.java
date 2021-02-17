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
package tech.tritonit.pdf.core.geometry;

/**
 * A simple point in a 2 dimensions space.
 * 
 * @author Régis Ramillien
 */
public class Point {
	/**
	 * The X position of point.
	 */
	protected float x;
	/**
	 * The Y position of point.
	 */
	protected float y;
	
	/**
	 * Create a Point with X and Y coordinates.
	 * 
	 * @param x The X position of point.
	 * @param y The Y position of point.
	 */
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X coordinate of point.
	 * 
	 * @return The X position.
	 */
	public float getX() {
		return x;
	}

	/**
	 * Set the X coordinate of point.
	 * 
	 * @param x The new position.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Get the Y coordinate of point.
	 * 
	 * @return The Y position.
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set the Y coordinate of point.
	 * 
	 * @param y The new position.
	 */
	public void setY(float y) {
		this.y = y;
	}
}
