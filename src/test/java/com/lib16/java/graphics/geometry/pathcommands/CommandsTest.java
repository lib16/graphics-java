package com.lib16.java.graphics.geometry.pathcommands;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatter;

public class CommandsTest
{
	static final NumberFormatter F = NumberFormatter.DEFAULT_FORMATTER;

	@DataProvider(name = "provider")
	public static Object[][] provider()
	{
		Point p1 = new Point(10, 20);
		Point p2 = new Point(30, 40);
		Point p3 = new Point(50, 60);
		Angle a = Angle.byDegrees(45);

		return new Object[][]
		{
			{new MoveTo(new Point(1.23456, 78.9)).toSvg(F, F), "M 1.2346,78.9"},
			{new MoveTo(new Point(1.23456, 78.9)).rel().toSvg(F, F), "m 1.2346,78.9"},
			{new LineTo(new Point(1.23456, 78.9)).toSvg(F, F), "L 1.2346,78.9"},
			{new LineTo(new Point(1.23456, 78.9)).rel().toSvg(F, F), "l 1.2346,78.9"},
			{new HorizontalLineTo(100).toSvg(F, F), "H 100"},
			{new HorizontalLineTo(100).rel().toSvg(F, F), "h 100"},
			{new VerticalLineTo(100).toSvg(F, F), "V 100"},
			{new VerticalLineTo(100).rel().toSvg(F, F), "v 100"},
			{new CubicCurveTo(p1, p2, p3).toSvg(F, F), "C 10,20 30,40 50,60"},
			{new CubicCurveTo(p1, p2, p3).rel().toSvg(F, F), "c 10,20 30,40 50,60"},
			{new SmoothCubicCurveTo(p1, p2).toSvg(F, F), "S 10,20 30,40"},
			{new SmoothCubicCurveTo(p1, p2).rel().toSvg(F, F), "s 10,20 30,40"},
			{new QuadraticCurveTo(p1, p2).toSvg(F, F), "Q 10,20 30,40"},
			{new QuadraticCurveTo(p1, p2).rel().toSvg(F, F), "q 10,20 30,40"},
			{new SmoothQuadraticCurveTo(p1).toSvg(F, F), "T 10,20"},
			{new SmoothQuadraticCurveTo(p1).rel().toSvg(F, F), "t 10,20"},
			{new Arc(10, 20, a, true, false, p1).toSvg(F, F), "A 10 20 45 1 0 10,20"},
			{new Arc(10, 20, a, true, false, p1).rel().toSvg(F, F), "a 10 20 45 1 0 10,20"},
			{new ClosePath().toSvg(F, F), "Z"},
			{new ClosePath().rel().toSvg(F, F), "z"},
		};
	}

	@Test(dataProvider = "provider")
	public void test(String actual, String expected)
	{
		Assert.assertEquals(actual, expected);
	}
}
