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
}
