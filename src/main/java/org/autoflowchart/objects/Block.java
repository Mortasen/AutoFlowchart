package org.autoflowchart.objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Block
{
	public int height;
	public ShapeType type;
	public String text;
	public int textOffsetX;
	public int textOffsetY;

	public List<Shape> connectionQueue;

	public Shape shape;

	public Block next = null;
	public Block nextFalse = null;
	public int level;

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

	public Block (int height, ShapeType type, String text, int level, Block next, Block nextFalse)
	{
		this(height, type, text, level);
		this.next = next;
		this.nextFalse = nextFalse;
	}

	public Block (int height, ShapeType type, Node node, Block next, Block nextFalse)
	{
		this(height, type, node);
		this.next = next;
		this.nextFalse = nextFalse;
	}

	public Block getNext ()
	{
		return this.next;
	}

	public void setNext (Block next)
	{
		if (this.next == null)
			this.next = next;
		else
			this.nextFalse = next;
	}

	public void addToConnectionQueue (Shape shape)
	{
		if (this.connectionQueue == null)
			this.connectionQueue = new ArrayList<Shape>();
		this.connectionQueue.add(shape);
	}

	public void draw (Canvas canvas)
	{

	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Block block = (Block) o;
		return height == block.height && level == block.level && type == block.type && Objects.equals(text, block.text) && Objects.equals(shape, block.shape);
	}

	public boolean completelyEquals (Object o)
	{
		if (this.equals(o)) {
			Block block = (Block)o;
			return textOffsetX == block.textOffsetX && textOffsetY == block.textOffsetY;
		}
		return false;
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(height, type, text, shape, level);
	}
}
