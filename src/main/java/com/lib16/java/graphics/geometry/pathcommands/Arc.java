package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

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
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "a " : "A ")
				+ formatter.format(rx) + " "
				+ formatter.format(ry) + " "
				+ (xAxisRotation == null ? "0" : xAxisRotation.toSvg(degreeFormatter)) + " "
				+ (largeArc ? "1 " : "0 ")
				+ (sweep ? "1 " : "0 ")
				+ points[0].toSvg(formatter);
	}
}
