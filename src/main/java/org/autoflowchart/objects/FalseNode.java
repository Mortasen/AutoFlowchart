package org.autoflowchart.objects;

import org.autoflowchart.utils.Point;

public class FalseNode extends Element
{
	private Node node;

	public FalseNode (Node node)
	{
		this.node = node;
	}

	public FalseNode (Node node, Node next)
	{
		this(node);
		this.setNext(next);
	}

	public Node getNode ()
	{
		return this.node;
	}

	public Shape getShape ()
	{
		return this.node.getShape();
	}

	public Point getConnectionPoint ()
	{
		return this.node.getShape().getPointFromCenter(1, 0);
	}
}
