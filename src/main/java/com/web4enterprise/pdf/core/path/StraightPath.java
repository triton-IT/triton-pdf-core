package com.web4enterprise.pdf.core.path;

import java.util.Arrays;
import java.util.List;

import com.web4enterprise.pdf.core.geometry.Point;
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
	 * Creates a straight path from points.
	 * 
	 * @param startPoint The first point in path.
	 * @param points The other points in path.
	 */
	public StraightPath(Point startPoint, Point... points) {
		super(startPoint);
		
		this.points = Arrays.asList(points);
	}
	
	/**
	 * Creates a straight path from points.
	 * 
	 * @param startPoint The first point in path.
	 * @param points The other points in path.
	 */
	public StraightPath(float lineWidth, Color strokeColor, Point startPoint, Point... points) {
		super(startPoint);
		
		this.lineWidth = lineWidth;
		this.strokeColor = strokeColor;
		this.points = Arrays.asList(points);
	}

	/**
	 * Get the points of path.
	 * 
	 * @return The points.
	 */
	public List<Point> getPoints() {
		return points;
	}
}
