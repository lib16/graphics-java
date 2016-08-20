package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class Arc extends Command
{
	private double rx, ry;
	private Angle xAxisRotation;
	private boolean largeArc, sweep;

	public Arc(double rx, double ry, Angle xAxisRotation,
			boolean largeArc, boolean sweep, Point point)
	{
		this.rx = rx;
		this.ry = ry;
		this.xAxisRotation = xAxisRotation;
		this.largeArc = largeArc;
		this.sweep = sweep;
		points = new Point[]{point};
	}

	public double getRx()
	{
		return rx;
	}

	public double getRy()
	{
		return ry;
	}

	public Angle getXAxisRotation()
	{
		return xAxisRotation;
	}

	public boolean isLargeArc()
	{
		return largeArc;
	}

	public boolean isSweep()
	{
		return sweep;
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "a " : "A ")
				+ coordinateFormat.format(rx) + " "
				+ coordinateFormat.format(ry) + " "
				+ (xAxisRotation == null ? "0" : xAxisRotation.toSvg(degreeFormat)) + " "
				+ (largeArc ? "1 " : "0 ")
				+ (sweep ? "1 " : "0 ")
				+ points[0].toSvg(coordinateFormat);
	}
}
