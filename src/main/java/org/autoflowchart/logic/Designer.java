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
		// Рисуем первый блок
		Block block = firstBlock;
		Shape shape = new Shape(this.x, this.y, block);
		this.layout.addShape(shape);
		block.shape = shape;

		while (block.next != null)
		{
			// Передаём текущий блок в метод для отрисовки стрелочки к следующему блоку и самого следующего блока
			// до тех пор, пока текущий блок имеет следующий
			block = this.placeNextBlock(block);
		}

		return this.layout;
	}

	public Block placeNextBlock (Block block)
	{
		// Рисует от переданного блока стрелочку до следующего и создаёт фигуру следующего блока
		Block nextBlock;

		// Проверяем, является ли текущий блок условием
		if (block.nextFalse != null) {
			// Если является, обходим правдивую ветку методом
			this.traverseIfBranch(block);
			this.x = (defaultWidth + defaultGapX) * block.level;
			this.level = block.level;
			nextBlock = block.nextFalse;
		} else {
			nextBlock = block.next;
		}

		if (nextBlock.level != this.level) {
			this.x = (defaultWidth + defaultGapX) * nextBlock.level;
			this.level = block.next.level;
		}

		Arrow arrow;

		this.y += block.height;

		if (nextBlock.connectionQueue != null) {
			this.y += defaultGapY;
			for (Shape shape : nextBlock.connectionQueue) {
				arrow = new Arrow(shape.getPointFromCenter(0, 1));
				arrow.addPointFromPreviousChangingY(this.y);
				arrow.addPointFromPreviousChangingX(this.x + (int) (defaultWidth * 0.75));
				arrow.addPointFromPrevious(0, defaultGapY);
				this.layout.addArrow(arrow);
			}
		}

		arrow = new Arrow(block.shape.getPointFromCenter(0, 1));
		this.y += defaultGapY;
		arrow.addPointFromPreviousChangingY(this.y);
		if (this.x != block.shape.x) {
			arrow.addPointFromPreviousChangingX(this.x + defaultWidth / 2);
			this.y += defaultGapY;
			arrow.addPointFromPreviousChangingY(this.y);
		}
		this.layout.addArrow(arrow);

		Shape nextShape = new Shape(this.x, this.y, nextBlock);
		this.layout.addShape(nextShape);
		nextBlock.shape = nextShape;



		return nextBlock;
	}

	public void traverseIfBranch (Block block)
	{
		// Обходит и отрисовывает всю правдивую ветку ифа

		// Создаём стрелочку от условия, переданного как блок
		Arrow arrow = new Arrow(block.shape.getPointFromCenter(1, 0));
		arrow.addPointFromPrevious(defaultGapX + defaultWidth / 2, 0);
		arrow.addPointFromPrevious(0, defaultGapY + defaultHeight / 2);
		this.layout.addArrow(arrow);
		// Меняем уровень, на котором будут отрисовываться последующие блоки
		block = block.next;
		this.x = (defaultWidth + defaultGapX) * block.level;
		//arrow.addPointFromPreviousChangingX(newX);
		//arrow.addPointFromPrevious(0, defaultGapY);
		this.y += defaultHeight + defaultGapY;
		this.level = block.level;

		// Отрисовываем первый блок из тела ифа
		Shape shape = new Shape(this.x, this.y, block);
		this.layout.addShape(shape);
		block.shape = shape;

		// Присоединяем новый блок к текущему до тех пор, пока новый блок не располагается на меньшем уровне
		// То есть до тех пор, пока не заканчивается тело ифа
		// Или до тех пор, пока следующий блок не будет уже отрисован
		// Это нужно на случай операторов по типу continue
		while (block.next.level >= this.level && block.next.shape == null)
		{
			// Запоминаем форму текущего блока, она пригодится при отрисовке стрелочки
			block = this.placeNextBlock(block);
		}

		if (block.next.shape != null) {
			// Если следующий блок уже отрисован
			// То это значит, что условие совершает прыжок назад
			// И нужно прорисовать стрелочку
			// От текущего блока к одному из предыдущих
			arrow = new Arrow(block.shape.getPointFromCenter(0, 1));
			this.y += defaultGapY;
			arrow.addPointFromPrevious(0, defaultGapY);
			arrow.addPointFromPreviousChangingX(block.next.shape.getXFromCenter(0.5));
			arrow.addPoint(block.next.shape.getPointFromCenter(0.5, 1));
			this.layout.addArrow(arrow);
		} else {
			// Если первое условие не сработало, значит цикл while был завершен из-за второго условия
			// И следующий блок имеет другой уровень, то есть его надо будет отрисовать позже
			// В таком случае, добавляем форму текущего блока в очередь будущего блока
			// Чтобы при его отрисовке можно было нарисовать эту стрелочку
			block.next.addToConnectionQueue(block.shape);
		}
	}
}
