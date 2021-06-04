package org.autoflowchart.logic;

import org.autoflowchart.objects.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest
{

	@Test
	void parse ()
	{
		String precode =
				"package com.company;" +
				"public class Main {" +
				"public static void main (String[] args) {";
		String postcode =
				"}}";

		// Test 1 : 3 linear nodes
		/*
		int a = 1;
		int b = 2;
		int c = a + b;
		 */
		System.out.println("Test 1 : 3 linear nodes");

		String mainCode =
				"int a = 1;" +
				"int b = 2;" +
				"int c = a + b;";

		String code = precode + mainCode + postcode;

		Node expected0 = new Node("main()", 0);
		Node expected1 = new Node("int a = 1;", 0);
		expected0.setNext(expected1);
		Node expected2 = new Node("int b = 2;", 0);
		expected1.setNext(expected2);
		Node expected3 = new Node("int c = a + b;", 0);
		expected2.setNext(expected3);
		Node expected4 = new Node("return;");

		Node actual0 = Parser.parse(code);
		Node actual1 = actual0.getNext();
		Node actual2 = actual1.getNext();
		Node actual3 = actual2.getNext();
		Node actual4 = actual3.getNext();

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);

		// Test 2 : 4 nodes, 1st is condition
		/*
		if (x == 1)
			x = 0;
		else
			x = 1;
		x++;
		 */
		System.out.println("Test 2 : 4 nodes, 1st is condition");

		mainCode =
				"if (x == 1)" +
				"	x = 0;" +
				"else" +
				"	x = 1;" +
				"x++;";

		code = precode + mainCode + postcode;

		expected0 = new Node("main()", 0);
		expected1 = new Node("x == 1", 0);
		expected0.setNext(expected1);
		expected2 = new Node("x = 0;", 1);
		expected1.setNext(expected2);
		expected3 = new Node("x = 1;", 0);
		expected1.setNextFalse(expected3);
		expected4 = new Node("x++;", 0);
		expected2.setNext(expected4);
		expected3.setNext(expected4);
		Node expected5 = new Node("return;", 0);
		expected4.setNext(expected5);

		actual0 = Parser.parse(code);
		actual1 = actual0.getNext();
		actual2 = actual1.getNext();
		actual3 = actual1.getNextFalse();
		actual4 = actual2.getNext();
		Node actual5 = actual4.getNext();

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertSame(actual2.getNext(), actual3.getNext());

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

		mainCode =
				"int i = 0;" +
				"while (i < 5)" +
				"{" +
				"	x += i;" +
				"	i++;" +
				"}" +
				"print(i);";

		code = precode + mainCode + postcode;

		expected0 = new Node("main()", 0);
		expected1 = new Node("int i = 0;", 0);
		expected0.setNext(expected1);
		expected2 = new Node("i < 5", 0);
		expected1.setNext(expected2);
		expected3 = new Node("x += i;", 1);
		expected2.setNext(expected3);
		expected4 = new Node("i++;", 1);
		expected3.setNext(expected4);
		expected4.setNext(expected2);
		expected5 = new Node("print(i);", 0);
		expected2.setNextFalse(expected5);
		Node expected6 = new Node("return;", 0);
		expected5.setNext(expected6);

		actual0 = Parser.parse(code);
		actual1 = actual0.getNext();
		actual2 = actual1.getNext();
		actual3 = actual2.getNext();
		actual4 = actual3.getNext();
		actual5 = actual2.getNextFalse();
		Node actual6 = actual5.getNext();

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertSame(actual4.getNext(), actual2);

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

		mainCode =
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

		code = precode + mainCode + postcode;

		expected0 = new Node("main()", 0);
		expected1 = new Node("int i = 0;", 0);
		expected0.setNext(expected1);
		expected2 = new Node("true", 0);
		expected1.setNext(expected2);
		expected3 = new Node("i % 2 == 0", 1);
		expected2.setNext(expected3);
		expected4 = new Node("x++;", 2);
		expected3.setNext(expected4);
		expected5 = new Node("x > 16", 1, true);
		expected4.setNext(expected5);
		expected6 = new Node("i++;", 1);
		expected3.setNextFalse(expected6);
		expected5.setNextFalse(expected6);
		expected6.setNext(expected2);
		Node expected7 = new Node("print(x);", 0);
		expected2.setNextFalse(expected7);
		expected5.setNext(expected7);
		Node expected8 = new Node("return;", 0);
		expected7.setNext(expected8);

		actual0 = Parser.parse(code);
		actual1 = actual0.getNext();
		actual2 = actual1.getNext();
		actual3 = actual2.getNext();
		actual4 = actual3.getNext();
		actual5 = actual4.getNext();
		actual6 = actual5.getNextFalse();
		Node actual7 = actual2.getNextFalse();
		Node actual8 = actual7.getNext();

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
		assertEquals(expected8, actual8);
		assertSame(actual3.getNextFalse(), actual6);
		assertSame(actual5.getNext(), actual7);
		assertSame(actual6.getNext(), actual2);
		assertTrue(actual3.getFalseNode().isNextJump());
	}
}