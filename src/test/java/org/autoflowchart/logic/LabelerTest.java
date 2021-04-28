package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelerTest
{

	@Test
	void labelNodes ()
	{
	}

	@Test
	void traverseBranch ()
	{
	}

	@Test
	void labelNode ()
	{
		Labeler labeler = new Labeler();
		Node node = new Node("test node text");
		Node nextNode = new Node("");
		node.next = nextNode;
		Block block = labeler.labelNode(node);
		Block testBlock = new Block("test node text", 2);
	}
}