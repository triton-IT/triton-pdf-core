package com.web4enterprise.pdf.core.path;

import java.util.Arrays;
import java.util.List;

import com.web4enterprise.pdf.core.geometry.Point;

/**
 * Class representing a straight path to render in a PDF document.
 * 
 * @author Régis Ramillien
 */
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
