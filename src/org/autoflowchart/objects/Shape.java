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
}
