package com.lib16.java.graphics.geometry;

import com.lib16.java.utils.NumberFormatter;

public class Angle
{
	private double radians, sin, cos, tan;

	private Angle() {}

	public Angle(double radians)
	{
		set(radians);
	}

	public static Angle byDegrees(double degrees)
	{
		return new Angle(Math.toRadians(degrees));
	}

	public Angle copy()
	{
		Angle angle = new Angle();
		angle.radians = this.radians;
		angle.sin = this.sin;
		angle.cos = this.cos;
		angle.tan = this.tan;
		return angle;
	}

	public Angle set(double radians)
	{
		this.radians = radians;
		sin = Math.sin(this.radians);
		cos = Math.cos(this.radians);
		tan = Math.tan(this.radians);
		return this;
	}

	public Angle setDegrees(double degrees)
	{
		set(Math.toRadians(degrees));
		return this;
	}

	public Angle add(double radians)
	{
		set(this.radians + radians);
		return this;
	}

	public Angle addDegrees(double degrees)
	{
		add(Math.toRadians(degrees));
		return this;
	}

	public double getRadians()
	{
		return radians;
	}

	public double getDegrees()
	{
		return Math.toDegrees(radians);
	}

	public double getSin()
	{
		return sin;
	}

	public double getCos()
	{
		return cos;
	}

	public double getTan()
	{
		return tan;
	}

	public String toSvg(NumberFormatter formatter)
	{
		return formatter.format(getDegrees());
	}
}