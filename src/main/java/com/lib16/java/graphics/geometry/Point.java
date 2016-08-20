package com.lib16.java.graphics.geometry;

import java.text.NumberFormat;

public class Point
{
	public static final Point ORIGIN = new Point(0, 0);

	private double x, y;

	public Point(double x, double y)
	{
		set(x, y);
	}

	public Point set(double x, double y)
	{
		this.x = x;
		this.y = y;
		return this;
	}

	public Point rotate(Point center, Angle angle)
	{
		double x = this.x - center.x;
		double y = this.y - center.y;
		this.x = x * angle.getCos() - y * angle.getSin() + center.x;
		this.y = y * angle.getCos() + x * angle.getSin() + center.y;
		return this;
	}

	public Point scale(Point center, double factor)
	{
		return scaleX(center, factor).scaleY(center, factor);
	}

	public Point scaleX(Point center, double factor)
	{
		x = scaleVal(x, center.x, factor);
		return this;
	}

	public Point scaleY(Point center, double factor)
	{
		y = scaleVal(y, center.y, factor);
		return this;
	}

	public Point skewX(Point center, Angle angle)
	{
		x += angle.getTan() * (y - center.y);
		return this;
	}

	public Point skewY(Point center, Angle angle)
	{
		y += angle.getTan() * (x - center.x);
		return this;
	}

	public Point translate(double deltaX, double deltaY)
	{
		return translateX(deltaX).translateY(deltaY);
	}

	public Point translateX(double deltaX)
	{
		x += deltaX;
		return this;
	}

	public Point translateY(double deltaY)
	{
		y += deltaY;
		return this;
	}

	public Point copy()
	{
		return new Point(x, y);
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	/**
	 * ???
	 * @param value
	 * @param offset
	 * @param factor
	 * @return
	 */
	public static double scaleVal(double value, double offset, double factor)
	{
		return (value - offset) * factor + offset;
	}

	public String toSvg(NumberFormat numberFormat)
	{
		return numberFormat.format(x) + "," + numberFormat.format(y);
	}
}