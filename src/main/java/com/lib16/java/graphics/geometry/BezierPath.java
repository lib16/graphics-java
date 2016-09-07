package com.lib16.java.graphics.geometry;

import com.lib16.java.utils.NumberFormatter;

public class BezierPath
{
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