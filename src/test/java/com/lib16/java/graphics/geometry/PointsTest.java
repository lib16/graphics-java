package com.lib16.java.graphics.geometry;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PointsTest
{
	@DataProvider(name = "provider")
	public static Object[][] provider()
	{
		Angle a1 = Angle.byDegrees(45);
		Angle a2 = Angle.byDegrees(135);
		double l1 = Math.sin(Math.toRadians(45)) * 100;
		double l2 = l1 / 2;

		return new Object[][]
		{
			{
				Points.rectangle(new Point(10, 20), 100, 80),
				new double[][] {{10, 20}, {110, 20}, {110, 100}, {10, 100}}
			},
			{
				Points.roundedRectangle(new Point(10, 20), 100, 80, 10),
				new double[][] {
					{100, 20}, {110, 30}, {110, 90}, {100, 100},
					{20, 100}, {10, 90}, {10, 30}, {20, 20}
				}
			},
			{
				Points.star(new Point(10, 20), 4, 100),
				new double[][] {{10, -80}, {110, 20}, {10, 120}, {-90, 20}}
			},
			{
				Points.star(new Point(10, 20), 1, 100, 50, 100, 50),
				new double[][] {{10, -80}, {60, 20}, {10, 120}, {-40, 20}}
			},
			{
				Points.sector(new Point(10, 20), a1, a2, 100),
				new double[][] {{10, 20}, {10+l1, 20+l1}, {10-l1, 20+l1}}
			},
			{
				Points.ringSector(new Point(10, 20), a1, a2, 100, 50),
				new double[][] {{10+l1, 20+l1}, {10-l1, 20+l1}, {10-l2, 20+l2}, {10+l2, 20+l2}}
			},
		};
	}

	@Test(dataProvider = "provider")
	public void test(Point[] actual, double[][] expected)
	{
		for (int i = 0; i < expected.length; i++) {
			Assert.assertEquals(actual[i].getX(), expected[i][0], 0.01);
			Assert.assertEquals(actual[i].getY(), expected[i][1], 0.01);
		}
	}
}