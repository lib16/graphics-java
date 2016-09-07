package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class HorizontalLineTo extends Command
{
	private double x;

	public HorizontalLineTo(double x)
	{
		this.x = x;
		points = new Point[]{};
	}

	public double getX()
	{
		return x;
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "h " : "H ") + formatter.format(x);
	}
}
