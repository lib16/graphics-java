package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class SmoothCubicCurveTo extends Command
{
	public SmoothCubicCurveTo (Point controlPoint2, Point point)
	{
		points = new Point[]{controlPoint2, point};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "s " : "S ")
				+ points[0].toSvg(formatter) + " "
				+ points[1].toSvg(formatter);
	}
}
