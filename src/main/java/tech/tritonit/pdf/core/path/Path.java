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
import tech.tritonit.pdf.core.styling.Color;

/**
 * Class representing a simple path with its default properties.
 * 
 * @author Régis Ramillien
 */
public abstract class Path {
	/**
	 * The first point of path.
	 */
	protected final Point startPoint;
	/**
	 * The stroke State of the path (default is true).
	 */
	protected boolean stroked = true;
	/**
	 * The close state of the path (default is false).
	 */
	protected boolean closed;
	/**
	 * The filled state of the path (default is false).
	 */
	protected boolean filled;
	/**
	 * The line width of the path (default is 1.0f).
	 */
	protected float lineWidth = 1.0f;
	/**
	 * The stroke color (default is black).
	 */
	protected Color strokeColor = Color.BLACK;
	/**
	 * The fill color (default is black).
	 */
	protected Color fillColor = Color.BLACK;
	
	/**
	 * Creates a path.
	 * 
	 * @param startPoint The first point of path.
	 */
	protected Path(Point startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * Get the first point of the path.
	 * 
	 * @return The first point in the path.
	 */
	public Point getStartPoint() {
		return startPoint;
	}

	/**
	 * Get the stroke state of path.
	 * 
	 * @return true if path is stroked, false otherwise.
	 */
	public boolean isStroked() {
		return stroked;
	}
	
	/**
	 * Set the stroke state.
	 * 
	 * @param stroked the state to set.
	 */
	public void setStroked(boolean stroked) {
		this.stroked = stroked;
	}

	/**
	 * Get the close state of path.
	 * 
	 * @return true if path is closed, false otherwise.
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * Set the closed state.
	 * 
	 * @param closed the state to set.
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	/**
	 * Get the fill state of path.
	 * 
	 * @return true if path is filled, false otherwise.
	 */
	public boolean isFilled() {
		return filled;
	}
	
	/**
	 * Set the fill state.
	 * 
	 * @param filled the state to set.
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/**
	 * Get the line width of path.
	 * 
	 * @return The line width.
	 */
	public float getLineWidth() {
		return lineWidth;
	}

	/**
	 * Set the line width of path.
	 * 
	 * @param lineWidth The line width to set.
	 */
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * Get the stroke color of path.
	 * 
	 * @return The stroke color.
	 */
	public Color getStrokeColor() {
		return strokeColor;
	}

	/**
	 * Set the stroke color of path.
	 * 
	 * @param strokeColor The stroke color.
	 */
	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

	/**
	 * Get the fill color of path.
	 * 
	 * @return The fill color.
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Set the fill color of path.
	 * 
	 * @param fillColor The fill color.
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}
