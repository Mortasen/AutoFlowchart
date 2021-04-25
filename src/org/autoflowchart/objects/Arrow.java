package org.autoflowchart.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Arrow
{
	List<Integer> xPoints = new ArrayList<Integer>(2);
	List<Integer> yPoints = new ArrayList<Integer>(2);

	public Arrow (int x, int y)
	{
		this.addPoint(x, y);
	}

	public Arrow (Point point)
	{
		this(point.x, point.y);
	}

	public void addPoint (int x, int y)
	{
		this.xPoints.add(x);
		this.yPoints.add(y);
	}

	public void addPoint (Point point)
	{
		this.addPoint(point.x, point.y);
	}

	public void addPointFromPrevious (int offsetX, int offsetY)
	{
		int x = this.xPoints.get(this.xPoints.size() - 1) + offsetX;
		int y = this.yPoints.get(this.yPoints.size() - 1) + offsetY;
		this.addPoint(x, y);
	}

	public void addPointFromPrevious (Point point)
	{
		this.addPointFromPrevious(point.x, point.y);
	}
}
