package org.autoflowchart.objects;

import org.autoflowchart.logic.Designer;
import org.autoflowchart.utils.Point;

import java.util.Objects;

public class Shape
{
	public int x;
	public int y;
	public int width;
	public int height;
	public ShapeType type;
	public String text;
	public int textOffsetX;
	public int textOffsetY;

	public Shape (int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Shape (int x, int y, int width, int height, ShapeType type, String text)
	{
		this(x, y, width, height);
		this.type = type;
		this.text = text;
	}

	public Shape (int x, int y, int width, int height, ShapeType type, String text, int textOffsetX, int textOffsetY)
	{
		this(x, y, width, height, type, text);
		this.textOffsetX = textOffsetX;
		this.textOffsetY = textOffsetY;
	}

	public Shape (int x, int y, Node node)
	{
		this.x = x;
		this.y = y;
		this.width = Designer.defaultWidth;
		this.height = node.getHeight();
		this.type = node.getType();
		this.text = node.getText();
		this.textOffsetX = node.getTextOffsetX();
		this.textOffsetY = node.getTextOffsetY();
	}

	public int getXFromCorner (double xk)
	{
		return this.x + (int)(this.width * xk);
	}

	public int getXFromCenter (double xk)
	{
		return this.getXFromCorner(xk / 2 + 0.5);
	}

	public int getYFromCorner (double yk)
	{
		return this.y + (int)(this.height * yk);
	}

	public int getYFromCenter (double yk)
	{
		return this.getYFromCorner(yk / 2 + 0.5);
	}

	public Point getPointFromCorner (double xk, double yk)
	{
		int x = this.getXFromCorner(xk);
		int y = this.getYFromCorner(yk);
		return new Point(x, y);
	}

	public Point getPointFromCenter (double xk, double yk)
	{
		int x = this.getXFromCenter(xk);
		int y = this.getYFromCenter(yk);
		return new Point(x, y);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Shape shape = (Shape) o;
		return x == shape.x && y == shape.y && width == shape.width && height == shape.height && type == shape.type && Objects.equals(text, shape.text);
	}

	public boolean completelyEquals (Object o)
	{
		if (this.equals(o)) {
			Shape shape = (Shape)o;
			return textOffsetX == shape.textOffsetX && textOffsetY == shape.textOffsetY;
		}
		return false;
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(x, y, width, height, type, text);
	}
}
