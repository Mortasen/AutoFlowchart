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
	public String text;
	public Node next;
	public Element falseNode;
	public int waitsFor;
	public int level;

	public Block block;

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
		this.nextFalse = nextFalse;
	}

	public Node getNext ()
	{
		return this.next;
	}

	public void setNext (Node next)
	{
		if (this.next == null && this.nextFalse == null) {
			if (this.waitsFor == 0)
				this.next = next;
			else
				this.nextFalse = next;
		} else if (this.next == null)
			this.next = next;
		else
			this.nextFalse = next;
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