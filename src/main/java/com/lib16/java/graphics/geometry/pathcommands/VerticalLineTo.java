package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

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
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "v " : "V ") + formatter.format(y);
	}
}
