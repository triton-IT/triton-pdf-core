package com.web4enterprise.pdf.core;

import java.util.List;

public class BezierPath extends Path {
	protected List<BezierPoint> bezierPoints;
	
	public BezierPath(Point startPoint, List<BezierPoint> bezierPoints) {
		super(startPoint);
		
		this.bezierPoints = bezierPoints;
	}

	public List<BezierPoint> getBezierPoints() {
		return bezierPoints;
	}
}
