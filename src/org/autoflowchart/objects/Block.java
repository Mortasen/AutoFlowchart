package org.autoflowchart.objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Block
{
	public int x;
	public int y;
	public int width;
	public int height;
	public ShapeType type;
	public String text;
	public int textOffsetX;
	public int textOffsetY;

	public Block next = null;
	public Block nextFalse = null;
	public int level;

	public Shape shape;

	public void draw (Canvas canvas)
	{

	}
}
