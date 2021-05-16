package org.autoflowchart.objects;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Node extends Element
{
	private String text;
	private FalseNode falseNode;
	private int level;

	private Block block;

	public Node () {}

	public Node (String text)
	{
		this.text = text;
	}

	public Node (String text, int level)
	{
		this(text);
		this.level = level;
	}

	public Node (String text, int level, Node next, Node nextFalse)
	{
		this(text, level);
		this.next = next;
		this.falseNode = new FalseNode(nextFalse);
	}

	public Node getNode () {
		return this;
	}

	public String getText ()
	{
		return text;
	}

	public void setText (String text)
	{
		this.text = text;
	}

	public FalseNode getFalseNode ()
	{
		return falseNode;
	}

	public void setFalseNode (FalseNode falseNode)
	{
		this.falseNode = falseNode;
	}

	public int getLevel ()
	{
		return level;
	}

	public void setLevel (int level)
	{
		this.level = level;
	}

	public Block getBlock ()
	{
		return block;
	}

	public void setBlock (Block block)
	{
		this.block = block;
	}

	public Node getNextFalse () {
		if (this.falseNode != null)
			return this.falseNode.getNode();
		else
			return null;
	}

	public void setNextFalse (Node next) {
		if (this.falseNode != null)
			this.falseNode.setNext(next);
		else
			this.falseNode = new FalseNode(next);

	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return level == node.level && Objects.equals(text, node.text);
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(text, level);
	}

}