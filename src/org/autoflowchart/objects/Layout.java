package org.autoflowchart.objects;

import java.util.ArrayList;
import java.util.List;

public class Layout
{
	List<Shape> shapes = new ArrayList<Shape>();
	List<Arrow> arrows = new ArrayList<Arrow>();

	public void addShape (Shape shape)
	{
		this.shapes.add(shape);
	}

	public void addShape (int x, int y, int width, int height, ShapeType type, String text, int textOffsetX, int textOffsetY)
	{
		Shape shape = new Shape(x, y, width, height, type, text, textOffsetX, textOffsetY);
		this.addShape(shape);
	}

	public void addArrow (Arrow arrow)
	{
		this.arrows.add(arrow);
	}

	public void addArrow (int xPoints[], int yPoints[])
	{
		Arrow arrow = new Arrow(xPoints, yPoints);
		this.addArrow(arrow);
	}
}
