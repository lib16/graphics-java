package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class SmoothQuadraticCurveTo extends Command
{
	public SmoothQuadraticCurveTo(Point point)
	{
		points = new Point[]{point};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "t " : "T ") + points[0].toSvg(formatter);
	}
}
