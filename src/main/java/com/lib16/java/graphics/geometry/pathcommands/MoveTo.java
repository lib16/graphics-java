package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class MoveTo extends Command
{
	public MoveTo(Point point)
	{
		points = new Point[]{point};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "m " : "M ") + points[0].toSvg(formatter);
	}
}
