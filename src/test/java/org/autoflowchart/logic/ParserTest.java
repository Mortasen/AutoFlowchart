package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

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
		expected0.next = expected0;
		Node expected2 = new Node("int b = 2;", 0);
		expected1.next = expected2;
		Node expected3 = new Node("int c = a + b;", 0);
		expected2.next = expected3;
		Node expected4 = new Node("return;");

		Node actual0 = Parser.parse(code);
		Node actual1 = actual0.next;
		Node actual2 = actual1.next;
		Node actual3 = actual2.next;
		Node actual4 = actual3.next;

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
		expected2 = new Node("x = 0;", 1);
		expected1.next = expected2;
		expected3 = new Node("x = 1;", 0);
		expected1.nextFalse = expected3;
		expected4 = new Node("x++;", 0);
		expected2.next = expected4;
		expected3.next = expected4;
		Node expected5 = new Node("return;", 0);
		expected4.next = expected5;

		actual0 = Parser.parse(code);
		actual1 = actual0.next;
		actual2 = actual1.next;
		actual3 = actual1.nextFalse;
		actual4 = actual2.next;
		Node actual5 = actual4.next;

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertSame(actual2.next, actual3.next);

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
		expected2 = new Node("i < 5", 0);
		expected1.next = expected2;
		expected3 = new Node("x += i;", 1);
		expected2.next = expected3;
		expected4 = new Node("i++;", 1);
		expected3.next = expected4;
		expected4.next = expected2;
		expected5 = new Node("print(i);", 0);
		expected2.nextFalse = expected5;
		Node expected6 = new Node("return;", 0);
		expected5.next = expected6;

		actual0 = Parser.parse(code);
		actual1 = actual0.next;
		actual2 = actual1.next;
		actual3 = actual2.next;
		actual4 = actual3.next;
		actual5 = actual2.nextFalse;
		Node actual6 = actual5.next;

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertSame(actual4.next, actual2);

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
		expected2 = new Node("true", 0);
		expected1.next = expected2;
		expected3 = new Node("i % 2 == 0", 1);
		expected2.next = expected3;
		expected4 = new Node("x++;", 2);
		expected3.next = expected4;
		expected5 = new Node("x > 16", 1);
		expected4.next = expected5;
		expected6 = new Node("i++;", 1);
		expected3.nextFalse = expected6;
		expected5.nextFalse = expected6;
		Node expected7 = new Node("print(x);", 0);
		expected2.nextFalse = expected7;
		expected5.next = expected7;
		Node expected8 = new Node("return;", 0);
		expected7.next = expected8;

		actual0 = Parser.parse(code);
		actual1 = actual0.next;
		actual2 = actual1.next;
		actual3 = actual2.next;
		actual4 = actual3.next;
		actual5 = actual4.next;
		actual6 = actual5.nextFalse;
		Node actual7 = actual2.nextFalse;
		Node actual8 = actual7.next;

		assertEquals(expected0, actual0);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
		assertEquals(expected4, actual4);
		assertEquals(expected5, actual5);
		assertEquals(expected6, actual6);
		assertEquals(expected7, actual7);
		assertEquals(expected8, actual8);
		assertSame(actual3.nextFalse, actual6);
		assertSame(actual5.next, actual7);
	}
}