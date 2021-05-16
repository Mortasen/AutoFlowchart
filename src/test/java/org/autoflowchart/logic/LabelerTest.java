package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelerTest
{

	@Test
	void labelNodes ()
	{
		Labeler labeler = new Labeler();

		// Test 1 : complete program with all elements
		/*
		int x = 0;
		for(int i = 0; i < 1000; i++)
		{
			if (i % 2 == 0)
				continue;

			if (i < 10)
				int m = 3;
			else if (i < 100)
				int m = 2;
			else
				int m = 1;

			x += i * m;
			if (x > 100) {
				if (i < 100)
					continue;
				else
					break;
			}
			x -= 10;
		}
		print(x);
		 */
		System.out.println("Test 1 : complete program with all elements");

		Node node1 = new Node("main();", 0);
		Node node2 = new Node("int x = 0;", 0);
		node1.setNext(node2);
		Node node3 = new Node("int i = 0;", 0);
		node2.setNext(node3);
		Node node4 = new Node("i < 1000", 0);
		node3.setNext(node4);
		Node node5 = new Node("i % 2 == 0", 1);
		node4.setNext(node5);
		Node node6 = new Node("i < 10", 1);
		node5.setNextFalse(node6);
		Node node7 = new Node("int m = 3;", 2);
		node6.setNext(node7);
		Node node8 = new Node("i < 100", 1);
		node6.setNextFalse(node8);
		Node node9 = new Node("int m = 2;", 2);
		node8.setNext(node9);
		Node node10 = new Node("int m = 1;", 1);
		node8.setNextFalse(node10);
		Node node11 = new Node("x += i * m;", 1);
		node7.setNext(node11);
		node9.setNext(node11);
		node10.setNext(node11);
		Node node12 = new Node("x > 100", 1);
		node11.setNext(node12);
		Node node13 = new Node("i < 100", 2);
		node12.setNext(node13);
		Node node14 = new Node("x -= 10", 1);
		node12.setNextFalse(node14);
		Node node15 = new Node("i++;", 1);
		node5.setNext(node15);
		node13.setNext(node15);
		node14.setNext(node15);
		node15.setNext(node4);
		Node node16 = new Node("print(x);", 0);
		node4.setNextFalse(node16);
		node13.setNextFalse(node16);
		Node node17 = new Node("return;", 0);
		node16.setNext(node17);

		Block expected1 = new Block(Designer.defaultHeight, ShapeType.ROUNDRECT, "main();", 0);
		Block expected2 = new Block(Designer.defaultHeight, ShapeType.RECT, "int x = 0;", 0);
		expected1.next = expected2;
		Block expected3 = new Block(Designer.defaultHeight, ShapeType.RECT, "int i = 0;", 0);
		expected2.next = expected3;
		Block expected4 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 1000", 0);
		expected3.next = expected4;
		Block expected5 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i % 2 == 0", 1);
		expected4.next = expected5;
		Block expected6 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 10", 1);
		expected5.nextFalse = expected6;
		Block expected7 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 3;", 2);
		expected6.next = expected7;
		Block expected8 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 100", 1);
		expected6.nextFalse = expected8;
		Block expected9 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 2;", 2);
		expected8.next = expected9;
		Block expected10 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 1;", 1);
		expected8.nextFalse = expected10;
		Block expected11 = new Block(Designer.defaultHeight, ShapeType.RECT, "x += i * m;", 1);
		expected7.next = expected11;
		expected9.next = expected11;
		expected10.next = expected11;
		Block expected12 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "x > 100", 1);
		expected11.next = expected12;
		Block expected13 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 100", 2);
		expected12.next = expected13;
		Block expected14 = new Block(Designer.defaultHeight, ShapeType.RECT, "x -= 10", 1);
		expected12.nextFalse = expected14;
		Block expected15 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		expected5.next = expected15;
		expected13.next = expected15;
		expected14.next = expected15;
		expected15.next = expected4;
		Block expected16 = new Block(Designer.defaultHeight, ShapeType.RECT, "print(x);", 0);
		expected4.nextFalse = expected16;
		expected13.nextFalse = expected16;
		Block expected17 = new Block(Designer.defaultHeight, ShapeType.ROUNDRECT, "return;", 0);
		expected16.next = expected17;

		Block actual1 = labeler.labelNodes(node1);
		Block actual2 = actual1.next;
		Block actual3 = actual2.next;
		Block actual4 = actual3.next;
		Block actual5 = actual4.next;
		Block actual6 = actual5.nextFalse;
		Block actual7 = actual6.next;
		Block actual8 = actual6.nextFalse;
		Block actual9 = actual8.next;
		Block actual10 = actual8.nextFalse;
		Block actual11 = actual7.next;
		Block actual12 = actual11.next;
		Block actual13 = actual12.next;
		Block actual14 = actual12.nextFalse;
		Block actual15 = actual5.next;
		Block actual16 = actual4.nextFalse;
		Block actual17 = actual16.next;

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
		assertEquals(expected8, actual8);
		assertEquals(expected9, actual9);
		assertEquals(expected10, actual10);
		assertEquals(expected11, actual11);
		assertEquals(expected12, actual12);
		assertEquals(expected13, actual13);
		assertEquals(expected14, actual14);
		assertEquals(expected15, actual15);
		assertEquals(expected16, actual16);
		assertEquals(expected17, actual17);
		expected9.next = expected11;
		expected10.next = expected11;
		expected13.next = expected15;
		expected14.next = expected15;
		expected15.next = expected4;
		expected4.nextFalse = expected16;
		assertSame(actual9.next, actual11);
		assertSame(actual10.next, actual11);
		assertSame(actual13.next, actual15);
		assertSame(actual14.next, actual15);
		assertSame(actual15.next, actual4);
		assertSame(actual4.nextFalse, actual16);

	}

	@Test
	void traverseBranch ()
	{
		Labeler labeler = new Labeler();

		// Test 1 : 3 linear nodes
		/*
		int a = 1;
		int b = 2;
		int c = a + b;
		 */
		System.out.println("Test 1 : 3 linear nodes");

		Node node1 = new Node("int a = 1;", 0);
		Node node2 = new Node("int b = 2;", 0);
		node1.setNext(node2);
		Node node3 = new Node("int c = a + b;", 0);
		node2.setNext(node3);

		Block initial = new Block(Designer.defaultHeight, ShapeType.RECT, "Initial", 0);

		Block expected1 = new Block(Designer.defaultHeight, ShapeType.RECT, "int a = 1;", 0);
		Block expected2 = new Block(Designer.defaultHeight, ShapeType.RECT, "int b = 2;", 0);
		expected1.next = expected2;
		Block expected3 = new Block(Designer.defaultHeight, ShapeType.RECT, "int c = a + b;", 0);
		expected2.next = expected3;

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
		System.out.println("Test 2 : 4 nodes, 1st is condition");

		node1 = new Node("x == 1", 0);
		node2 = new Node("x = 0;", 1);
		node1.setNext(node2);
		node3 = new Node("x = 1;", 0);
		node1.setNextFalse(node3);
		Node node4 = new Node("x++;", 0);
		node2.setNext(node4);
		node3.setNext(node4);

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
		System.out.println("Test 3 : 5 nodes, with cycle");

		node1 = new Node("int i = 0;", 0);
		node2 = new Node("i < 5", 0);
		node1.setNext(node2);
		node3 = new Node("x += i;", 1);
		node2.setNext(node3);
		node4 = new Node("i++;", 1);
		node3.setNext(node4);
		node4.setNext(node2);
		Node node5 = new Node("print(i);", 0);
		node2.setNextFalse(node5);

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
		System.out.println("Test 4 : 7 nodes, cycle with continue and break");

		node1 = new Node("int i = 0;", 0);
		node2 = new Node("true", 0);
		node1.setNext(node2);
		node3 = new Node("i % 2 == 0", 1);
		node2.setNext(node3);
		node4 = new Node("x++;", 2);
		node3.setNext(node4);
		node5 = new Node("x > 16", 1);
		node4.setNext(node5);
		Node node6 = new Node("i++;", 1);
		node3.setNextFalse(node6);
		node5.setNextFalse(node6);
		Node node7 = new Node("print(x);", 0);
		node2.setNextFalse(node7);
		node5.setNext(node7);

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
	}

	@Test
	void labelNode ()
	{
		Labeler labeler = new Labeler();

		// Test 1 : Regular node
		System.out.println("Test 1 : Regular node");

		Node node = new Node("test node text", 0);
		node.setNext(new Node(""));

		Block expected = new Block(Designer.defaultHeight, ShapeType.RECT, "test node text", 0);
		expected.textOffsetX = 90;
		expected.textOffsetY = 41;
		Block actual = labeler.labelNode(node);

		assertTrue(actual.completelyEquals(expected));

		// Test 2 : Condition
		System.out.println("Test 2 : Condition");

		node = new Node("test node text 2", 1);
		node.setNext(new Node(""));
		node.setNextFalse(new Node(""));

		expected = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "test node text 2", 1);
		expected.textOffsetX = 82;
		expected.textOffsetY = 41;
		actual = labeler.labelNode(node);

		assertTrue(actual.completelyEquals(expected));

		/*// Test 3 : Node with two-lined text
		System.out.println("Test 3 : Node with two-lined text");

		node = new Node("---------1---------2---------3---------4", 0);
		node.next = new Node("");

		expected = new Block(Designer.defaultHeight, ShapeType.RECT, "test node text", 0);
		expected.textOffsetX = 90;
		expected.textOffsetY = 41;
		actual = labeler.labelNode(node);

		//assertTrue(actual.completelyEquals(expected));

		// Test 4 : Node with too much text even for two lines
		System.out.println("Test 3 : Node with two-lined text");

		node = new Node("---------1---------2---------3---------4---------5---------6---------7---------8", 0);
		node.next = new Node("");

		expected = new Block(Designer.defaultHeight, ShapeType.RECT, "test node text", 0);
		expected.textOffsetX = 90;
		expected.textOffsetY = 41;
		actual = labeler.labelNode(node);

		//assertTrue(actual.completelyEquals(expected));*/
	}
}