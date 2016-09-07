package com.lib16.java.graphics.geometry.pathcommands;

import com.lib16.java.graphics.geometry.Command;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public final class ClosePath extends Command
{
	public ClosePath()
	{
		points = new Point[]{};
	}

	@Override
	public String toSvg(NumberFormatter formatter, NumberFormatter degreeFormatter)
	{
		return (relative ? "z" : "Z");
	}
}
