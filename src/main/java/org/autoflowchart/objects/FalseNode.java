package org.autoflowchart.objects;

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
}
