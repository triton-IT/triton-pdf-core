package com.web4enterprise.pdf.core;

import java.util.Arrays;
import java.util.List;

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
