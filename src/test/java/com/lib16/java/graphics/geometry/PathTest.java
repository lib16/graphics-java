package com.lib16.java.graphics.geometry;

import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lib16.java.utils.NumberFormatWrapper;

public class PathTest
{
	@DataProvider (name = "provider")
	public static Object[][] provider()
	{
		NumberFormat nf = new NumberFormatWrapper(4).getNumberFormat();
		Angle[] angles = new Angle[3];
		String[] pointR1 = new String[3];
		String[] pointR2 = new String[3];
		int i = 0;
		for (double degrees: new double[] {30, 60, 215}) {
			angles[i] = Angle.byDegrees(degrees);
			pointR1[i] = new Point(
					angles[i].getCos() * 50 + 10,
					angles[i].getSin() * 50 + 20).toSvg(nf);
			pointR2[i] = new Point(
					angles[i].getCos() * 25 + 10,
					angles[i].getSin() * 25 + 20).toSvg(nf);

			i++;
		}
		return new Object[][] {
			// rectangle()
			{
				new Path().rectangle(new Point(10, 20), 100, 80),
				"M 10,20 H 110 V 100 H 10 Z"
			},
			{
				new Path().ccw().rectangle(new Point(10, 20), 100, 80),
				"M 10,100 H 110 V 20 H 10 Z"
			},
			// roundedRectangle()
			{
				new Path().cw().roundedRectangle(new Point(10, 20), 100, 80, 10),
				"M 100,20 A 10 10 0 0 1 110,30 " +
				"V 90 A 10 10 0 0 1 100,100 " +
				"H 20 A 10 10 0 0 1 10,90 " +
				"V 30 A 10 10 0 0 1 20,20 Z"
			},
			{
				new Path().ccw().roundedRectangle(new Point(10, 20), 100, 80, 10),
				"M 20,20 A 10 10 0 0 0 10,30 " +
				"V 90 A 10 10 0 0 0 20,100 " +
				"H 100 A 10 10 0 0 0 110,90 " +
				"V 30 A 10 10 0 0 0 100,20 Z"
			},
			// circle()
			{
				new Path().circle(new Point(10, 20), 100),
				"M 110,20 A 100 100 0 0 1 -90,20 A 100 100 0 0 1 110,20"
			},
			{
				new Path().ccw().circle(new Point(10, 20), 100),
				"M 110,20 A 100 100 0 0 0 -90,20 A 100 100 0 0 0 110,20"
			},
			// ellipse()
			{
				new Path().ellipse(new Point(10, 20), 100, 50),
				"M 110,20 A 100 50 0 0 1 -90,20 A 100 50 0 0 1 110,20"
			},
			{
				new Path().ccw().ellipse(new Point(10, 20), 100, 50, Angle.byDegrees(90)),
				"M 10,120 A 100 50 90 0 0 10,-80 A 100 50 90 0 0 10,120"
			},
			// star
			{
				new Path().star(new Point(10, 20), 4, 100),
				"M 10,-80 L 110,20 L 10,120 L -90,20 Z"
			},
			{
				new Path().ccw().star(new Point(10, 20), 4, 100),
				"M 10,-80 L -90,20 L 10,120 L 110,20 Z"
			},
			{
				new Path().star(new Point(10, 20), 2, 100, 50),
				"M 10,-80 L 60,20 L 10,120 L -40,20 Z"
			},
			{
				new Path().ccw().star(new Point(10, 20), 2, 100, 50),
				"M 10,-80 L -40,20 L 10,120 L 60,20 Z"
			},
			{
				new Path().star(new Point(10, 20), 1, 100, 50, 100, 50),
				"M 10,-80 L 60,20 L 10,120 L -40,20 Z"
			},
			{
				new Path().ccw().star(new Point(10, 20), 1, 100, 50, 100, 50),
				"M 10,-80 L -40,20 L 10,120 L 60,20 Z"
			},
			// sector()
			{
				new Path().sector(new Point(10, 20), angles[0], angles[1], 50),
				"M 10,20 L " + pointR1[0] + " A 50 50 0 0 1 " + pointR1[1] + " Z"
			},
			{
				new Path().sector(new Point(10, 20), angles[0], angles[2], 50),
				"M 10,20 L " + pointR1[0] + " A 50 50 0 1 1 " + pointR1[2] + " Z"
			},
			{
				new Path().ccw().sector(new Point(10, 20), angles[0], angles[1], 50),
				"M 10,20 L " + pointR1[1] + " A 50 50 0 0 0 " + pointR1[0] + " Z"
			},
			// ringSector()
			{
				new Path().ringSector(new Point(10, 20), angles[0], angles[1], 50, 25),
				"M " + pointR1[0] + " A 50 50 0 0 1 " + pointR1[1] + " " +
				"L " + pointR2[1] + " A 25 25 0 0 0 " + pointR2[0] + " Z"
			},
			{
				new Path().ringSector(new Point(10, 20), angles[0], angles[2], 50, 25),
				"M " + pointR1[0] + " A 50 50 0 1 1 " + pointR1[2] + " " +
				"L " + pointR2[2] + " A 25 25 0 1 0 " + pointR2[0] + " Z"
			},
			{
				new Path().ccw().ringSector(new Point(10, 20), angles[0], angles[1], 50, 25),
				"M " + pointR1[1] + " A 50 50 0 0 0 " + pointR1[0] + " " +
				"L " + pointR2[0] + " A 25 25 0 0 1 " + pointR2[1] + " Z"
			},



		};
	}

