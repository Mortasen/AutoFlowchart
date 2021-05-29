package org.autoflowchart.utils;

import org.autoflowchart.objects.Arrow;
import org.autoflowchart.objects.Shape;

import java.util.ArrayList;

public class HeightMap
{
	ArrayList<Integer> map = new ArrayList<Integer>();

	HeightMap ()
	{
		this.map.add(0);
	}

	void addShape (int index, Shape shape)
	{
		this.map.set(5, 3);
	}

	void addArrow (int index, Arrow arrow)
	{

	}
}
