package org.autoflowchart.objects;

import org.autoflowchart.logic.Designer;

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

	public Shape (int x, int y, int width, int height, ShapeType type, String text, int textOffsetX, int textOffsetY)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.text = text;
		this.textOffsetX = textOffsetX;
		this.textOffsetY = textOffsetY;
	}

	public Shape (int x, int y, Block block)
	{
		this.x = x;
		this.y = y;
		this.width = Designer.defaultWidth;
		this.height = block.height;
		this.type = block.type;
		this.text = block.text;
		this.textOffsetX = block.textOffsetX;
		this.textOffsetY = block.textOffsetY;
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
}
