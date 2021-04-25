package org.autoflowchart.logic;

import org.autoflowchart.objects.Arrow;
import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Layout;
import org.autoflowchart.objects.Shape;

import java.util.List;

public class Designer
{
	final int defaultWidth = 300;
	final int defaultHeight = 100;
	final int defaultGapX = 100;
	final int defaultGapY = 100;

	public Layout generateLayout (Block firstBlock)
	{
		Layout layout = new Layout();

		int x = 0;
		int y = 0;
		int level = 0;

		Block block = firstBlock;
		Shape shape = new Shape(x, y, block);
		layout.addShape(shape);
		block.shape = shape;

		while (block.next != null)
		{
			Arrow arrow = new Arrow(shape.getPointFromCenter(0, 1));
			y += block.height + defaultGapY;
			arrow.addPointFromPrevious(0, defaultGapY);

			if (block.level != level) {
				int deltaX = (defaultWidth + defaultGapX) * (block.level - level);
				int deltaY = defaultGapY;
				arrow.addPointFromPrevious(deltaX, 0);
				arrow.addPointFromPrevious(0, deltaY);
				x += deltaX;
				y += deltaY;
				level = block.level;
			}
			layout.addArrow(arrow);

			block = block.next;
			shape = new Shape(x, y, block);
			layout.addShape(shape);
			block.shape = shape;
		}

		return layout;
	}
}
