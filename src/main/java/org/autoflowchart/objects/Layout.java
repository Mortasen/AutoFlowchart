package org.autoflowchart.objects;

import java.util.ArrayList;
import java.util.List;

public class Layout
{
	public List<Shape> shapes = new ArrayList<Shape>();
	public List<Arrow> arrows = new ArrayList<Arrow>();

	public void addShape (Shape shape)
	{
		this.shapes.add(shape);
	}

	public void addArrow (Arrow arrow)
	{
		this.arrows.add(arrow);
	}
}
