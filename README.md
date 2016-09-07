# Lib16 graphics for Java 8

## Dependency Information

```xml
<dependency>
    <groupId>com.lib16</groupId>
    <artifactId>graphics</artifactId>
    <version>1.1</version>
</dependency>
```
See also http://search.maven.org/#artifactdetails|com.lib16|graphics|1.1|jar

## Basic example
```java
package com.lib16.java.example.graphics.colors;

import com.lib16.java.arrays.Arrays;
import com.lib16.java.graphics.geometry.Angle;
import com.lib16.java.graphics.geometry.Path;
import com.lib16.java.graphics.geometry.Point;
import com.lib16.java.graphics.geometry.Points;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class GraphicsReadmeDemo extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Point center1 = new Point(100, 150);
		Point center2 = new Point(80, 290);
		Point center3 = new Point(210, 290);
		Point[] star = Points.star(center3, 7, 40, 40 * Points.STAR_RADIUS_7_3);
		Point[] rect = Points.rectangle(center3.copy().translate(-50, -52), 100, 100);
		Points.skewX(center3, Angle.byDegrees(-30), star, rect);
		Arrays.reverse(star);
		Path path = new Path()
				.star(new Point(50, 50), 5, 40, 40 * Points.STAR_RADIUS_5_2)
				.star(new Point(150, 50), 8, 40 * Points.STAR_RADIUS_8_3, 40)
				.star(new Point(250, 50), 6, 30, 40, 40)
				.star(center1, 6, 70)
				.ccw()
				.ellipse(center1, 60, 20, Angle.byDegrees(30))
				.ellipse(center1, 60, 20, Angle.byDegrees(90))
				.ellipse(center1, 60, 20, Angle.byDegrees(150))
				.cw()
				.rectangle(new Point(170, 95), 110, 110)
				.ccw().roundedRectangle(new Point(175, 100), 100, 100, 20).cw()
				.circle(new Point(225, 150), 45)
				.sector(center2, Angle.byDegrees(30), Angle.byDegrees(175), 50)
				.sector(center2, Angle.byDegrees(290), Angle.byDegrees(325), 50)
				.ringSector(center2, Angle.byDegrees(175), Angle.byDegrees(275), 60, 50)
				.polygon(rect)
				.polygon(star);

		Paint color = Paint.valueOf("#26ac");
		SVGPath svg = new SVGPath();
		svg.setFill(color);
		svg.setStroke(Paint.valueOf("#fff"));
		svg.setContent(path.toSvg());

		HBox box = new HBox();
		HBox.setMargin(svg, new Insets(20));;
		box.getChildren().add(svg);
		Group root = new Group();
		root.getChildren().add(box);
		Scene scene = new Scene(root);
		scene.setFill(color);
		primaryStage.setTitle("Graphics Demo");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
```
