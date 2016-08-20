package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class SmoothCubicCurveTo extends Command
{
	public SmoothCubicCurveTo (Point controlPoint2, Point point)
	{
		points = new Point[]{controlPoint2, point};
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "s " : "S ")
				+ points[0].toSvg(coordinateFormat) + " "
				+ points[1].toSvg(coordinateFormat);
	}
}
