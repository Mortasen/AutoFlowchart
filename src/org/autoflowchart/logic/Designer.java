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
		int currentLevel = 0;

		Block currentBlock = firstBlock;
		Shape currentShape = new Shape(x, y, currentBlock);
		layout.addShape(currentShape);
		currentBlock.shape = currentShape;

		while (currentBlock.next != null)
		{
			Arrow arrow = new Arrow()
			y += currentBlock.height;
			y += defaultGapY;
			if (currentBlock.level != currentLevel) {
				currentLevel += 1;
				x += currentBlock.width;
				x += defaultGapX;
				y += defaultGapY;
			}
			currentBlock = currentBlock.next;
			currentShape = new Shape(x, y, currentBlock);
			layout.addShape(currentShape);
			currentBlock.shape = currentShape;
		}
	}
}
