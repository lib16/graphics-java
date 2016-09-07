package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class QuadraticCurveTo extends Command
{
	public QuadraticCurveTo (Point controlPoint, Point point)
	{
		points = new Point[]{controlPoint, point};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "q " : "Q ")
				+ points[0].toSvg(formatter) + " "
				+ points[1].toSvg(formatter);
	}
}
