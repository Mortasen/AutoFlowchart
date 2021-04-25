package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Layout;
import org.autoflowchart.objects.Shape;

import java.util.List;

public class Designer
{
	final int defaultWidth = 300;
	final int defaultHeight = 100;

	public Layout generateLayout (Block firstBlock)
	{
		Layout layout = new Layout();

		Block currentBlock = firstBlock;
		layout.addShape(0, 0, currentBlock.width, currentBlock.height, currentBlock.type, currentBlock.text, currentBlock.textOffsetX, currentBlock.textOffsetY);

		while (currentBlock.next != null)
		{
			currentBlock =
		}
	}
}
