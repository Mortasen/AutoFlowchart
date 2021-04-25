package org.autoflowchart.objects;

public class Shape
{
	int x;
	int y;
	int width;
	int height;
	ShapeType type;
	String text;
	int textOffsetX;
	int textOffsetY;

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
		this.width = block.width;
		this.height = block.height;
		this.type = block.type;
		this.text = block.text;
		this.textOffsetX = block.textOffsetX;
		this.textOffsetY = block.textOffsetY;
	}

	public Point point (double xk, double yk)
	{
		int x = this.x + (int)(this.width * xk);
		int y = this.y + (int)(this.height * yk);
		return new Point(x, y);
	}

	public Point pointFromCenter (double xk, double yk)
	{
		return this.point(xk * 2 - 1, yk * 2 - 1);
	}
}
