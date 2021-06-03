package org.autoflowchart.logic;

import org.autoflowchart.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest
{
	public static final String precode =
			"package com.company;" +
			"public class Main {" +
			"public static void main (String[] args) {";
	public static final String postcode =
			"}}";

	Designer designer;
	Saver saver;
	Layout expected;
	Layout actual;

	@BeforeEach
	void init ()
	{
		this.designer = new Designer();
		this.saver = new Saver();
		this.expected = new Layout();
	}

	@Test
	void process1 () throws IOException
	{
		String mainCode =
				"int a = 1;" +
				"int b = 2;" +
				"int c = a + b;";

		String code = precode + mainCode + postcode;

		Node firstNode = Parser.parse(code);
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

		saver.save(expected, "src/test/resources/expected_1.png");

		Layout actual = designer.generateLayout(firstNode);
		saver.save(actual, "src/test/resources/p_actual_1.png");

		assertEquals(expected, actual);
	}
}