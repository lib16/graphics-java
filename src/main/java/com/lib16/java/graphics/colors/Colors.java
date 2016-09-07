package com.lib16.java.graphics.colors;

public class Colors
{
	public static int max(int r, int g, int b)
	{
		return Math.max(r, Math.max(g, b));
	}

	public static int max(int argb)
	{
		return max(getRed(argb), getGreen(argb), getBlue(argb));
	}

	public static int min(int r, int g, int b)
	{
		return Math.min(r, Math.min(g, b));
	}

	public static int min(int argb)
	{
		return min(getRed(argb), getGreen(argb), getBlue(argb));
	}

	public static int saturation(int r, int g, int b)
	{
		return max(r, g, b) - min(r, g, b);
	}

	public static int saturation(int argb)
	{
		return saturation(getRed(argb), getGreen(argb), getBlue(argb));
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

	public static float hue(int argb)
	{
		return hue(getRed(argb), getGreen(argb), getBlue(argb));
	}

	public static int[] rgb(float hue, int saturation)
	{
		hue /= 60f;
		return new int[] {
			Math.round(saturation * Math.max(Math.min(Math.max(2f - hue, hue - 4f), 1f), 0f)),
			Math.round(saturation * Math.max(Math.min(Math.min(4f - hue, hue),      1f), 0f)),
			Math.round(saturation * Math.max(Math.min(Math.min(6f - hue, hue - 2f), 1f), 0f))
		};
	}

	public static int argb(float hue, int saturation)
	{
		int[] rgb = rgb(hue, saturation);
		return argb(rgb[0], rgb[1], rgb[2], 255);
	}

	public static int grayValue(int r, int g, int b)
	{
		return grayValue(r, g, b, 0.3f, 0.59f, 0.11f);
	}

	public static int grayValue(int r, int g , int b, float rFactor, float gFactor, float bFactor)
	{
		return Math.round(rFactor * r + gFactor * g + bFactor * b);
	}

	public static int grayValue(int argb)
	{
		return grayValue(Colors.getRed(argb), Colors.getGreen(argb), Colors.getBlue(argb));
	}

	public static int grayValue(int argb, float rFactor, float gFactor, float bFactor)
	{
		return grayValue(Colors.getRed(argb), Colors.getGreen(argb), Colors.getBlue(argb),
				rFactor, gFactor, bFactor);
	}

	public static int getRed(int argb)
	{
		return argb >> 16 & 0xff;
	}

	public static int getGreen(int argb)
	{
		return argb >> 8 & 0xff;
	}

	public static int getBlue(int argb)
	{
		return argb & 0xff;
	}

	public static int getAlpha(int argb)
	{
		return argb >> 24 & 0xff;
	}

	public static int argb(int red, int green, int blue, int alpha)
	{
		return blue | green << 8 | red << 16 | alpha << 24;
	}



	private static float delta(int med, int min, int max)
	{
		return 60f * (med - min) / (max - min);
	}
}
