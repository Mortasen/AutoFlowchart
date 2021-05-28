package org.autoflowchart.objects;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockDEPRECATED extends Node
{
	public int height;
	public ShapeType type;
	public String text;
	public int textOffsetX;
	public int textOffsetY;

	public List<Shape> connectionQueue;

	public Shape shape;

	public BlockDEPRECATED next = null;
	public BlockDEPRECATED nextFalse = null;
	public int level;

	public BlockDEPRECATED () {}

	public BlockDEPRECATED (String text, int level)
	{
		this.text = text;
		this.level = level;
	}

	public BlockDEPRECATED (Node node)
	{
		this(node.getText(), node.getLevel());
	}

	public BlockDEPRECATED (int height, ShapeType type, String text, int level)
	{
		this(text, level);
		this.height = height;
		this.type = type;
	}

	public BlockDEPRECATED (int height, ShapeType type, Node node)
	{
		this(node);
		this.height = height;
		this.type = type;
	}

	public BlockDEPRECATED (int height, ShapeType type, String text, int level, BlockDEPRECATED next, BlockDEPRECATED nextFalse)
	{
		this(height, type, text, level);
		this.next = next;
		this.nextFalse = nextFalse;
	}

	public BlockDEPRECATED (int height, ShapeType type, Node node, BlockDEPRECATED next, BlockDEPRECATED nextFalse)
	{
		this(height, type, node);
		this.next = next;
		this.nextFalse = nextFalse;
	}

	public BlockDEPRECATED getNext ()
	{
		return this.next;
	}

	public void setNext (BlockDEPRECATED next)
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
		BlockDEPRECATED blockDEPRECATED = (BlockDEPRECATED) o;
		return height == blockDEPRECATED.height && level == blockDEPRECATED.level && type == blockDEPRECATED.type && Objects.equals(text, blockDEPRECATED.text) && Objects.equals(shape, blockDEPRECATED.shape);
	}

	public boolean completelyEquals (Object o)
	{
		if (this.equals(o)) {
			BlockDEPRECATED blockDEPRECATED = (BlockDEPRECATED)o;
			return textOffsetX == blockDEPRECATED.textOffsetX && textOffsetY == blockDEPRECATED.textOffsetY;
		}
		return false;
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(height, type, text, shape, level);
	}
}
