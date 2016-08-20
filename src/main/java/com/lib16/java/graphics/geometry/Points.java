package com.lib16.java.graphics.geometry;

public class Points
{
	public static Point[] rectangle(Point corner, double width, double height)
	{
		Point[] points = new Point[4];
		points[0] = corner.copy();
		points[1] = points[0].copy().translateX(width);
		points[2] = points[1].copy().translateY(height);
		points[3] = points[0].copy().translateY(height);
		return points;
	}

	public static Point[] roundedRectangle(
			Point corner, double width, double height, double radius)
	{
		Point[] points = new Point[8];
		points[7] = corner.copy().translateX(radius);
		points[0] = corner.copy().translateX(width - radius);
		points[6] = corner.copy().translateY(radius);
		points[5] = corner.copy().translateY(height - radius);
		points[1] = points[6].copy().translateX(width);
		points[2] = points[5].copy().translateX(width);
		points[4] = points[7].copy().translateY(height);
		points[3] = points[0].copy().translateY(height);
		return points;
	}

	public static Point[] star(Point center, int n, double... radii)
	{
		Point[] points = new Point[n * radii.length];
		double delta = Math.toRadians(360) / n / radii.length;
		Angle angle = new Angle(0);
		for (int i = 0, k = 0; i < n; i++) {
			for (double radius: radii) {
				points[k++] = center.copy().translateY(-radius).rotate(center, angle);
				angle.add(delta);
			}
		}
		return points;
	}

	public static Point[] sector(Point center, Angle start, Angle stop, double radius)
	{
		Point[] points = new Point[3];
		points[0] = center.copy();
		points[1] = center.copy().translateX(radius).rotate(center, start);
		points[2] = center.copy().translateX(radius).rotate(center, stop);
		return points;
	}

	public static Point[] ringSector(Point center,
			Angle start, Angle stop, double radius, double innerRadius)
	{
		Point[] points = new Point[4];
		points[0] = center.copy().translateX(radius).rotate(center, start);
		points[1] = center.copy().translateX(radius).rotate(center, stop);
		points[2] = center.copy().translateX(innerRadius).rotate(center, stop);
		points[3] = center.copy().translateX(innerRadius).rotate(center, start);
		return points;
	}
}