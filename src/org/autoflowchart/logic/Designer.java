package org.autoflowchart.logic;

import org.autoflowchart.objects.Arrow;
import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Layout;
import org.autoflowchart.objects.Shape;

import java.util.List;

public class Designer
{
	public final static int defaultWidth = 300;
	public final static int defaultHeight = 100;
	public final static int defaultGapX = 100;
	public final static int defaultGapY = 100;

	Layout layout = new Layout();

	int x = 0;
	int y = 0;
	int level = 0;

	public Layout generateLayout (Block firstBlock)
	{
		Block block = firstBlock;
		Shape shape = new Shape(this.x, this.y, block);
		this.layout.addShape(shape);
		block.shape = shape;

		while (block.next != null)
		{
			block = this.placeNextBlock(block, shape);
			shape = block.shape;
		}

		return this.layout;
	}

	public Block placeNextBlock (Block block, Shape shape)
	{
		if (block.nextFalse != null)
			y += this.traverseIfBranch(block, shape);

		Arrow arrow = new Arrow(shape.getPointFromCenter(0, 1));
		this.y += block.height + defaultGapY;
		arrow.addPointFromPrevious(0, defaultGapY);

		if (block.level != this.level) {
			int deltaX = (defaultWidth + defaultGapX) * (block.level - level);
			int deltaY = defaultGapY;
			arrow.addPointFromPrevious(deltaX, 0);
			arrow.addPointFromPrevious(0, deltaY);
			this.x += deltaX;
			this.y += deltaY;
			this.level = block.level;
		}
		this.layout.addArrow(arrow);

		if (block.nextFalse == null)
			block = block.next;
		else
			block = block.nextFalse;

		shape = new Shape(this.x, this.y, block);
		this.layout.addShape(shape);
		block.shape = shape;
		return block;
	}

	public int traverseIfBranch (Block block, Shape shape)
	{
		Arrow arrow = new Arrow(shape.getPointFromCenter(1, 0));
		arrow.addPointFromPrevious(defaultGapX + defaultWidth / 2, 0);
		arrow.addPointFromPrevious(0, defaultGapY + defaultHeight / 2);
		this.layout.addArrow(arrow);
		this.x += defaultWidth + defaultGapX;
		this.y += defaultHeight + defaultGapY;
		block = block.next;
		shape = new Shape(this.x, this.y, block);

		while (block.level >= this.level)
		{
			block = this.placeNextBlock(block, shape);
			shape = block.shape;
		}
	}
}
