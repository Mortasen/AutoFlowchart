package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest
{

	@Test
	void parse ()
	{
		/*String precode =
				"package com.company;" +
				"class Main {" +
				"public static void main (String[] args) {";
		String postcode =
				"}}";

		// Test 1 : 3 linear nodes
		/*
		int a = 1;
		int b = 2;
		int c = a + b;
		 */
		/*System.out.println("Test 1 : 3 linear nodes");

		String mainCode =
				"int a = 1;" +
				"int b = 2;" +
				"int c = a + b;";
		String code = precode + mainCode + postcode;

		Node node0 = new Node("main()", 0);
		Node node1 = new Node("int a = 1;", 0);
		node0.next = node0;
		Node node2 = new Node("int b = 2;", 0);
		node1.next = node2;
		Node node3 = new Node("int c = a + b;", 0);
		node2.next = node3;
		Node node4 = new Node("return;");

		Block initial = new Block(Designer.defaultHeight, ShapeType.RECT, "Initial", 0);

		Block lastBlock = labeler.traverseBranch(node1, initial);
		Block actual1 = initial.next;
		Block actual2 = actual1.next;
		Block actual3 = actual2.next;

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertSame(actual3, lastBlock);

		// Test 2 : 4 nodes, 1st is condition
		/*
		if (x == 1)
			x = 0;
		else
			x = 1;
		x++;
		 */
		/*System.out.println("Test 2 : 4 nodes, 1st is condition");

		node1 = new Node("x == 1", 0);
		node2 = new Node("x = 0;", 1);
		node1.next = node2;
		node3 = new Node("x = 1;", 0);
		node1.nextFalse = node3;
		Node node4 = new Node("x++;", 0);
		node2.next = node4;
		node3.next = node4;

		initial = new Block(Designer.defaultHeight, ShapeType.RECT, "Initial", 0);

		expected1 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "x == 1", 0);
		expected2 = new Block(Designer.defaultHeight, ShapeType.RECT, "x = 0;", 1);
		expected1.next = expected2;
		expected3 = new Block(Designer.defaultHeight, ShapeType.RECT, "x = 1;", 0);
		expected1.nextFalse = expected3;
		Block expected4 = new Block(Designer.defaultHeight, ShapeType.RECT, "x++;", 0);
		expected2.next = expected4;
		expected3.next = expected4;

		lastBlock = labeler.traverseBranch(node1, initial);
		actual1 = initial.next;
		actual2 = actual1.next;
		actual3 = actual1.nextFalse;
		Block actual4 = actual2.next;

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertSame(actual2.next, actual3.next);
		assertSame(actual4, lastBlock);

		// Test 3 : 5 nodes, with cycle
		/*
		int i = 0;
		while (i < 5)
		{
			x += i;
			i++;
		}
		print(i);
		 */
		/*System.out.println("Test 3 : 5 nodes, with cycle");

		node1 = new Node("int i = 0;", 0);
		node2 = new Node("i < 5", 0);
		node1.next = node2;
		node3 = new Node("x += i;", 1);
		node2.next = node3;
		node4 = new Node("i++;", 1);
		node3.next = node4;
		node4.next = node2;
		Node node5 = new Node("print(i);", 0);
		node2.nextFalse = node5;

		initial = new Block(Designer.defaultHeight, ShapeType.RECT, "Initial", 0);

		expected1 = new Block(Designer.defaultHeight, ShapeType.RECT, "int i = 0;", 0);
		expected2 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 5", 0);
		expected1.next = expected2;
		expected3 = new Block(Designer.defaultHeight, ShapeType.RECT, "x += i;", 1);
		expected2.next = expected3;
		expected4 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		expected3.next = expected4;
		expected4.next = expected2;
		Block expected5 = new Block(Designer.defaultHeight, ShapeType.RECT, "print(i);", 0);
		expected2.nextFalse = expected5;

		lastBlock = labeler.traverseBranch(node1, initial);
		actual1 = initial.next;
		actual2 = actual1.next;
		actual3 = actual2.next;
		actual4 = actual3.next;
		Block actual5 = actual2.nextFalse;

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertSame(actual4.next, actual2);
		assertSame(actual5, lastBlock);

		// Test 4 : 7 nodes, cycle with continue and break
		/* for(int i = 0;true;i++)
		{
			if (i % 2 == 0)
				x++;
			else
				continue;
			if (x > 16)
				break;
		}
		print(x);
		 */
		/*System.out.println("Test 4 : 7 nodes, cycle with continue and break");

		node1 = new Node("int i = 0;", 0);
		node2 = new Node("true", 0);
		node1.next = node2;
		node3 = new Node("i % 2 == 0", 1);
		node2.next = node3;
		node4 = new Node("x++;", 2);
		node3.next = node4;
		node5 = new Node("x > 16", 1);
		node4.next = node5;
		Node node6 = new Node("i++;", 1);
		node3.nextFalse = node6;
		node5.nextFalse = node6;
		Node node7 = new Node("print(x);", 0);
		node2.nextFalse = node7;
		node5.next = node7;

		initial = new Block(Designer.defaultHeight, ShapeType.RECT, "Initial", 0);

		expected1 = new Block(Designer.defaultHeight, ShapeType.RECT, "int i = 0;", 0);
		expected2 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "true", 0);
		expected1.next = expected2;
		expected3 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i % 2 == 0", 1);
		expected2.next = expected3;
		expected4 = new Block(Designer.defaultHeight, ShapeType.RECT, "x++;", 2);
		expected3.next = expected4;
		expected5 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "x > 16", 1);
		expected4.next = expected5;
		Block expected6 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		expected3.nextFalse = expected6;
		expected5.nextFalse = expected6;
		Block expected7 = new Block(Designer.defaultHeight, ShapeType.RECT, "print(x);", 0);
		expected2.nextFalse = expected7;
		expected5.next = expected7;

		lastBlock = labeler.traverseBranch(node1, initial);
		actual1 = initial.next;
		actual2 = actual1.next;
		actual3 = actual2.next;
		actual4 = actual3.next;
		actual5 = actual4.next;
		Block actual6 = actual5.nextFalse;
		Block actual7 = actual2.nextFalse;

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
		assertSame(actual3.nextFalse, actual6);
		assertSame(actual5.next, actual7);
		assertSame(actual7, lastBlock);
*/
	}
}