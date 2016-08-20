package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class HorizontalLineTo extends Command
{
	private double x;

	public HorizontalLineTo(double x)
	{
		this.x = x;
		points = new Point[]{};
	}

	public double getX()
	{
		return x;
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "h " : "H ") + coordinateFormat.format(x);
	}
}
