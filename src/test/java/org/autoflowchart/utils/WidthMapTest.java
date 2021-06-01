package org.autoflowchart.utils;

import org.autoflowchart.objects.Arrow;
import org.autoflowchart.objects.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WidthMapTest
{
	WidthMap map = new WidthMap();

	@BeforeEach
	void initialize ()
	{

	}


	@Test
	void addShape1 ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape2 ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape3 ()
	{
		map = new WidthMap(Map.of(
				0, 100
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape4 ()
	{
		map = new WidthMap(Map.of(
				0, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape5 ()
	{
		map = new WidthMap(Map.of(
				0, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape6 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 100
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape7 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 200
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape8 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 300
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape9 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 400
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				300, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape10 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 100
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape11 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape12 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape13 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 200
		));

		Shape shape = new Shape(0, 100, 300, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape14 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape15 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape16 ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				300, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape1_l ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Shape shape = new Shape(100, 0, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300,
				400, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape2_l ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Shape shape = new Shape(0, 0, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				400, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape3_l ()
	{
		map = new WidthMap(Map.of(
				0, 100
		));

		Shape shape = new Shape(0, 0, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				400, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape4_l ()
	{
		map = new WidthMap(Map.of(
				0, 200
		));

		Shape shape = new Shape(0, 0, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape5_l ()
	{
		map = new WidthMap(Map.of(
				0, 300
		));

		Shape shape = new Shape(0, 0, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape6_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 100
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape7_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 200
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape8_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 300
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape9_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 400
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape10_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 100
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape11_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape12_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape13_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 200
		));

		Shape shape = new Shape(0, 100, 300, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape14_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape15_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape16_lb ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				100, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape6_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 100
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape7_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 200
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape8_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 300
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape9_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 400
		));

		Shape shape = new Shape(100, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 300,
				500, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape10_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 100
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape11_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape12_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape13_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 200
		));

		Shape shape = new Shape(0, 100, 300, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape14_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 200
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape15_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				500, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addShape16_rb ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				500, 300
		));

		Shape shape = new Shape(0, 100, 200, 400);

		map.addShape(shape);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				500, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow1 ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 100,
				600, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow2 ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow3 ()
	{
		map = new WidthMap(Map.of(
				0, 100
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow4 ()
	{
		map = new WidthMap(Map.of(
				0, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow5 ()
	{
		map = new WidthMap(Map.of(
				0, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow6 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 100
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow7 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 200
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow8 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 300
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow9 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 400
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				300, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow10 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 100
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow11 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow12 ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				300, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow13 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow14 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow15 ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				300, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow16 ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				300, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow1_l ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(300, 0);
		arrow.addPoint(300, 400);
		arrow.addPoint(100, 400);
		arrow.addPoint(100, 500);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300,
				400, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow2_l ()
	{
		map = new WidthMap(Map.of(
				0, 0
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(200, 0);
		arrow.addPoint(200, 400);
		arrow.addPoint(0, 400);
		arrow.addPoint(0, 500);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				400, 0
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow3_l ()
	{
		map = new WidthMap(Map.of(
				0, 100
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(200, 0);
		arrow.addPoint(200, 400);
		arrow.addPoint(0, 400);
		arrow.addPoint(0, 500);

		map.addArrow(arrow);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				400, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow4_l ()
	{
		map = new WidthMap(Map.of(
				0, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(200, 0);
		arrow.addPoint(200, 400);
		arrow.addPoint(0, 400);
		arrow.addPoint(0, 500);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow5_l ()
	{
		map = new WidthMap(Map.of(
				0, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(200, 0);
		arrow.addPoint(200, 400);
		arrow.addPoint(0, 400);
		arrow.addPoint(0, 500);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow6_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 100
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 1000,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow7_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 200
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow8_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 300
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow9_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 400
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				300, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow10_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 100
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow11_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow12_lb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				100, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow13_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow14_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow15_lb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				100, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow16_lb ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				100, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow6_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 100
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 1000,
				100, 300,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow7_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 200
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow8_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 300
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow9_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 400
		));

		Arrow arrow = new Arrow(100, 0);
		arrow.addPoint(100, 100);
		arrow.addPoint(300, 100);
		arrow.addPoint(300, 500);
		arrow.addPoint(100, 500);
		arrow.addPoint(100, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				300, 400
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow10_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 100
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				500, 100
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow11_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow12_rb ()
	{
		map = new WidthMap(Map.of(
				0, 0,
				500, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 0,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow13_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 300,
				500, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow14_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 200
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow15_rb ()
	{
		map = new WidthMap(Map.of(
				0, 100,
				500, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 100,
				100, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}

	@Test
	void addArrow16_rb ()
	{
		map = new WidthMap(Map.of(
				0, 200,
				500, 300
		));

		Arrow arrow = new Arrow(0, 0);
		arrow.addPoint(0, 100);
		arrow.addPoint(200, 100);
		arrow.addPoint(200, 500);
		arrow.addPoint(0, 500);
		arrow.addPoint(0, 600);

		map.addArrow(arrow);

		Map<Integer, Integer> expected = new TreeMap<>(Map.of(
				0, 200,
				300, 300
		));

		Map<Integer, Integer> actual = map.getMap();

		assertEquals(expected, actual);
	}
}