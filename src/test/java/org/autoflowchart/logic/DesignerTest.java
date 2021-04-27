package org.autoflowchart.logic;

import org.autoflowchart.objects.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DesignerTest
{

	@Test
	void generateLayout ()
	{
		Block[] blocks = {
				new Block(Designer.defaultHeight, ShapeType.ROUNDRECT, "main()", 0),
				new Block(Designer.defaultHeight, ShapeType.RECT, "int[] arr = { 5, 7, 3, 4, 2, 9, 1, 7, 6, 8 };", 0),
				new Block(Designer.defaultHeight, ShapeType.RECT, "int i = 0;", 0),
				new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < arr.size()", 0),
				new Block(Designer.defaultHeight, ShapeType.RECT, "int min_ind = i;", 1),
				new Block(Designer.defaultHeight, ShapeType.RECT, "int j = i;", 1),
				new Block(Designer.defaultHeight, ShapeType.DIAMOND, "j < arr.size()", 1),
				new Block(Designer.defaultHeight, ShapeType.DIAMOND, "arr[i] < arr[min_ind]", 2),
				new Block(Designer.defaultHeight, ShapeType.RECT, "min_ind = i;", 3),
		};
		for (int i = 0; i < blocks.length - 1; i++)
		{
			blocks[i].next = blocks[i+1];
		}

		Block block1 = new Block(Designer.defaultHeight, ShapeType.RECT, "j++;", 2);
		blocks[7].nextFalse = block1;
		blocks[8].next = block1;
		block1.next = blocks[6];
		Block block2 = new Block(Designer.defaultHeight, ShapeType.RECT, "swap(arr[i], arr[min_ind]);", 1);
		blocks[6].nextFalse = block2;
		Block block3 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		block2.next = block3;
		block3.next = blocks[3];
		Block block4 = new Block(Designer.defaultHeight, ShapeType.RECT, "arr[2] = 10;", 0);
		blocks[3].nextFalse = block4;
		Block block5 = new Block(Designer.defaultHeight, ShapeType.RECT, "return;", 0);
		block4.next = block5;

		Designer designer = new Designer();
		Layout testLayout = designer.generateLayout(blocks[0]);
		Saver saver = new Saver();
		try {
			saver.save(testLayout, "src/test/resources/img.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		int x = 0;
		int y = 0;

		Layout layout = new Layout();
		Shape shape = new Shape(x, y, blocks[0]);
		Arrow arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		y += Designer.defaultHeight + Designer.defaultGapY;
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[1]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		y += Designer.defaultHeight + Designer.defaultGapY;
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[2]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		y += Designer.defaultHeight + Designer.defaultGapY;
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[3]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		x += Designer.defaultWidth + Designer.defaultWidth;
		y += Designer.defaultHeight + Designer.defaultGapY * 2;
		arrow.addPointFromPrevious(Designer.defaultWidth + Designer.defaultGapX, 0);
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[4]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		y += Designer.defaultHeight + Designer.defaultGapY;
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[5]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		y += Designer.defaultHeight + Designer.defaultGapY;
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[6]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		x += Designer.defaultWidth + Designer.defaultWidth;
		y += Designer.defaultHeight + Designer.defaultGapY * 2;
		arrow.addPointFromPrevious(Designer.defaultWidth + Designer.defaultGapX, 0);
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[7]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		x += Designer.defaultWidth + Designer.defaultWidth;
		y += Designer.defaultHeight + Designer.defaultGapY * 2;
		arrow.addPointFromPrevious(Designer.defaultWidth + Designer.defaultGapX, 0);
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		layout.addShape(shape);
		layout.addArrow(arrow);

		shape = new Shape(x, y, blocks[8]);
		arrow = new Arrow(shape.getPointFromCenter(0, 1));
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		x -= Designer.defaultWidth + Designer.defaultWidth;
		y += Designer.defaultHeight + Designer.defaultGapY * 2;
		arrow.addPointFromPrevious(-Designer.defaultWidth - Designer.defaultGapX, 0);
		arrow.addPointFromPrevious(0, Designer.defaultGapY);
		layout.addShape(shape);
		layout.addArrow(arrow);

		layout.addShape(new Shape(0, 0, blocks[2]));
		layout.addShape(new Shape(0, 0, blocks[3]));
		layout.addShape(new Shape(0, 0, blocks[4]));
		layout.addShape(new Shape(0, 0, blocks[5]));
		layout.addShape(new Shape(0, 0, blocks[6]));
		layout.addShape(new Shape(0, 0, blocks[7]));

		/*Block block1 = new Block(Designer.defaultHeight, ShapeType.RECT, "j++;", 2);
		blocks[7].nextFalse = block1;
		blocks[8].next = block1;
		block1.next = blocks[6];
		Block block2 = new Block(Designer.defaultHeight, ShapeType.RECT, "swap(arr[i], arr[min_ind]);", 1);
		blocks[6].nextFalse = block2;
		Block block3 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		block2.next = block3;
		block3.next = blocks[3];
		Block block4 = new Block(Designer.defaultHeight, ShapeType.RECT, "arr[2] = 10;", 0);
		blocks[3].nextFalse = block4;
		Block block5 = new Block(Designer.defaultHeight, ShapeType.RECT, "return;", 0);
		block4.next = block5;*/
	}

	@Test
	void placeNextBlock ()
	{
	}

	@Test
	void traverseIfBranch ()
	{
	}
}