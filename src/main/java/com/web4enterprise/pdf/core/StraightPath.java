package com.web4enterprise.pdf.core;

import java.util.Arrays;
import java.util.List;

public class StraightPath extends Path {
	protected List<Point> points;
	
	public StraightPath(Point startPoint, Point... points) {
		super(startPoint);
		
		this.points = Arrays.asList(points);
	}

	public List<Point> getPoints() {
		return points;
	}
}
