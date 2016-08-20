package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class QuadraticCurveTo extends Command
{
	public QuadraticCurveTo (Point controlPoint, Point point)
	{
		points = new Point[]{controlPoint, point};
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "q " : "Q ")
				+ points[0].toSvg(coordinateFormat) + " "
				+ points[1].toSvg(coordinateFormat);
	}
}
