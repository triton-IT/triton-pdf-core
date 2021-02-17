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

import java.util.Arrays;
import java.util.List;

import tech.tritonit.pdf.core.geometry.Point;

/**
 * class representing a Bezier curve to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
public class BezierPath extends Path {
	/**
	 * The list points in the Bezier path.
	 */
	protected final List<BezierPoint> bezierPoints;
	
	/**
	 * Creates a Bezier path from points.
	 * @param startPoint The first point in path.
	 * @param bezierPoints The other points in path.
	 */
	public BezierPath(Point startPoint, BezierPoint... bezierPoints) {
		super(startPoint);
		
		this.bezierPoints = Arrays.asList(bezierPoints);
	}

	/**
	 * Get the Bezier points of path.
	 * 
	 * @return The points.
	 */
	public List<BezierPoint> getBezierPoints() {
		return bezierPoints;
	}
}
