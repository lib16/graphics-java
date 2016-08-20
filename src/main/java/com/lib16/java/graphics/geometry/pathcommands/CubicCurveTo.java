package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class CubicCurveTo extends Command
{
	public CubicCurveTo(Point controlPoint1, Point controlPoint2, Point point)
	{
		points = new Point[]{controlPoint1, controlPoint2, point};
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "c " : "C ")
				+ points[0].toSvg(coordinateFormat) + " "
				+ points[1].toSvg(coordinateFormat) + " "
				+ points[2].toSvg(coordinateFormat);
	}
}
