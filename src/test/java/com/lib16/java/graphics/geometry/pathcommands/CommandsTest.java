package com.lib16.java.graphics.geometry.pathcommands;

import java.text.NumberFormat;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.utils.NumberFormatWrapper;

public class CommandsTest
{
	@DataProvider(name = "provider")
	public static Object[][] provider()
	{
		NumberFormat cf = new NumberFormatWrapper(4).getNumberFormat();
		NumberFormat df = new NumberFormatWrapper(4).getNumberFormat();
		Point p1 = new Point(10, 20);
		Point p2 = new Point(30, 40);
		Point p3 = new Point(50, 60);
		Angle a = Angle.byDegrees(45);

		return new Object[][]
		{
			{new MoveTo(new Point(1.23456, 78.9)).toSvg(cf, df), "M 1.2346,78.9"},
			{new MoveTo(new Point(1.23456, 78.9)).rel().toSvg(cf, df), "m 1.2346,78.9"},
			{new LineTo(new Point(1.23456, 78.9)).toSvg(cf, df), "L 1.2346,78.9"},
			{new LineTo(new Point(1.23456, 78.9)).rel().toSvg(cf, df), "l 1.2346,78.9"},
			{new HorizontalLineTo(100).toSvg(cf, df), "H 100"},
			{new HorizontalLineTo(100).rel().toSvg(cf, df), "h 100"},
			{new VerticalLineTo(100).toSvg(cf, df), "V 100"},
			{new VerticalLineTo(100).rel().toSvg(cf, df), "v 100"},
			{new CubicCurveTo(p1, p2, p3).toSvg(cf, df), "C 10,20 30,40 50,60"},
			{new CubicCurveTo(p1, p2, p3).rel().toSvg(cf, df), "c 10,20 30,40 50,60"},
			{new SmoothCubicCurveTo(p1, p2).toSvg(cf, df), "S 10,20 30,40"},
			{new SmoothCubicCurveTo(p1, p2).rel().toSvg(cf, df), "s 10,20 30,40"},
			{new QuadraticCurveTo(p1, p2).toSvg(cf, df), "Q 10,20 30,40"},
			{new QuadraticCurveTo(p1, p2).rel().toSvg(cf, df), "q 10,20 30,40"},
			{new SmoothQuadraticCurveTo(p1).toSvg(cf, df), "T 10,20"},
			{new SmoothQuadraticCurveTo(p1).rel().toSvg(cf, df), "t 10,20"},
			{new Arc(10, 20, a, true, false, p1).toSvg(cf, df), "A 10 20 45 1 0 10,20"},
			{new Arc(10, 20, a, true, false, p1).rel().toSvg(cf, df), "a 10 20 45 1 0 10,20"},
			{new ClosePath().toSvg(cf, df), "Z"},
			{new ClosePath().rel().toSvg(cf, df), "z"},
		};
	}

	@Test(dataProvider = "provider")
	public void test(String actual, String expected)
	{
		Assert.assertEquals(actual, expected);
	}
}
