package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class CubicCurveTo extends Command
{
	public CubicCurveTo(Point controlPoint1, Point controlPoint2, Point point)
	{
		points = new Point[]{controlPoint1, controlPoint2, point};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "c " : "C ")
				+ points[0].toSvg(formatter) + " "
				+ points[1].toSvg(formatter) + " "
				+ points[2].toSvg(formatter);
	}
}
