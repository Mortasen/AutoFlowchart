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
			shape = block.shape;
			block = this.placeNextBlock(block);
		}

		return this.layout;
	}

	public Block placeNextBlock (Block block)
	{
		if (block.nextFalse != null)
			this.traverseIfBranch(block);

		Arrow arrow = new Arrow(block.shape.getPointFromCenter(0, 1));
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

		Shape shape = new Shape(this.x, this.y, block);
		this.layout.addShape(shape);
		block.shape = shape;
		return block;
	}

	public void traverseIfBranch (Block block)
	{
		Arrow arrow = new Arrow(block.shape.getPointFromCenter(1, 0));
		arrow.addPointFromPrevious(defaultGapX + defaultWidth / 2, 0);
		arrow.addPointFromPrevious(0, defaultGapY + defaultHeight / 2);
		this.layout.addArrow(arrow);
		this.x += defaultWidth + defaultGapX;
		this.y += defaultHeight + defaultGapY;
		block = block.next;
		Shape shape = new Shape(this.x, this.y, block);
		block.shape = shape;

		while (block.level >= this.level && block.next.shape == null)
		{
			shape = block.shape;
			block = this.placeNextBlock(block);
		}

		if (block.next.shape != null) {
			arrow = new Arrow(shape.getPointFromCorner(0, 1));
			arrow.addPointFromPrevious(0, defaultGapY);
			arrow.addPointFromPreviousChangingX(block.shape.getXFromCenter(0.5));
			arrow.addPoint(block.shape.getPointFromCenter(0.5, 1));
		}
	}
}
