package com.lib16.java.graphics.colors;

public class Colors
{
	public static int max(int r, int g, int b)
	{
		return Math.max(r, Math.max(g, b));
	}

	public static int min(int r, int g, int b)
	{
		return Math.min(r, Math.min(g, b));
	}

	public static int saturation(int r, int g, int b)
	{
		return max(r, g, b) - min(r, g, b);
	}

	public static float hue(int r, int g, int b)
	{
		int min = min(r, g, b);
		int max = max(r, g, b);
		if (r == g && g == b)
			return 0;
		if (r == max && b == min)
			return delta(g, min, max);
		if (g == max)
			if (b == min)
				return 120 - delta(r, min, max);
			else
				return 120 + delta(b, min, max);
		if (b == max)
			if (r == min)
				return 240 - delta(g, min, max);
			else
				return 240 + delta(r, min, max);
		return 360 - delta(b, min, max); // r == max && g == min
	}

	public static int[] rgb(float hue, int scale)
	{
		hue /= 60f;
		return new int[] {
			Math.round(scale * Math.max(Math.min(Math.max(2f - hue, hue - 4f), 1f), 0f)),
			Math.round(scale * Math.max(Math.min(Math.min(4f - hue, hue),      1f), 0f)),
			Math.round(scale * Math.max(Math.min(Math.min(6f - hue, hue - 2f), 1f), 0f))
		};
	}

	public static int grayValue(int r, int g, int b)
	{
		return grayValue(r, g, b, 0.3f, 0.59f, 0.11f);
	}

	public static int grayValue(int r, int g , int b, float rFactor, float gFactor, float bFactor)
	{
		return Math.round(rFactor * r + gFactor * g + bFactor * b);
	}

	private static float delta(int med, int min, int max)
	{
		return 60f * (med - min) / (max - min);
	}
}
