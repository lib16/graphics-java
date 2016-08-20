package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class VerticalLineTo extends Command
{
	private double y;

	public VerticalLineTo(double y)
	{
		this.y = y;
		points = new Point[]{};
	}

	public double getY()
	{
		return y;
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "v " : "V ") + coordinateFormat.format(y);
	}
}
