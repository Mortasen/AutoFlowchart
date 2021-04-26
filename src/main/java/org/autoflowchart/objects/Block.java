package org.autoflowchart.objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Block
{
	public int height;
	public ShapeType type;
	public String text;
	public int textOffsetX;
	public int textOffsetY;

	public Block next = null;
	public Block nextFalse = null;
	public int level;

	public Shape shape;

	public Block () {}

	public Block (String text, int level)
	{
		this.text = text;
		this.level = level;
	}

	public Block (Node node)
	{
		this(node.text, node.level);
	}

	public Block (int height, ShapeType type, String text, int level)
	{
		this(text, level);
		this.height = height;
		this.type = type;
	}

	public Block (int height, ShapeType type, Node node)
	{
		this(node);
		this.height = height;
		this.type = type;
	}

	public void draw (Canvas canvas)
	{

	}
}
