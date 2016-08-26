package com.lib16.java.graphics.colors;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class ColorsTest
{
	@DataProvider (name = "saturationProvider")
	public static Object[][] saturationProvider()
	{
		return new Object[][] {
			{255, 255, 255, 0},
			{200, 150, 100, 100},
			{255, 210, 0, 255},
			{1000, 500, 0, 1000}
		};
	}

	@Test (dataProvider = "saturationProvider")
	public void testSaturation(int r, int g, int b, int saturation)
	{
		Assert.assertEquals(Colors.saturation(r, g, b), saturation);
	}

	@DataProvider (name = "hueProvider")
	public static Object[][] hueProvider()
	{
		return new Object[][] {
			{200, 200, 200, 0f},
			{200, 100, 100, 0f},
			{255, 235, 195, 40f},
			{235, 235, 195, 60f},
			{235, 255, 195, 80f},
			{195, 235, 195, 120f},
			{0, 60, 40, 160f},
			{10, 235, 235, 180f},
			{0, 40, 60, 200f},
			{195, 195, 255, 240f},
			{235, 195, 255, 280f},
			{255, 195, 255, 300f},
			{255, 195, 235, 320f},
			{255, 195, 225, 330f},
			{255, 195, 215, 340f},
			{1000, 500, 0, 30f}
		};
	}

	@Test (dataProvider = "hueProvider")
	public void testHue(int r, int g, int b, float hue)
	{
		Assert.assertEquals(Colors.hue(r, g, b), hue);
	}

	@DataProvider (name = "grayValueProvider")
	public static Object[][] grayValueProvider()
	{
		return new Object[][] {
			{255, 255, 255, 255},
			{200, 200, 200, 200},
			{0, 0, 0, 0},
			{1000, 1000, 1000, 1000}
		};
	}

	@Test (dataProvider = "grayValueProvider")
	public void testGrayValue(int r, int g, int b, int grayValue)
	{
		Assert.assertEquals(Colors.grayValue(r, g, b), grayValue);
	}

	@DataProvider (name = "rgbProvider")
	public static Object[][] rgbProvider()
	{
		return new Object[][] {
			{0f, 90, new int[] {90, 0, 0}},
			{20f, 90, new int[] {90, 30, 0}},
			{40f, 90, new int[] {90, 60, 0}},
			{60f, 90, new int[] {90, 90, 0}},
			{80f, 90, new int[] {60, 90, 0}},
			{100f, 90, new int[] {30, 90, 0}},
			{120f, 90, new int[] {0, 90, 0}},
			{140f, 90, new int[] {0, 90, 30}},
			{160f, 90, new int[] {0, 90, 60}},
			{180f, 90, new int[] {0, 90, 90}},
			{210f, 90, new int[] {0, 45, 90}},
			{240f, 90, new int[] {0, 0, 90}},
			{270f, 90, new int[] {45, 0, 90}},
			{300f, 90, new int[] {90, 0, 90}},
			{330f, 90, new int[] {90, 0, 45}}
		};
	}

	@Test (dataProvider = "rgbProvider")
	public void testRgb(float hue, int scale, int[] expected)
	{
		ArrayAsserts.assertArrayEquals(expected, Colors.rgb(hue, scale));
	}
}
