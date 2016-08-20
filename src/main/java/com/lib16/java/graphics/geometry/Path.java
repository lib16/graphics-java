package com.lib16.java.graphics.geometry;

import java.text.NumberFormat;
import java.util.ArrayList;

import com.lib16.java.arrays.Arrays;
import com.lib16.java.graphics.geometry.pathcommands.Arc;
import com.lib16.java.graphics.geometry.pathcommands.ClosePath;
import com.lib16.java.graphics.geometry.pathcommands.CubicCurveTo;
import com.lib16.java.graphics.geometry.pathcommands.HorizontalLineTo;
import com.lib16.java.graphics.geometry.pathcommands.LineTo;
import com.lib16.java.graphics.geometry.pathcommands.MoveTo;
import com.lib16.java.graphics.geometry.pathcommands.QuadraticCurveTo;
import com.lib16.java.graphics.geometry.pathcommands.SmoothCubicCurveTo;
import com.lib16.java.graphics.geometry.pathcommands.SmoothQuadraticCurveTo;
import com.lib16.java.graphics.geometry.pathcommands.VerticalLineTo;

public final class Path
{
	private ArrayList<Command> commands = new ArrayList<>();
	private boolean ccw = false;

	public Path m(Point point)
	{
		commands.add(new MoveTo(point));
		return this;
	}

	public Path l(Point point)
	{
		commands.add(new LineTo(point));
		return this;
	}

	public Path h(double x)
	{
		commands.add(new HorizontalLineTo(x));
		return this;
	}

	public Path v(double y)
	{
		commands.add(new VerticalLineTo(y));
		return this;
	}

	public Path c(Point controlPoint1, Point controlPoint2, Point point)
	{
		commands.add(new CubicCurveTo(controlPoint1, controlPoint2, point));
		return this;
	}

	public Path s(Point controlPoint2, Point point)
	{
		commands.add(new SmoothCubicCurveTo(controlPoint2, point));
		return this;
	}

	public Path q(Point controlPoint, Point point)
	{
		commands.add(new QuadraticCurveTo(controlPoint, point));
		return this;
	}

	public Path t(Point point)
	{
		commands.add(new SmoothQuadraticCurveTo(point));
		return this;
	}

	public Path a(double rx, double ry, Angle xAxisRotation,
			boolean largeArc, boolean sweep, Point point)
	{
		commands.add(new Arc(rx, ry, xAxisRotation, largeArc, sweep, point));
		return this;
	}

	public Path z()
	{
		commands.add(new ClosePath());
		return this;
	}

	public Path polygon(Point[] points)
	{
		m(points[0]);
		for (int i = 1; i < points.length; i++) {
			l(points[i]);
		}
		z();
		return this;
	}

	public Path rectangle(Point corner, double width, double height)
	{
		Point[] points = Points.rectangle(corner, width, height);
		reorderPoints(ccw, points, 0);
		m(points[0]);
		h(points[1].getX());
		v(points[2].getY());
		h(points[3].getX());
		z();
		return this;
	}

	public Path roundedRectangle(Point corner, double width, double height, double radius)
	{
		Point[] points = Points.roundedRectangle(corner, width, height, radius);
		reorderPoints(ccw, points, 0);
		m(points[0]);
		a(radius, radius, null, false, !ccw, points[1]);
		v(points[2].getY());
		a(radius, radius, null, false, !ccw, points[3]);
		h(points[4].getX());
		a(radius, radius, null, false, !ccw, points[5]);
		v(points[6].getY());
		a(radius, radius, null, false, !ccw, points[7]);
		z();
		return this;
	}

	public Path circle(Point center, double radius)
	{
		return ellipse(center, radius, radius);
	}

	public Path ellipse(Point center, double rx, double ry)
	{
		return ellipse(center, rx, ry, new Angle(0));
	}

	public Path ellipse(Point center, double rx, double ry, Angle xAxisRotation)
	{
		Point[] points = new Point[] {
				center.copy().translateX(+rx).rotate(center, xAxisRotation),
				center.copy().translateX(-rx).rotate(center, xAxisRotation)
		};
		boolean sweep = !ccw;
		m(points[0]);
		a(rx, ry, xAxisRotation, false, sweep, points[1]);
		a(rx, ry, xAxisRotation, false, sweep, points[0]);
		return this;
	}

	/**
	 * Adds path commands for a regular (star) polygon.
	 *
	 * @param  n  Number of corners of the underlying polygon.
	 */
	public Path star(Point center, int n, double... radii)
	{
		Point[] points = Points.star(center, n, radii);
		reorderPoints(ccw, points, 1);
		polygon(points);
		return this;
	}

	public Path sector(Point center, Angle start, Angle stop, double radius)
	{
		Point[] points = Points.sector(center, start, stop, radius);
		reorderPoints(ccw, points, 1);
		m(points[0]);
		l(points[1]);
		a(radius, radius, null, largeArc(start, stop), !ccw, points[2]);
		z();
		return this;
	}

	public Path ringSector(Point center,
			Angle start, Angle stop, double radius, double innerRadius)
	{
		boolean largeArc = largeArc(start, stop);
		if (ccw) {
			Angle swap = start; start = stop; stop = swap;
		}
		Point[] points = Points.ringSector(center, start, stop, radius, innerRadius);
		m(points[0]);
		a(radius, radius, null, largeArc, !ccw, points[1]);
		l(points[2]);
		a(innerRadius, innerRadius, null, largeArc, ccw, points[3]);
		z();
		return this;
	}

	public Path ccw()
	{
		ccw = true;
		return this;
	}

	public Path cw()
	{
		ccw = false;
		return this;
	}

	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		String string = "";
		boolean first = true;
		for (Command command: commands) {
			if (!first) {
				string += " ";
			}
			else {
				first = false;
			}
			string += command.toSvg(coordinateFormat, degreeFormat);
		}
		return string;
	}

	private void reorderPoints(boolean ccw, Point[] points, int split)
	{
		if (ccw) {
			Arrays.reverse(points, split, points.length);
		}
	}

	private boolean largeArc(Angle start, Angle stop)
	{
		return stop.getRadians() - start.getRadians() >= Math.PI;
	}
}
