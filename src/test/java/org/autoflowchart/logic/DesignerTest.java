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
			saver.save(testLayout, "src/test/resources/img1.png");
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

		/*
		int x = 0;
		for(int i = 0; i < 1000; i++)
		{
			if (i % 2 == 0)
				continue;

			if (i < 10)
				int m = 3;
			else if (i < 100)
				int m = 2;
			else
				int m = 1;

			x += i * m;
			if (x > 100) {
				if (i < 100)
					continue;
				else
					break;
			}
			x -= 10;
		}
		print(x);
		 */

		Block expected1 = new Block(Designer.defaultHeight, ShapeType.ROUNDRECT, "main();", 0);
		Block expected2 = new Block(Designer.defaultHeight, ShapeType.RECT, "int x = 0;", 0);
		expected1.next = expected2;
		Block expected3 = new Block(Designer.defaultHeight, ShapeType.RECT, "int i = 0;", 0);
		expected2.next = expected3;
		Block expected4 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 1000", 0);
		expected3.next = expected4;
		Block expected5 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i % 2 == 0", 1);
		expected4.next = expected5;
		Block expected6 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 10", 1);
		expected5.nextFalse = expected6;
		Block expected7 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 3;", 2);
		expected6.next = expected7;
		Block expected8 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 100", 1);
		expected6.nextFalse = expected8;
		Block expected9 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 2;", 2);
		expected8.next = expected9;
		Block expected10 = new Block(Designer.defaultHeight, ShapeType.RECT, "int m = 1;", 1);
		expected8.nextFalse = expected10;
		Block expected11 = new Block(Designer.defaultHeight, ShapeType.RECT, "x += i * m;", 1);
		expected7.next = expected11;
		expected9.next = expected11;
		expected10.next = expected11;
		Block expected12 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "x > 100", 1);
		expected11.next = expected12;
		Block expected13 = new Block(Designer.defaultHeight, ShapeType.DIAMOND, "i < 100", 2);
		expected12.next = expected13;
		Block expected14 = new Block(Designer.defaultHeight, ShapeType.RECT, "x -= 10", 1);
		expected12.nextFalse = expected14;
		Block expected15 = new Block(Designer.defaultHeight, ShapeType.RECT, "i++;", 1);
		expected5.next = expected15;
		expected13.next = expected15;
		expected14.next = expected15;
		expected15.next = expected4;
		Block expected16 = new Block(Designer.defaultHeight, ShapeType.RECT, "print(x);", 0);
		expected4.nextFalse = expected16;
		expected13.nextFalse = expected16;
		Block expected17 = new Block(Designer.defaultHeight, ShapeType.ROUNDRECT, "return;", 0);
		expected16.next = expected17;

		designer = new Designer();
		layout = designer.generateLayout(expected1);
		try {
			saver.save(layout, "src/test/resources/img2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
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