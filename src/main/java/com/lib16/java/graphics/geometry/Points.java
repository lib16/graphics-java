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

	public static final double STAR_RADIUS_5_2 = innerRadiusStar(5, 2);
	public static final double STAR_RADIUS_6_2 = innerRadiusStar(6, 2);
	public static final double STAR_RADIUS_7_2 = innerRadiusStar(7, 2);
	public static final double STAR_RADIUS_7_3 = innerRadiusStar(7, 3);
	public static final double STAR_RADIUS_8_2 = innerRadiusStar(8, 2);
	public static final double STAR_RADIUS_8_3 = innerRadiusStar(8, 3);

	/**
	 * Calculates the inner radius of a star polygon.
	 *
	 * Assumes that circumradius is 1.
	 *
	 * @param  n  Number of corners.
	 * @param  m  2 <= m < n/2
	 */
	public static double innerRadiusStar(int n, int m)
	{
		return Math.cos(Math.PI * m / n) / Math.cos(Math.PI * (m - 1) / n);
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

	public static void rotate(Point center, Angle angle, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.rotate(center, angle);
			}
		}
	}

	public static void rotate(Angle angle, Point[]...points)
	{
		rotate(Point.ORIGIN, angle, points);
	}

	public static void scale(Point center, double factor, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.scale(center, factor);
			}
		}
	}

	public static void scale(double factor, Point[]... points)
	{
		scale(Point.ORIGIN, factor, points);
	}

	public static void scaleX(Point center, double factor, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.scaleX(center, factor);
			}
		}
	}

	public static void scaleX(double factor, Point[]... points)
	{
		scaleX(Point.ORIGIN, factor, points);
	}

	public static void scaleY(Point center, double factor, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.scaleY(center, factor);
			}
		}
	}

	public static void scaleY(double factor, Point[]... points)
	{
		scaleY(Point.ORIGIN, factor, points);
	}

	public static void skewX(Point center, Angle angle, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.skewX(center, angle);
			}
		}
	}

	public static void skewX(Angle angle, Point[]... points)
	{
		skewX(Point.ORIGIN, angle, points);
	}

	public static void skewY(Point center, Angle angle, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.skewY(center, angle);
			}
		}
	}

	public static void skewY(Angle angle, Point[]... points)
	{
		skewY(Point.ORIGIN, angle, points);
	}

	public static void translate(double deltaX, double deltaY, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.translate(deltaX, deltaY);
			}
		}
	}

	public static void translateX(double deltaX, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.translateX(deltaX);
			}
		}
	}

	public static void translateY(double deltaY, Point[]... points)
	{
		for (Point[] pointArray: points) {
			for (Point point: pointArray) {
				point.translateY(deltaY);
			}
		}
	}
}