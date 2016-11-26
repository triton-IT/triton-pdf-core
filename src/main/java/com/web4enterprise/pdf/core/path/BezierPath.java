package com.web4enterprise.pdf.core.path;

import java.util.Arrays;
import java.util.List;

import com.web4enterprise.pdf.core.geometry.Point;

/**
 * class representing a Bezier curve to render in a PDF document.
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
