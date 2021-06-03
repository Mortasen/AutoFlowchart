package org.autoflowchart.objects;

import org.autoflowchart.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arrow
{
	public List<Integer> xPoints = new ArrayList<Integer>(2);
	public List<Integer> yPoints = new ArrayList<Integer>(2);

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

	public void addPointFromPreviousChangingX (int newX)
	{
		int y = this.yPoints.get(this.yPoints.size() - 1);
		this.addPoint(newX, y);
	}

	public void addPointFromPreviousChangingY (int newY)
	{
		int x = this.xPoints.get(this.xPoints.size() - 1);
		this.addPoint(x, newY);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Arrow arrow = (Arrow) o;
		return Objects.equals(xPoints, arrow.xPoints) && Objects.equals(yPoints, arrow.yPoints);
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(xPoints, yPoints);
	}
}
