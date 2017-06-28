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

import static com.web4enterprise.pdf.core.document.Pdf.LINE_SEPARATOR;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.List;

import com.web4enterprise.report.commons.geometry.Point;
import com.web4enterprise.report.commons.geometry.Rect;

/**
 * Represents a Bezier curve to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class BezierPath extends Path {
	/**
	 * The list points in the Bezier path.
	 */
	protected List<BezierPoint> bezierPoints;
	
	/**
	 * Creates a Bezier path from points.
	 * @param startPoint The first point in path.
	 * @param points The other points in path.
	 */
	public BezierPath(Point startPoint, BezierPoint... points) {
		super(startPoint, points);
		
		this.bezierPoints = Arrays.asList(points);

		float top = boundingBox.getTop();
		float left = boundingBox.getLeft();
		float bottom = boundingBox.getBottom();
		float right = boundingBox.getRight();
		
		for(BezierPoint point : points) {
			top = max(point.getY1(), top);
			top = max(point.getY2(), top);
			
			left = min(point.getX1(), left);
			left = min(point.getX2(), left);
			
			bottom = min(point.getY1(), bottom);
			bottom = min(point.getY2(), bottom);
			
			right = max(point.getX1(), right);
			right = max(point.getX2(), right);
		}
		
		boundingBox = new Rect(top, left, bottom, right);
	}

	/**
	 * Get the Bezier points of path.
	 * 
	 * @return The points.
	 */
	public List<BezierPoint> getBezierPoints() {
		return bezierPoints;
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
		for(BezierPoint point : getBezierPoints()) {
			builder.append(point.getX1()).append(" ").append(point.getY1()).append(" ")
				.append(point.getX2()).append(" ").append(point.getY2()).append(" ")
				.append(point.getX()).append(" ").append(point.getY())
				.append(" c ");
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
}
