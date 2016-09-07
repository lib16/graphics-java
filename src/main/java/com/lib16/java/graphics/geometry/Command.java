package com.lib16.java.graphics.geometry;

import com.lib16.java.utils.NumberFormatter;

public abstract class Command
{
	protected Point[] points;

	protected boolean relative = false;

	public Point[] getPoints()
	{
		return points;
	}

	public Command rel()
	{
		this.relative = true;
		return this;
	}

	public boolean isRelative()
	{
		return relative;
	}

	public abstract String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter);
}
