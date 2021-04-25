package org.autoflowchart.objects;

import java.util.ArrayList;

class Nodes extends Node
{
	public ArrayList<Node> nodes = new ArrayList<Node>();

	public void add (Node node)
	{
		if (node == null)
			node = null;
		this.nodes.add(node);
	}

	public Node getNext ()
	{
		return this.nodes.get(0).getNext();
	}

	public void setNext (Node next)
	{
		for (Node node : this.nodes)
			node.setNext(next);
	}
}