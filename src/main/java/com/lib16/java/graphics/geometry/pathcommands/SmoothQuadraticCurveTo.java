package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;

public final class SmoothQuadraticCurveTo extends Command
{
	public SmoothQuadraticCurveTo(Point point)
	{
		points = new Point[]{point};
	}

	@Override
	public String toSvg(NumberFormat coordinateFormat, NumberFormat degreeFormat)
	{
		return (relative ? "t " : "T ") + points[0].toSvg(coordinateFormat);
	}
}