	@Test (dataProvider = "provider")
	public void test(Path path, String expected)
	{
		NumberFormat cf = new NumberFormatWrapper(4).getNumberFormat();
		NumberFormat df = new NumberFormatWrapper(4).getNumberFormat();
		String actual = path.toSvg(cf, df);
		Assert.assertEquals(actual, expected,
				"Difference: " + StringUtils.difference(actual, expected));
	}
}

/*
<?php

namespace ClacyBuilders\Svg\Tests;

require_once 'vendor/clacy-builders/xml/allIncl.php';
require_once 'vendor/clacy-builders/xml/tests/ClacyBuilders_TestCase.php';
require_once 'vendor/clacy-builders/graphics/allIncl.php';
require_once 'src/Svg.php';

use ClacyBuilders\Tests\ClacyBuilders_TestCase;
use ClacyBuilders\Graphics\Angle;
use ClacyBuilders\Graphics\Point;
use ClacyBuilders\Graphics\Points;
use ClacyBuilders\Svg\Svg;

class SvgTest extends ClacyBuilders_TestCase
{
	public function provider()
	{
		return {
				// rect()
				{
						Svg::createSub().rect([4.5, 3.5], 2, 3.2, 0.2, 0.5),
						'<rect x="4.5" y="3.5" width="2" height="3.2" rx="0.2" ry="0.5"/>'
				),
				{
						Svg::createSub().rect([4.5, 3.5], 2, 3.2),
						'<rect x="4.5" y="3.5" width="2" height="3.2"/>'
				),
				// circle()
				{
						Svg::createSub().circle([4.5, 3.5], 3.2),
						'<circle cx="4.5" cy="3.5" r="3.2"/>'
				),
				// ellipse()
				{
						Svg::createSub().ellipse([4.5, 3.5], 2, 3.2),
						'<ellipse cx="4.5" cy="3.5" rx="2" ry="3.2"/>'
				),
				// line()
				{
						Svg::createSub().line([0.1, 1.2], [2.3, 3.4]),
						'<line x1="0.1" y1="1.2" x2="2.3" y2="3.4"/>'
				),
				// polygon()
				{
						new Polygon('2,2 2,4 4,4 4,6 6,6'),
						'<polygon points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				{
						new Polygon([[2, 2], [2, 4], [4, 4], [4, 6], [6, 6]]),
						'<polygon points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				// polyline()
				{
						new Polyline('2,2 2,4 4,4 4,6 6,6'),
						'<polyline points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				{
						new Polyline([[2, 2], [2, 4], [4, 4], [4, 6], [6, 6]]),
						'<polyline points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				// setPoints()
				{
						new Polyline('2,2 2,4').setPoints('4,4 4,6 6,6'),
						'<polyline points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				{
						new Polyline('2,2 2,4').setPoints([[4, 4], [4, 6], [6, 6]]),
						'<polyline points="2,2 2,4 4,4 4,6 6,6"/>'
				),
				{
						new Polyline('2,2 2,4').setPoints([Point::create(4, 4)]),
						'<polyline points="2,2 2,4 4,4"/>'
				),
				{
						new Polyline().setPoints('2,2 2,4 4,4'),
						'<polyline points="2,2 2,4 4,4"/>'
				),
				// addPoint()
				{
						new Polyline('2,2 2,4').addPoint([4, 4]),
						'<polyline points="2,2 2,4 4,4"/>'
				),
				{
						new Polyline('').addPoint([4, 4]),
						'<polyline points="4,4"/>'
				),
				{
						new Polyline('').addPoint(Point::create(4, 4)),
						'<polyline points="4,4"/>'
				),
				{
						new Polyline().addPoint([4, 4]),
						'<polyline points="4,4"/>'
				),
				{
						new Polyline().addPoint(Point::create(4, 4)),
						'<polyline points="4,4"/>'
				),
				// path()
				{
						new Path('M 20,20 V 40 H 40 V 20 Z'),
						'<path d="M 20,20 V 40 H 40 V 20 Z"/>'
				),
				// moveTo(), lineTo(), vLineTo(), hLineTo(), closePath()
				{
						new Path()
								.moveTo([20, 20])
								.vLineTo(40)
								.hLineTo(40)
								.vLineTo(20)
								.closePath(),
						'<path d="M 20,20 V 40 H 40 V 20 Z"/>'
				),
				{
						new Path()
								.moveTo([20, 20])
								.vLineTo(20, true)
								.hLineTo(20, true)
								.vLineTo(-20, true)
								.closePath(),
						'<path d="M 20,20 v 20 h 20 v -20 Z"/>'
				),
				{
						new Path()
								.moveTo([20, 20], true)
								.lineTo([20, 40])
								.lineTo([20, 0], true)
								.lineTo([0, -20], true)
								.lineTo([-20, 0], true)
								.closePath(),
						'<path d="m 20,20 L 20,40 l 20,0 l 0,-20 l -20,0 Z"/>'
				),
				// curveTo()
				{
						new Path()
								.moveTo([20, 40])
								.curveTo([60, 0], [80, 80], [120, 40])
								.curveTo([40, 40], [-40, 60], [0, 100], true),
						'<path d="M 20,40 C 60,0 80,80 120,40 c 40,40 -40,60 0,100"/>'
				),
				// sCurveTo()
				{
						new Path()
								.moveTo([100, 100])
								.curveTo([140, 60], [160, 60], [200, 100])
								.sCurveTo([240, 160], [200, 200])
								.sCurveTo([-60, 40], [-100, 0], true)
								.sCurveTo([-40, -60], [0, -100], true),
						'<path d="M 100,100 C 140,60 160,60 200,100 S 240,160 200,200 '
						. 's -60,40 -100,0 s -40,-60 0,-100"/>'
				),
				// qCurveTo()
				{
						new Path()
								.moveTo([100, 100])
								.qCurveTo([150, 50], [200, 100])
								.qCurveTo([250, 150], [200, 200])
								.qCurveTo([-50, 50], [-100, 0], true)
								.qCurveTo([-50, -50], [0, -100], true),
						'<path d="M 100,100 Q 150,50 200,100 Q 250,150 200,200 '
						. 'q -50,50 -100,0 q -50,-50 0,-100"/>'
				),
				// sqCurveTo()
				{
						new Path()
								.moveTo([100, 100])
								.qCurveTo([150, 50], [200, 100])
								.sqCurveTo([200, 200])
								.sqCurveTo([-100, 0], true)
								.sqCurveTo([0, -100], true),
						'<path d="M 100,100 Q 150,50 200,100 T 200,200 t -100,0 t 0,-100"/>'
				),
				// arc()
				{
						new Path()
								.moveTo([400, 300])
								.arc(80, 60, 45, 1, 1, [500, 300])
								.arc(80, 60, 45, 0, 0, [-100, 0], true)
								.arc(80, 60, 45, 1, 0, [500, 300])
								.arc(80, 60, 45, 0, 1, [-100, 0], true),
						'<path d="M 400,300 A 80 60 45 1 1 500,300 a 80 60 45 0 0 -100,0 '
						. 'A 80 60 45 1 0 500,300 a 80 60 45 0 1 -100,0"/>'
				),
				// text(), setX(), setY(), setDx(), setDy(), setXY(), setDxDy(), setRotate()
				{
						Svg::createSub().text('lorem ipsum', [20, 100]),
						'<text x="20" y="100">lorem ipsum</text>'
				),
				{
						Svg::createSub().text('lorem ipsum', [0, 0]),
						'<text x="0" y="0">lorem ipsum</text>'
				),
				{
						Svg::createSub().text('lorem ipsum')
								.setY([100, 105]).setX([20, 30, 40]),
						'<text x="20 30 40" y="100 105">lorem ipsum</text>'
				),
				{
						Svg::createSub().text('lorem ipsum', [20, 100])
								.setY(105).setX([30, 40]),
						'<text x="20 30 40" y="100 105">lorem ipsum</text>'
				),
				{
						Svg::createSub().text('lorem ipsum', [10, 10], [2, 3, 4], [1, 2]),
						'<text x="10" y="10" dx="2 3 4" dy="1 2">lorem ipsum</text>'
				),
				{
						Svg::createSub().text('lorem ipsum', [10, 10], null, null, [15, 30, 45]),
						'<text x="10" y="10" rotate="15 30 45">lorem ipsum</text>'
				),
				// tspan()
				{
						Svg::createSub().tspan('lorem ipsum', [10, 40],
								[2, 3, 4], [1, 2], [15, 30, 45]),
						'<tspan x="10" y="40" dx="2 3 4" dy="1 2" rotate="15 30 45">' .
								'lorem ipsum</tspan>'
				),
				// textPath()
				{
						Svg::createSub().textPath('lorem ipsum', '#path'),
						'<textPath xlink:href="#path">lorem ipsum</textPath>'
				),
				// useElem()
				{
						Svg::createSub().useElem('#circle', [20, 30], 40, 50),
						'<use xlink:href="#circle" x="20" y="30" width="40" height="50"/>'
				),
				// defs()
				{
						Svg::createSub().defs().circle([0, 0], 20).attrib('id', 'circle'),
						"<defs>\n\t<circle cx=\"0\" cy=\"0\" r=\"20\" id=\"circle\"/>\n</defs>"
				),
				// g()
				{
						Svg::createSub().g().circle([0, 0], 20),
						"<g>\n\t<circle cx=\"0\" cy=\"0\" r=\"20\"/>\n</g>"
				),
				// title(), desc()
				{
						Svg::createSub().title('Foo Bar').getRoot().desc('lorem ipsum'),
						"<title>Foo Bar</title>\n<desc>lorem ipsum</desc>"
				),
				// image()
				{
						Svg::createSub().image('cat.jpg', [80, 20], 320, 200,
								Svg::PRESERVE_XMIDYMID),
						'<image xlink:href="cat.jpg" x="80" y="20" width="320" height="200"' .
						' preserveAspectRatio="xMidYMid"/>'
				),
				// clipPath()
				{
						Svg::createSub().clipPath('p1', Svg::UNITS_USER_SPACE_ON_USE),
						'<clipPath id="p1" clipPathUnits="userSpaceOnUse"/>'
				),
				// mask()
				{
						Svg::createSub().mask('m1', new Point(10, 20), 200, 120,
								Svg::UNITS_OBJECT_BOUNDING_BOX, Svg::UNITS_USER_SPACE_ON_USE),
						'<mask id="m1" x="10" y="20" width="200" height="120"' .
						' maskUnits="objectBoundingBox" maskContentUnits="userSpaceOnUse"/>'
				)
		);
	}

	public function testAnyPolygonPath()
	{
		$this.assertExpectedMarkup(new Path().anyPolygonPath(
				Points::polygon(Point::create(10, 20), 4, 100)),
				'<path d="M 10,-80 L 110,20 L 10,120 L -90,20 Z"/>');
	}

	public function rectanglePathProvider()
	{
		return {
		);
	}


	 * @dataProvider rectanglePathProvider

	public function testRectanglePath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function polygonPathProvider()
	{
		return {
			{
						new Path().polygonPath(new Point(10, 20), 4, 100),
						'<path d="M 10,-80 L 110,20 L 10,120 L -90,20 Z"/>'
				),
					{
						new Path().polygonPath(new Point(10, 20), 4, 100, true),
						'<path d="M 10,-80 L -90,20 L 10,120 L 110,20 Z"/>'
				)
		);
	}


	 * @dataProvider polygonPathProvider

	public function testPolygonPath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function starPathProvider()
	{
		return {
				{
						new Path().starPath(new Point(10, 20), 2, 100, 50),
						'<path d="M 10,-80 L 60,20 L 10,120 L -40,20 Z"/>'
				),
				{
						new Path().starPath(new Point(10, 20), 2, 100, 50, true),
						'<path d="M 10,-80 L -40,20 L 10,120 L 60,20 Z"/>'
				),
				{
						new Path().starPath(new Point(10, 20), 1, 100, [50, 100, 50]),
						'<path d="M 10,-80 L 60,20 L 10,120 L -40,20 Z"/>'
				),
				{
						new Path().starPath(new Point(10, 20), 1, 100, [50, 100, 50], true),
						'<path d="M 10,-80 L -40,20 L 10,120 L 60,20 Z"/>'
				)
		);
	}


	 * @dataProvider starPathProvider

	public function testStarPath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function circlePathProvider()
	{
		return {
				{
						new Path().circlePath(new Point(10, 20), 100),
						'<path d="M 110,20 A 100 100 0 0 1 -90,20 A 100 100 0 0 1 110,20"/>'
				),
				{
						new Path().circlePath(new Point(10, 20), 100, true),
						'<path d="M 110,20 A 100 100 0 0 0 -90,20 A 100 100 0 0 0 110,20"/>'
				)
		);
	}


	 * @dataProvider circlePathProvider

	public function testCirclePath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function ellipsePathProvider()
	{
		return {
				{
						new Path().ellipsePath(new Point(10, 20), 100, 50),
						'<path d="M 110,20 A 100 50 0 0 1 -90,20 A 100 50 0 0 1 110,20"/>'
				),
				{
						new Path().ellipsePath(new Point(10, 20), 100, 50, 90, true),
						'<path d="M 10,120 A 100 50 90 0 0 10,-80 A 100 50 90 0 0 10,120"/>'
				)
		);
	}


	 * @dataProvider ellipsePathProvider

	public function testEllipsePath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function sectorPathProvider()
	{
		$a = [];
		foreach ([30, 60, 215] as $i => $degrees) {
			$angle = Angle::byDegrees($degrees);
			$a[] = {
					'a' => $angle,
					's' => $angle.sin * 50 + 20,
					'c' => $angle.cos * 50 + 10
			);
		}
		return {
				{
						new Path().sectorPath(new Point(10, 20), 30, 60, 50),
						"<path d=\"M 10,20 L {$a[0]['c']},{$a[0]['s']} " .
						"A 50 50 0 0 1 {$a[1]['c']},{$a[1]['s']} Z\"/>"
				),
				{
						new Path().sectorPath(new Point(10, 20), 30, 215, 50),
						"<path d=\"M 10,20 L {$a[0]['c']},{$a[0]['s']} " .
						"A 50 50 0 1 1 {$a[2]['c']},{$a[2]['s']} Z\"/>"
				),
				{
						new Path().sectorPath(new Point(10, 20), 30, 60, 50, true),
						"<path d=\"M 10,20 L {$a[1]['c']},{$a[1]['s']} " .
						"A 50 50 0 0 0 {$a[0]['c']},{$a[0]['s']} Z\"/>"
				)
		);
	}


	 * @dataProvider sectorPathProvider

	public function testSectorPath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function ringSectorPathProvider()
	{
		$a = [];
		foreach ([30, 60, 215] as $i => $degrees) {
			$angle = Angle::byDegrees($degrees);
			foreach ([50, 25] as $r) {
				$a[] = {
						'a' => $angle,
						's' => $angle.sin * $r + 20,
						'c' => $angle.cos * $r + 10
				);
			}
		}
		return {
				{
						new Path().ringSectorPath(new Point(10, 20), 30, 60, 50, 25),
						"<path d=\"M {$a[0]['c']},{$a[0]['s']} " .
						"A 50 50 0 0 1 {$a[2]['c']},{$a[2]['s']} " .
						"L {$a[3]['c']},{$a[3]['s']} " .
						"A 25 25 0 0 0 {$a[1]['c']},{$a[1]['s']} Z\"/>"
				),
				{
						new Path().ringSectorPath(new Point(10, 20), 30, 215, 50, 25),
						"<path d=\"M {$a[0]['c']},{$a[0]['s']} " .
						"A 50 50 0 1 1 {$a[4]['c']},{$a[4]['s']} " .
						"L {$a[5]['c']},{$a[5]['s']} " .
						"A 25 25 0 1 0 {$a[1]['c']},{$a[1]['s']} Z\"/>"
				),
				{
						new Path().ringSectorPath(new Point(10, 20), 30, 60, 50, 25, true),
						"<path d=\"M {$a[2]['c']},{$a[2]['s']} " .
						"A 50 50 0 0 0 {$a[0]['c']},{$a[0]['s']} " .
						"L {$a[1]['c']},{$a[1]['s']} " .
						"A 25 25 0 0 1 {$a[3]['c']},{$a[3]['s']} Z\"/>"
				)
		);
	}


	 * @dataProvider ringSectorPathProvider

	public function testRingSectorPath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function roundedRectanglePathProvider()
	{
		return {
				{
						new Path().roundedRectanglePath(new Point(10, 20), 100, 80, 10),
						'<path d="M 100,20 A 10 10 0 0 1 110,30 L 110,90 ' .
						'A 10 10 0 0 1 100,100 L 20,100 A 10 10 0 0 1 10,90 ' .
						'L 10,30 A 10 10 0 0 1 20,20 Z"/>'
				),
				{
						new Path().roundedRectanglePath(new Point(10, 20), 100, 80, 10, true),
						'<path d="M 20,20 A 10 10 0 0 0 10,30 L 10,90 ' .
						'A 10 10 0 0 0 20,100 L 100,100 A 10 10 0 0 0 110,90 ' .
						'L 110,30 A 10 10 0 0 0 100,20 Z"/>'
				)
		);
	}


	 * @dataProvider roundedRectanglePathProvider

	public function testRoundedRectanglePath($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function starProvider()
	{
		return {
				{
						Svg::createSub().star(new Point(10, 20), 2, 100, 50),
						'<polygon points="10,-80 60,20 10,120 -40,20"/>'
				),
				{
						Svg::createSub().star(new Point(10, 20), 1, 100, [50, 100, 50]),
						'<polygon points="10,-80 60,20 10,120 -40,20"/>'
				),
		);
	}


	 * @dataProvider starProvider

	public function testStar($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}

	public function testSetViewBox()
	{
		$this.assertExpectedMarkup(
				Svg::createSub('svg').setViewBox([0, 0], 640, 400),
				'<svg viewBox="0 0 640 400"/>');
	}

	public function testSetPreserveAspectRatio()
	{
		$this.assertExpectedMarkup(
				Svg::createSub('image').setPreserveAspectRatio(Svg::PRESERVE_XMIDYMIN, true, true),
				'<image preserveAspectRatio="defer xMidYMin slice"/>');
	}

	public function filterProvider()
	{
		return {
				// filter()
				{
						Svg::createSub().filter('f2', ['-10%', '-10%'], '120%', '120%', '#f1',
								Svg::UNITS_OBJECT_BOUNDING_BOX, Svg::UNITS_USER_SPACE_ON_USE),
						'<filter id="f2" xlink:href="#f1"' .
						' x="-10%" y="-10%" width="120%" height="120%"' .
						' filterUnits="objectBoundingBox" primitiveUnits="userSpaceOnUse"/>'
				),
				// setIn()
				{
						Svg::createSub().feGaussianBlur(4).setIn(Svg::IN_SOURCE_ALPHA),
						'<feGaussianBlur in="SourceAlpha" stdDeviation="4"/>'
				),
				{
						Svg::createSub().feGaussianBlur(4).setIn('offset'),
						'<feGaussianBlur in="offset" stdDeviation="4"/>'
				),
				{
						function() {
							$offset = Svg::createSub().feOffset(2, 2).setResult('offset');
							return Svg::createSub().feGaussianBlur(4).setIn($offset);
						},
						'<feGaussianBlur in="offset" stdDeviation="4"/>'
				),
				// setResult()
				{
						Svg::createSub().feGaussianBlur(4).setResult('gb'),
						'<feGaussianBlur stdDeviation="4" result="gb"/>'
				),
				// feBlend()
				{
						Svg::createSub().feBlend('fe1', 'fe2', Svg::MODE_DARKEN, 'fe3'),
						'<feBlend in="fe1" in2="fe2" mode="darken" result="fe3"/>'
				),
				// feColorMatrix()
				{
						Svg::createSub().feColorMatrix(Svg::TYPE_MATRIX, [
								[1, 0,   0,   0, 0],
								[0, 0.2, 0,   0, 0],
								[0, 0,   0.2, 0, 0],
								[0, 0,   0,   1, 0]], 'fe1', 'fe2'),
						'<feColorMatrix in="fe1" type="matrix"' .
						' values="1 0 0 0 0 0 0.2 0 0 0 0 0 0.2 0 0 0 0 0 1 0" result="fe2"/>'
				),
				{
						Svg::createSub().feColorMatrix(Svg::TYPE_MATRIX,
								'1 0 0 0 0 0 0.2 0 0 0 0 0 0.2 0 0 0 0 0 1 0', 'fe1', 'fe2'),
						'<feColorMatrix in="fe1" type="matrix"' .
						' values="1 0 0 0 0 0 0.2 0 0 0 0 0 0.2 0 0 0 0 0 1 0" result="fe2"/>'
				),
				{
						Svg::createSub().feColorMatrix(
								Svg::TYPE_LUMINANCE_TO_ALPHA, null, 'fe1', 'fe2'),
						'<feColorMatrix in="fe1" type="luminanceToAlpha" result="fe2"/>'
				),
				// feComponentTransfer()
				{
						Svg::createSub().feComponentTransfer('fe1', 'fe2'),
						'<feComponentTransfer in="fe1" result="fe2"/>'
				),
				// feFuncIdentity(), feFuncTable(), feFuncDiscrete(), feFuncLinear(), feFuncGamma()
				{
						Svg::createSub().feFuncIdentity(Svg::CHANNEL_RGBA),
						'<feFuncR type="identity"/>' . "\n" .
						'<feFuncG type="identity"/>' . "\n" .
						'<feFuncB type="identity"/>' . "\n" .
						'<feFuncA type="identity"/>'
				),
				{
						Svg::createSub().feFuncTable(Svg::CHANNEL_RGB, [1, 0]),
						'<feFuncR type="table" tableValues="1,0"/>' . "\n" .
						'<feFuncG type="table" tableValues="1,0"/>' . "\n" .
						'<feFuncB type="table" tableValues="1,0"/>'
				),
				{
						Svg::createSub().feFuncDiscrete(Svg::CHANNEL_RGB, '0 0.5 1'),
						'<feFuncR type="discrete" tableValues="0 0.5 1"/>' . "\n" .
						'<feFuncG type="discrete" tableValues="0 0.5 1"/>' . "\n" .
						'<feFuncB type="discrete" tableValues="0 0.5 1"/>'
				),
				{
						Svg::createSub().feFuncLinear(Svg::CHANNEL_R, 0.5, 0.3),
						'<feFuncR type="linear" slope="0.5" intercept="0.3"/>'
				),
				{
						Svg::createSub().feFuncGamma(Svg::CHANNEL_G, 2, 3, 0),
						'<feFuncG type="gamma" amplitude="2" exponent="3" offset="0"/>'
				),
				// feGaussianBlur()
				{
						Svg::createSub().feGaussianBlur([4, 4], Svg::IN_BACKGROUND_ALPHA, 'gb'),
						'<feGaussianBlur in="BackgroundAlpha" stdDeviation="4 4" result="gb"/>'
				),
				// feImage()
				{
						Svg::createSub().feImage('img.jpg', Svg::PRESERVE_XMAXYMAX, 'i',
								[0, 50], 300, 200),
						'<feImage xlink:href="img.jpg" preserveAspectRatio="xMaxYMax"' .
						' x="0" y="50" width="300" height="200" result="i"/>'
				),
				// feMerge()
				{
						Svg::createSub().inLine().feMerge([null, 'fe2'], 'm'),
						'<feMerge result="m"><feMergeNode/><feMergeNode in="fe2"/></feMerge>'
				),
				// feMorphology()
				{
						Svg::createSub().feMorphology([2, 3], Svg::OPERATOR_ERODE, 'fe1', 'fe2'),
						'<feMorphology in="fe1" radius="2,3" operator="erode" result="fe2"/>'
				),
				// feOffset()
				{
						Svg::createSub().feOffset(2, 1, Svg::IN_BACKGROUND_IMAGE, 'offset'),
						'<feOffset in="BackgroundImage" dx="2" dy="1" result="offset"/>'
				),
		);
	}


	 * @dataProvider filterProvider

	public function testFilter($xml, $expectedMarkup)
	{
		$this.assertExpectedMarkup($xml, $expectedMarkup);
	}


}

*/