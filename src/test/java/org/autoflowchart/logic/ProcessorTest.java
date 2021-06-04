package org.autoflowchart.logic;

import org.autoflowchart.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



class ProcessorTest
{
	public static final String precode =
			"package com.company;\n\n" +
			"public class Main {\n" +
			"public static void main (String[] args) {\n";
	public static final String postcode =
			"\n}\n}";

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

		for (int i = 0; i < expected.shapes.size(); i++) {
			if (!expected.shapes.get(i).equals(actual.shapes.get(i)))
				System.out.println("Shape" + i);
		}

		for (int i = 0; i < expected.arrows.size(); i++) {
			if (!expected.arrows.get(i).equals(actual.arrows.get(i)))
				System.out.println("Arrow" + i);
		}

		assertEquals(expected, actual);
	}

	@Test
	void process2 () throws IOException
	{
		String mainCode =
				"if (x == 1)" +
				"	x = 0;" +
				"else" +
				"	x = 1;" +
				"x++;";

		String code = precode + mainCode + postcode;

		Node firstNode = Parser.parse(code);
		Shape shape0 = new Shape(0, 0, 300, 100, ShapeType.ROUNDRECT, "main()");
		expected.addShape(shape0);
		Shape shape1 = new Shape(0, 200, 300, 100, ShapeType.DIAMOND, "x == 1");
		expected.addShape(shape1);
		Shape shape2 = new Shape(400, 400, 300, 100, ShapeType.RECT, "x = 0;");
		expected.addShape(shape2);
		Shape shape3 = new Shape(0, 600, 300, 100, ShapeType.RECT, "x = 1;");
		expected.addShape(shape3);
		Shape shape4 = new Shape(0, 800, 300, 100, ShapeType.RECT, "x++;");
		expected.addShape(shape4);
		Shape shape5 = new Shape(0, 1000, 300, 100, ShapeType.ROUNDRECT, "return;");
		expected.addShape(shape5);

		Arrow arrow0 = new Arrow(shape0.getPointFromCenter(0, 1));
		arrow0.addPoint(shape1.getPointFromCenter(0, -1));
		expected.addArrow(arrow0);
		Arrow arrow1 = new Arrow(shape1.getPointFromCenter(1, 0));
		arrow1.addPointFromPreviousChangingX(shape2.getXFromCenter(0));
		arrow1.addPoint(shape2.getPointFromCenter(0, -1));
		expected.addArrow(arrow1);
		Arrow arrow2 = new Arrow(shape1.getPointFromCenter(0, 1));
		arrow2.addPoint(shape3.getPointFromCenter(0, -1));
		expected.addArrow(arrow2);
		Arrow arrow3 = new Arrow(shape2.getPointFromCenter(0, 1));
		arrow3.addPointFromPrevious(0, Designer.DEFAULT_GAP_Y / 2);
		arrow3.addPointFromPreviousChangingY(shape4.getYFromCenter(-1) - Designer.DEFAULT_GAP_Y / 2);
		arrow3.addPointFromPreviousChangingX(shape4.getXFromCenter(0.5));
		arrow3.addPoint(shape4.getPointFromCenter(0.5, -1));
		expected.addArrow(arrow3);
		Arrow arrow4 = new Arrow(shape3.getPointFromCenter(0, 1));
		arrow4.addPoint(shape4.getPointFromCenter(0, -1));
		expected.addArrow(arrow4);
		Arrow arrow5 = new Arrow(shape4.getPointFromCenter(0, 1));
		arrow5.addPoint(shape5.getPointFromCenter(0, -1));
		expected.addArrow(arrow5);

		saver.save(expected, "src/test/resources/expected_2.png");

		Layout actual = designer.generateLayout(firstNode);
		saver.save(actual, "src/test/resources/p_actual_2.png");

		for (int i = 0; i < expected.shapes.size(); i++) {
			if (!expected.shapes.get(i).equals(actual.shapes.get(i)))
				System.out.println("Shape" + i);
		}

		for (int i = 0; i < expected.arrows.size(); i++) {
			if (!expected.arrows.get(i).equals(actual.arrows.get(i)))
				System.out.println("Arrow" + i);
		}

		assertEquals(expected, actual);
	}

	@Test
	void process3 () throws IOException
	{
		String mainCode =
				"int i = 0;" +
				"while (i < 5)" +
				"{" +
				"	x += i;" +
				"	i++;" +
				"}" +
				"print(i);";

		String code = precode + mainCode + postcode;

		Node firstNode = Parser.parse(code);

		Shape shape0 = new Shape(0, 0, 300, 100, ShapeType.ROUNDRECT, "main()");
		expected.addShape(shape0);
		Shape shape1 = new Shape(0, 200, 300, 100, ShapeType.RECT, "int i = 0;");
		expected.addShape(shape1);
		Shape shape2 = new Shape(0, 400, 300, 100, ShapeType.DIAMOND, "i < 5");
		expected.addShape(shape2);
		Shape shape3 = new Shape(400, 600, 300, 100, ShapeType.RECT, "x += i;");
		expected.addShape(shape3);
		Shape shape4 = new Shape(400, 800, 300, 100, ShapeType.RECT, "i++;");
		expected.addShape(shape4);
		Shape shape5 = new Shape(0, 1100, 300, 100, ShapeType.RECT, "print(i);");
		expected.addShape(shape5);
		Shape shape6 = new Shape(0, 1300, 300, 100, ShapeType.ROUNDRECT, "return;");
		expected.addShape(shape6);

		Arrow arrow0 = new Arrow(shape0.getPointFromCenter(0, 1));
		arrow0.addPoint(shape1.getPointFromCenter(0, -1));
		expected.addArrow(arrow0);
		Arrow arrow1 = new Arrow(shape1.getPointFromCenter(0, 1));
		arrow1.addPoint(shape2.getPointFromCenter(0, -1));
		expected.addArrow(arrow1);
		Arrow arrow2 = new Arrow(shape2.getPointFromCenter(1, 0));
		arrow2.addPointFromPreviousChangingX(shape3.getXFromCenter(0));
		arrow2.addPoint(shape3.getPointFromCenter(0, -1));
		expected.addArrow(arrow2);
		Arrow arrow3 = new Arrow(shape3.getPointFromCenter(0, 1));
		arrow3.addPoint(shape4.getPointFromCenter(0, -1));
		expected.addArrow(arrow3);
		Arrow arrow4 = new Arrow(shape4.getPointFromCenter(0, 1));
		arrow4.addPointFromPrevious(0, 100);
		arrow4.addPointFromPreviousChangingX(shape2.getXFromCenter(0.5));
		arrow4.addPoint(shape2.getPointFromCenter(0.5, 1));
		expected.addArrow(arrow4);
		Arrow arrow5 = new Arrow(shape2.getPointFromCenter(0, 1));
		arrow5.addPoint(shape5.getPointFromCenter(0, -1));
		expected.addArrow(arrow5);
		Arrow arrow6 = new Arrow(shape5.getPointFromCenter(0, 1));
		arrow6.addPoint(shape6.getPointFromCenter(0, -1));
		expected.addArrow(arrow6);

		saver.save(expected, "src/test/resources/expected_3.png");

		Layout actual = designer.generateLayout(firstNode);
		saver.save(actual, "src/test/resources/p_actual_3.png");

		for (int i = 0; i < expected.shapes.size(); i++) {
			if (!expected.shapes.get(i).equals(actual.shapes.get(i)))
				System.out.println("Shape" + i);
		}

		for (int i = 0; i < expected.arrows.size(); i++) {
			if (!expected.arrows.get(i).equals(actual.arrows.get(i)))
				System.out.println("Arrow" + i);
		}

		assertEquals(expected, actual);
	}

	@Test
	void process4 () throws IOException
	{
		String mainCode =
				"for (int i = 0;true;i++)" +
				"{" +
				"	if (i % 2 == 0)" +
				"		x++;" +
				"	else" +
				"		continue;" +
				"	if (x > 16)" +
				"		break;" +
				"}" +
				"print(x);";

		String code = precode + mainCode + postcode;

		Node firstNode = Parser.parse(code);
		Shape shape0 = new Shape(0, 0, 300, 100, ShapeType.ROUNDRECT, "main()");
		expected.addShape(shape0);
		Shape shape1 = new Shape(0, 200, 300, 100, ShapeType.RECT, "int i = 0;");
		expected.addShape(shape1);
		Shape shape2 = new Shape(0, 400, 300, 100, ShapeType.DIAMOND, "true");
		expected.addShape(shape2);
		Shape shape3 = new Shape(400, 600, 300, 100, ShapeType.DIAMOND, "i % 2 == 0");
		expected.addShape(shape3);
		Shape shape4 = new Shape(800, 800, 300, 100, ShapeType.RECT, "x++;");
		expected.addShape(shape4);
		Shape shape5 = new Shape(400, 1000, 300, 100, ShapeType.DIAMOND, "x > 16");
		expected.addShape(shape5);
		Shape shape6 = new Shape(400, 1200, 300, 100, ShapeType.RECT, "i++;");
		expected.addShape(shape6);
		Shape shape7 = new Shape(0, 1500, 300, 100, ShapeType.RECT, "print(x);");
		expected.addShape(shape7);
		Shape shape8 = new Shape(0, 1700, 300, 100, ShapeType.ROUNDRECT, "return;");
		expected.addShape(shape8);

		Arrow arrow1 = new Arrow(shape0.getPointFromCenter(0, 1));
		arrow1.addPoint(shape1.getPointFromCenter(0, -1));
		expected.addArrow(arrow1);
		Arrow arrow2 = new Arrow(shape1.getPointFromCenter(0, 1));
		arrow2.addPoint(shape2.getPointFromCenter(0, -1));
		expected.addArrow(arrow2);
		Arrow arrow3 = new Arrow(shape2.getPointFromCenter(1, 0));
		arrow3.addPointFromPreviousChangingX(shape3.getXFromCenter(0));
		arrow3.addPoint(shape3.getPointFromCenter(0, -1));
		expected.addArrow(arrow3);
		Arrow arrow4 = new Arrow(shape3.getPointFromCenter(1, 0));
		arrow4.addPointFromPreviousChangingX(shape4.getXFromCenter(0));
		arrow4.addPoint(shape4.getPointFromCenter(0, -1));
		expected.addArrow(arrow4);
		Arrow arrow5 = new Arrow(shape4.getPointFromCenter(0, 1));
		arrow5.addPointFromPrevious(0, Designer.DEFAULT_GAP_Y / 2);
		arrow5.addPointFromPrevious(0, 0);
		arrow5.addPointFromPreviousChangingX(shape5.getXFromCenter(0.5));
		arrow5.addPoint(shape5.getPointFromCenter(0.5, -1));
		expected.addArrow(arrow5);
		Arrow arrow6 = new Arrow(shape3.getPointFromCenter(0, 1));
		arrow6.addPointFromPrevious(0, Designer.DEFAULT_GAP_Y / 2);
		arrow6.addPointFromPrevious(600, 0);
		arrow6.addPointFromPreviousChangingY(shape6.getYFromCenter(-1) - Designer.DEFAULT_GAP_Y / 2);
		arrow6.addPointFromPreviousChangingX(shape6.getXFromCenter(0.5));
		arrow6.addPoint(shape6.getPointFromCenter(0.5, -1));
		expected.addArrow(arrow6);
		Arrow arrow7 = new Arrow(shape5.getPointFromCenter(0, 1));
		arrow7.addPoint(shape6.getPointFromCenter(0, -1));
		expected.addArrow(arrow7);
		Arrow arrow8 = new Arrow(shape6.getPointFromCenter(0, 1));
		arrow8.addPointFromPrevious(0, 100);
		arrow8.addPointFromPreviousChangingX(shape2.getXFromCenter(0.5));
		arrow8.addPoint(shape2.getPointFromCenter(0.5, 1));
		expected.addArrow(arrow8);
		Arrow arrow9 = new Arrow(shape5.getPointFromCenter(1, 0));
		arrow9.addPointFromPrevious(Designer.DEFAULT_GAP_X / 2, 0);
		arrow9.addPointFromPreviousChangingY(shape7.getYFromCenter(-1) - Designer.DEFAULT_GAP_Y / 2);
		arrow9.addPointFromPreviousChangingX(shape7.getXFromCenter(0.5));
		arrow9.addPoint(shape7.getPointFromCenter(0.5, -1));
		expected.addArrow(arrow9);
		Arrow arrow10 = new Arrow(shape2.getPointFromCenter(0, 1));
		arrow10.addPoint(shape7.getPointFromCenter(0, -1));
		expected.addArrow(arrow10);
		Arrow arrow11 = new Arrow(shape7.getPointFromCenter(0, 1));
		arrow11.addPoint(shape8.getPointFromCenter(0, -1));
		expected.addArrow(arrow11);

		saver.save(expected, "src/test/resources/expected_4.png");

		Layout actual = designer.generateLayout(firstNode);
		saver.save(actual, "src/test/resources/p_actual_4.png");

		for (int i = 0; i < expected.shapes.size(); i++) {
			if (!expected.shapes.get(i).equals(actual.shapes.get(i)))
				System.out.println("Shape" + i);
		}

		for (int i = 0; i < expected.arrows.size(); i++) {
			if (!expected.arrows.get(i).equals(actual.arrows.get(i)))
				System.out.println("Arrow" + i);
		}

		assertEquals(expected, actual);
	}

	@Test
	void process5 () throws IOException
	{
		String mainCode =
				"int x = 0;\n" +
				"for (int i = 0; i < 1000;i++)\n" +
				"{\n" +
				"	if (i % 2 == 0)\n" +
				"		continue;\n" +
				"   \n" +
				"   if (i < 10)\n" +
				"       m = 3;\n" +
				"   else if (i < 100)\n" +
				"       m = 2;\n" +
				"   else\n" +
				"       m = 1;\n" +
				"   \n" +
				"	x += i * m;\n" +
				"   if (x > 100) {\n" +
				"       if (i < 100)\n" +
				"		    continue;\n" +
				"       else\n" +
				"           break;\n" +
				"   \n}" +
				"   x -= 10;\n" +
				"}\n" +
				"print(x);\n";

//		int x = 0;
//		for (int i = 0; i < 1000; i++) {
//			if (i % 2 == 0)
//				continue;
//
//			if (i < 10)
//				m = 3;
//			else if (i < 100)
//				m = 2;
//			else
//				m = 1;
//
//			x += i * m;
//			if (x > 100) {
//				if (i < 100)
//					continue;
//				else
//					break;
//			}
//			x -= 10;
//		}
//		print(x);

		String code = precode + mainCode + postcode;
		System.out.println(code);

		Node firstNode = Parser.parse(code);

		Layout actual = designer.generateLayout(firstNode);
		saver.save(actual, "src/test/resources/p_actual_5.png");

		for (int i = 0; i < expected.shapes.size(); i++) {
			if (!expected.shapes.get(i).equals(actual.shapes.get(i)))
				System.out.println("Shape" + i);
		}

		for (int i = 0; i < expected.arrows.size(); i++) {
			if (!expected.arrows.get(i).equals(actual.arrows.get(i)))
				System.out.println("Arrow" + i);
		}
	}
}