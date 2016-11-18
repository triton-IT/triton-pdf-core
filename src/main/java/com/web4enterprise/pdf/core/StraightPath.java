package com.web4enterprise.pdf.core;

import java.util.ArrayList;
import java.util.List;

public class StraightPath extends Path {
	protected List<Point> points;
	
	public StraightPath(Point startPoint, List<Point> points) {
		super(startPoint);
		
		this.points = points;
	}
	
	public StraightPath(Point startPoint, Point endPoint) {
		super(startPoint);
		
		points = new ArrayList<>();
		points.add(endPoint);
	}

	public List<Point> getPoints() {
		return points;
	}
}
