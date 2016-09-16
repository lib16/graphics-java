package com.lib16.java.graphics.geometry;

import com.lib16.java.utils.NumberFormatter;

public class BezierPath
{
	public static final double QUADRANT_FACTOR = 0.5522847498;

	private Path path = new Path();

	public BezierPath m(Point point)
	{
		path.m(point);
		return this;
	}

	public BezierPath l(Point point)
	{
		path.l(point);
		return this;
	}

	public BezierPath c(Point controlPoint1, Point controlPoint2, Point point)
	{
		path.c(controlPoint1, controlPoint2, point);
		return this;
	}

	public BezierPath s(Point controlPoint2, Point point)
	{
		path.s(controlPoint2, point);
		return this;
	}

	public BezierPath q(Point controlPoint, Point point)
	{
		path.q(controlPoint, point);
		return this;
	}

	public BezierPath t(Point point)
	{
		path.t(point);
		return this;
	}

	public BezierPath z()
	{
		path.z();
		return this;
	}

	public BezierPath polygon(Point[] points)
	{
		path.polygon(points);
		return this;
	}

	public BezierPath rectangle(Point corner, double width, double height)
	{
		Point[] points = Points.rectangle(corner, width, height);
		path.reorderPoints(points, 0);
		polygon(points);
		return this;
	}

	public BezierPath roundedRectangle(Point corner, double width, double height, double radius)
	{
		double delta = radius * QUADRANT_FACTOR;
		Point[] rect = Points.roundedRectangle(corner, width, height, radius);
		Point[] points = {
			rect[0],
			rect[0].copy().translateX(+delta), rect[1].copy().translateY(-delta), rect[1],
			rect[2],
			rect[2].copy().translateY(+delta), rect[3].copy().translateX(+delta), rect[3],
			rect[4],
			rect[4].copy().translateX(-delta), rect[5].copy().translateY(+delta), rect[5],
			rect[6],
			rect[6].copy().translateY(-delta), rect[7].copy().translateX(-delta), rect[7],
		};
		path.reorderPoints(points, 0);
		m(points[0]);
		c(points[1], points[2], points[3]);
		l(points[4]);
		c(points[5], points[6], points[7]);
		l(points[8]);
		c(points[9], points[10], points[11]);
		l(points[12]);
		c(points[13], points[14], points[15]);
		z();
		return this;
	}

	/**
	 * Adds path commands for a regular (star) polygon.
	 *
	 * @param  n  Number of corners of the underlying polygon.
	 */
	public BezierPath star(Point center, int n, double... radii)
	{
		path.star(center, n, radii);
		return this;
	}

	public BezierPath circle(Point center, double radius)
	{
		return ellipse(center, radius, radius);
	}

	public BezierPath ellipse(Point center, double rx, double ry)
	{
		double dx = rx * QUADRANT_FACTOR;
		double dy = ry * QUADRANT_FACTOR;
		Point[] cross = Points.cross(center, rx, ry);
		Point[] points = new Point[] {
				cross[0].copy(),
				center.copy().translate( dx, -ry), center.copy().translate( rx, -dy), cross[1],
				center.copy().translate( rx,  dy), center.copy().translate( dx,  ry), cross[2],
				center.copy().translate(-dx,  ry), center.copy().translate(-rx,  dy), cross[3],
				center.copy().translate(-rx, -dy), center.copy().translate(-dx, -ry), cross[0]
		};
		path.reorderPoints(points, 0);
		m(points[0]);
		c(points[1], points[2], points[3]);
		c(points[4], points[5], points[6]);
		c(points[7], points[8], points[9]);
		c(points[10], points[11], points[12]);
		return this;
	}

	public BezierPath ccw()
	{
		path.ccw();
		return this;
	}

	public BezierPath cw()
	{
		path.cw();
		return this;
	}

	public BezierPath rotate(Point center, Angle angle)
	{
		for (Command command: path.getCommands()) {
			Points.rotate(center, angle, command.points);
		}
		return this;
	}

	public BezierPath rotate(Angle angle)
	{
		return rotate(Point.ORIGIN, angle);
	}

	public BezierPath scale(Point center, double factor)
	{
		for (Command command: path.getCommands()) {
			Points.scale(center, factor, command.points);
		}
		return this;
	}

	public BezierPath scale(double factor)
	{
		return scale(Point.ORIGIN, factor);
	}

	public BezierPath scaleX(Point center, double factor)
	{
		for (Command command: path.getCommands()) {
			Points.scaleX(center, factor, command.points);
		}
		return this;
	}

	public BezierPath scaleX(double factor)
	{
		return scaleX(Point.ORIGIN, factor);
	}

	public BezierPath scaleY(Point center, double factor)
	{
		for (Command command: path.getCommands()) {
			Points.scaleY(center, factor, command.points);
		}
		return this;
	}

	public BezierPath scaleY(double factor)
	{
		return scaleY(Point.ORIGIN, factor);
	}

	public BezierPath skewX(Point center, Angle angle)
	{
		for (Command command: path.getCommands()) {
			Points.skewX(center, angle, command.points);
		}
		return this;
	}

	public BezierPath skewX(Angle angle)
	{
		return skewX(Point.ORIGIN, angle);
	}

	public BezierPath skewY(Point center, Angle angle)
	{
		for (Command command: path.getCommands()) {
			Points.skewY(center, angle, command.points);
		}
		return this;
	}

	public BezierPath skewY(Angle angle)
	{
		return skewY(Point.ORIGIN, angle);
	}

	public BezierPath translate(double deltaX, double deltaY)
	{
		for (Command command: path.getCommands()) {
			Points.translate(deltaX, deltaY, command.points);
		}
		return this;
	}

	public BezierPath translateX(double deltaX)
	{
		return translate(deltaX, 0);
	}

	public BezierPath translateY(double deltaY)
	{
		return translate(0, deltaY);
	}

	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return path.toSvg(formatter, degreeFormatter);
	}

	public String toSvg()
	{
		return path.toSvg();
	}
}