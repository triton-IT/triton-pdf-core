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
package com.web4enterprise.pdf.core.path;

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;

import java.util.Arrays;
import java.util.List;

import com.web4enterprise.pdf.core.geometry.Point;
import com.web4enterprise.pdf.core.geometry.Rect;
import com.web4enterprise.pdf.core.styling.Color;

/**
 * Class representing a straight path to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class StraightPath extends Path {
	/**
	 * The list points in the straight path.
	 */
	protected List<Point> points;
	/**
	 * The bounding box of path.
	 */
	protected Rect boundingBox;
	
	/**
	 * Creates a straight path from points.
	 * 
	 * @param startPoint The first point in path.
	 * @param points The other points in path.
	 */
	public StraightPath(Point startPoint, Point... points) {
		super(startPoint);
		
		this.points = Arrays.asList(points);

		float top = startPoint.getY();
		float left = startPoint.getX();
		float bottom = startPoint.getY();
		float right = startPoint.getX();
		
		for(Point point : points) {
			if(point.getY() > top) {
				top = point.getY();
			}
			if(point.getX() < left) {
				left = point.getX();
			}
			if(point.getY() < bottom) {
				bottom = point.getY();
			}
			if(point.getX() > right) {
				right = point.getX();
			}
		}
		
		boundingBox = new Rect(top, left, bottom, right);
	}
	
	/**
	 * Creates a straight path from points.
	 * 
	 * @param lineWidth The width of line to create.
	 * @param strokeColor The stroke color of line.
	 * @param startPoint The first point in path.
	 * @param points The other points in path.
	 */
	public StraightPath(float lineWidth, Color strokeColor, Point startPoint, Point... points) {
		this(startPoint, points);
		
		this.lineWidth = lineWidth;
		this.strokeColor = strokeColor;
	}

	/**
	 * Get the points of path.
	 * 
	 * @return The points.
	 */
	public List<Point> getPoints() {
		return points;
	}
	
	@Override
	public void render(StringBuilder builder) {
		builder.append(getLineWidth()).append(" w ")		
			.append(((float) getStrokeColor().getRed()) / 255.0f).append(" ")
			.append(((float) getStrokeColor().getGreen()) / 255.0f).append(" ")
			.append(((float) getStrokeColor().getBlue()) / 255.0f).append(" ")
			.append("RG ")
			
			.append(((float) getFillColor().getRed()) / 255.0f).append(" ")
			.append(((float) getFillColor().getGreen()) / 255.0f).append(" ")
			.append(((float) getFillColor().getBlue()) / 255.0f).append(" ")
			.append("rg ")
		
			.append(getStartPoint().getX()).append(" ").append(getStartPoint().getY()).append(" m ");
		for(Point point : getPoints()) {
			builder.append(point.getX()).append(" ").append(point.getY()).append(" l ");
		}
		if(isFilled() && isStroked()) {
			builder.append("B");
		} else if(isFilled()) {
			builder.append("f");
		} else if(isStroked()) {
			builder.append(isClosed()?"s":"S");
		}
		builder.append(LINE_SEPARATOR);
	}
	
	@Override
	public float getLinkX() {
		return boundingBox.getLeft();
	}
	
	@Override
	public float getLinkY() {
		return boundingBox.getTop();
	}
	
	@Override
	public Rect getBoundingBox() {
		return boundingBox;
	}
}
