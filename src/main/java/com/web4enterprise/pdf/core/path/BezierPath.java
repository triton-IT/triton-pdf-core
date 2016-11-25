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
	protected List<BezierPoint> bezierPoints;
	
	public BezierPath(Point startPoint, BezierPoint... bezierPoints) {
		super(startPoint);
		
		this.bezierPoints = Arrays.asList(bezierPoints);
	}

	public List<BezierPoint> getBezierPoints() {
		return bezierPoints;
	}
}
