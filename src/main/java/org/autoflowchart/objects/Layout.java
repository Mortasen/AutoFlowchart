package org.autoflowchart.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Layout
{
	public List<Shape> shapes = new ArrayList<Shape>();
	public List<Arrow> arrows = new ArrayList<Arrow>();

	public int width;
	public int height;

	public void addShape (Shape shape)
	{
		this.shapes.add(shape);
	}

	public void addArrow (Arrow arrow)
	{
		this.arrows.add(arrow);
	}


	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Layout layout = (Layout) o;
		return Objects.equals(shapes, layout.shapes) && Objects.equals(arrows, layout.arrows);
	}

	/*@Override
	public int hashCode ()
	{
		return Objects.hash(shapes, arrows);
	}*/
}
