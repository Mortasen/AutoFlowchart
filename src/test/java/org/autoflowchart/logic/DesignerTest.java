package org.autoflowchart.logic;

import org.autoflowchart.objects.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DesignerTest
{

	@Test
	void generateLayout () throws IOException
	{
		Saver saver = new Saver();

		System.out.println("Test 1 : 3 linear nodes");

		Designer designer = new Designer();

		Node node0 = new Node(100, ShapeType.ROUNDRECT, "main()", 0);
		Node node1 = new Node(100, ShapeType.RECT, "int a = 1;", 0);
		node0.setNext(node1);
		Node node2 = new Node(100, ShapeType.RECT, "int b = 2;", 0);
		node1.setNext(node2);
		Node node3 = new Node(100, ShapeType.RECT, "int c = a + b;", 0);
		node2.setNext(node3);
		Node node4 = new Node(100, ShapeType.ROUNDRECT, "return;", 0);
		node3.setNext(node4);

		Layout expected = new Layout();
		Shape shape0 = new Shape(0, 0, 300, 100, ShapeType.ROUNDRECT, "main()");
		expected.addShape(shape0);
		Shape shape1 = new Shape(0, 200, 300, 100, ShapeType.RECT, "int a = 1;");
		expected.addShape(shape1);
		Shape shape2 = new Shape(0, 400, 300, 100, ShapeType.RECT, "int b = 2;");
		expected.addShape(shape2);
		Shape shape3 = new Shape(0, 600, 300, 100, ShapeType.RECT, "int c = a + b;");
		expected.addShape(shape3);
		Shape shape4 = new Shape(0, 800, 300, 100, ShapeType.ROUNDRECT, "return;");
		expected.addShape(shape4);

		Arrow arrow0 = new Arrow(shape0.getPointFromCenter(0, 1));
		arrow0.addPoint(shape1.getPointFromCenter(0, -1));
		expected.addArrow(arrow0);
		Arrow arrow1 = new Arrow(shape1.getPointFromCenter(0, 1));
		arrow1.addPoint(shape2.getPointFromCenter(0, -1));
		expected.addArrow(arrow1);
		Arrow arrow2 = new Arrow(shape2.getPointFromCenter(0, 1));
		arrow2.addPoint(shape3.getPointFromCenter(0, -1));
		expected.addArrow(arrow2);
		Arrow arrow3 = new Arrow(shape3.getPointFromCenter(0, 1));
		arrow3.addPoint(shape4.getPointFromCenter(0, -1));
		expected.addArrow(arrow3);

		Layout actual = designer.generateLayout(node0);

		saver.save(expected, "src/test/resources/expected_1.png");
		saver.save(actual, "src/test/resources/actual_1.png");

		assertEquals(expected, actual);

		System.out.println("Test 2 : 4 nodes, 1st is condition");

		designer = new Designer();

		node0 = new Node(100, ShapeType.ROUNDRECT, "main()", 0);
		node1 = new Node(100, ShapeType.DIAMOND, "x == 1", 0);
		node0.setNext(node1);
		node2 = new Node(100, ShapeType.RECT, "x = 0;", 1);
		node1.setNext(node2);
		node3 = new Node(100, ShapeType.RECT, "x = 1;", 0);
		node1.setNextFalse(node3);
		node4 = new Node(100, ShapeType.RECT, "x++;", 0);
		node2.setNext(node4);
		node3.setNext(node4);
		Node node5 = new Node(100, ShapeType.ROUNDRECT, "return;", 0);
		node4.setNext(node5);

		expected = new Layout();
		shape0 = new Shape(0, 0, 300, 100, ShapeType.ROUNDRECT, "main()");
		expected.addShape(shape0);
		shape1 = new Shape(0, 200, 300, 100, ShapeType.DIAMOND, "x == 1");
		expected.addShape(shape1);
		shape2 = new Shape(400, 400, 300, 100, ShapeType.RECT, "x = 0;");
		expected.addShape(shape2);
		shape3 = new Shape(0, 600, 300, 100, ShapeType.RECT, "x = 1;");
		expected.addShape(shape3);
		shape4 = new Shape(0, 900, 300, 100, ShapeType.RECT, "x++;");
		expected.addShape(shape4);
		Shape shape5 = new Shape(0, 1100, 300, 100, ShapeType.ROUNDRECT, "return;");
		expected.addShape(shape5);

		arrow0 = new Arrow(shape0.getPointFromCenter(0, 1));
		arrow0.addPoint(shape1.getPointFromCenter(0, -1));
		expected.addArrow(arrow0);
		arrow1 = new Arrow(shape1.getPointFromCenter(1, 0));
		arrow1.addPointFromPreviousChangingX(shape2.getXFromCenter(0));
		arrow1.addPoint(shape2.getPointFromCenter(0, -1));
		expected.addArrow(arrow1);
		arrow2 = new Arrow(shape1.getPointFromCenter(0, 1));
		arrow2.addPoint(shape3.getPointFromCenter(0, -1));
		expected.addArrow(arrow2);
		arrow3 = new Arrow(shape2.getPointFromCenter(0, 1));
		arrow3.addPointFromPreviousChangingY(shape4.getYFromCenter(-1) - 100);
		arrow3.addPointFromPreviousChangingX(shape4.getXFromCenter(0.5));
		arrow3.addPoint(shape4.getPointFromCenter(0.5, -1));
		expected.addArrow(arrow3);
		Arrow arrow4 = new Arrow(shape3.getPointFromCenter(0, 1));
		arrow4.addPoint(shape4.getPointFromCenter(0, -1));
		expected.addArrow(arrow4);
		Arrow arrow5 = new Arrow(shape4.getPointFromCenter(0, 1));
		arrow5.addPoint(shape5.getPointFromCenter(0, -1));
		expected.addArrow(arrow5);

		actual = designer.generateLayout(node0);

		saver.save(expected, "src/test/resources/expected_2.png");
		saver.save(actual, "src/test/resources/actual_2.png");

		assertEquals(expected, actual);


	}

	@Test
	void placeNextBlock ()
	{
	}

	@Test
	void traverseIfBranch ()
	{
	}
}