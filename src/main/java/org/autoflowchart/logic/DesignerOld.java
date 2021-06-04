//package org.autoflowchart.logic;
//
//import org.autoflowchart.objects.*;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//public class Designer
//{
//	public final static int defaultWidth = 300;
//	public final static int defaultHeight = 100;
//	public final static int defaultGapX = 100;
//	public final static int defaultGapY = 100;
//
//	Layout layout = new Layout();
//
//	int x = 0;
//	int y = 0;
//	int level = 0;
//
//	List<Node> queue = new LinkedList<Node>();
//
//	public Layout generateLayout (Node firstNode)
//	{
//		// Рисуем первый блок
//		Node node = firstNode;
//		Shape shape = new Shape(this.x, this.y, node);
//		this.layout.addShape(shape);
//		node.setShape(shape);
//
//		while (node.getNext() != null)
//		{
//			// Передаём текущий блок в метод для отрисовки стрелочки к следующему блоку и самого следующего блока
//			// до тех пор, пока текущий блок имеет следующий
//			node = this.placeNextNode(node);
//		}
//
//		return this.layout;
//	}
//
//	/*
//	Рисует от переданного блока стрелочку до следующего и создаёт фигуру следующего блока, возвращает следующий блок
//	 */
//	public Node placeNextNode (Node node)
//	{
//		Node nextNode;
//
//		// Проверяем, является ли текущий блок условием
//		if (node.getFalseNode() != null) {
//			// Если является, обходим правдивую ветку методом
//			this.traverseIfBranch(node);
//			this.x = (defaultWidth + defaultGapX) * node.getLevel();
//			this.level = node.getLevel();
//			nextNode = node.getNextFalse();
//			if (nextNode.isNextJump()) {
//				nextNode.addToConnectionQueue(node.getShape());
//				this.queue.add(nextNode);
//				return null;
//			}
//		} else {
//			nextNode = node.getNext();
//		}
//
//
//
//		if (nextNode.getLevel() != this.level) {
//			this.x = (defaultWidth + defaultGapX) * nextNode.getLevel();
//			this.level = node.getNext().getLevel();
//		}
//
//		Arrow arrow;
//
//		this.y += node.getHeight();
//
//		if (nextNode.connectionQueue != null) {
//			this.y += defaultGapY;
//			for (Shape shape : nextNode.connectionQueue) {
//				arrow = new Arrow(shape.getPointFromCenter(0, 1));
//				arrow.addPointFromPreviousChangingY(this.y);
//				arrow.addPointFromPreviousChangingX(this.x + (int) (defaultWidth * 0.75));
//				arrow.addPointFromPrevious(0, defaultGapY);
//				this.layout.addArrow(arrow);
//			}
//		}
//
//		arrow = new Arrow(node.getShape().getPointFromCenter(0, 1));
//		this.y += defaultGapY;
//		arrow.addPointFromPreviousChangingY(this.y);
//		if (this.x != node.getShape().x) {
//			arrow.addPointFromPreviousChangingX(this.x + defaultWidth / 2);
//			this.y += defaultGapY;
//			arrow.addPointFromPreviousChangingY(this.y);
//		}
//		this.layout.addArrow(arrow);
//
//		Shape nextShape = new Shape(this.x, this.y, nextNode);
//		this.layout.addShape(nextShape);
//		nextNode.setShape(nextShape);
//
//		return nextNode;
//	}
//
//	public Node placeNode (Node node)
//	{
//		// Проверяем, является ли текущий блок условием
//		if (node.getFalseNode() != null) {
//			// Если является, обходим правдивую ветку методом
//			this.traverseIfBranch(node);
//			this.x = (defaultWidth + defaultGapX) * node.getLevel();
//			this.level = node.getLevel();
//			if (node.isNextJump()) {
//				node.addToConnectionQueue(node.getShape());
//				this.queue.add(node);
//				return null;
//			}
//		}
//
//		if (node.getLevel() != this.level) {
//			this.x = (defaultWidth + defaultGapX) * node.getLevel();
//			this.level = node.getNext().getLevel();
//		}
//
//		Arrow arrow;
//
//		this.y += node.getHeight();
//
//		if (node.connectionQueue != null) {
//			this.y += defaultGapY;
//			for (Shape shape : node.connectionQueue) {
//				arrow = new Arrow(shape.getPointFromCenter(0, 1));
//				arrow.addPointFromPreviousChangingY(this.y);
//				arrow.addPointFromPreviousChangingX(this.x + (int) (defaultWidth * 0.75));
//				arrow.addPointFromPrevious(0, defaultGapY);
//				this.layout.addArrow(arrow);
//			}
//		}
//
//		Shape nextShape = new Shape(this.x, this.y, node);
//		this.layout.addShape(nextShape);
//		node.setShape(nextShape);
//
//		return node;
//	}
//
//	public void traverseIfBranch (Node node)
//	{
//		// Обходит и отрисовывает всю правдивую ветку ифа
//
//		// Создаём стрелочку от условия, переданного как блок
//		Arrow arrow = new Arrow(node.getShape().getPointFromCenter(1, 0));
//		arrow.addPointFromPrevious(defaultGapX + defaultWidth / 2, 0);
//		arrow.addPointFromPrevious(0, defaultGapY + defaultHeight / 2);
//		this.layout.addArrow(arrow);
//		// Меняем уровень, на котором будут отрисовываться последующие блоки
//		node = node.getNext();
//		this.x = (defaultWidth + defaultGapX) * node.getLevel();
//		//arrow.addPointFromPreviousChangingX(newX);
//		//arrow.addPointFromPrevious(0, defaultGapY);
//		this.y += defaultHeight + defaultGapY;
//		this.level = node.getLevel();
//
//		// Отрисовываем первый блок из тела ифа
//		Shape shape = new Shape(this.x, this.y, node);
//		this.layout.addShape(shape);
//		node.setShape(shape);
//
//		// Присоединяем новый блок к текущему до тех пор, пока новый блок не располагается на меньшем уровне
//		// То есть до тех пор, пока не заканчивается тело ифа
//		// Или до тех пор, пока следующий блок не будет уже отрисован
//		// Это нужно на случай операторов по типу continue
//		while (node != null && node.getNext().getLevel() >= this.level && node.getNext().getShape() == null && !node.getNext().isNextJump())
//		{
//			// Запоминаем форму текущего блока, она пригодится при отрисовке стрелочки
//			node = this.placeNextNode(node);
//		}
//
//		this.queue.removeIf(queueNode -> queueNode.getShape() != null);
//
//		for (Node queueNode : this.queue) {
//			if (queueNode.getLevel() >= this.level) {
//				while (queueNode != null && queueNode.getNext().getLevel() >= this.level && queueNode.getNext().getShape() == null && !queueNode.getNext().isNextJump())
//				{
//					// Запоминаем форму текущего блока, она пригодится при отрисовке стрелочки
//					queueNode = this.placeNode(queueNode);
//				}
//			}
//		}
//
//
//		if (node.getNext().getShape() != null) {
//			// Если следующий блок уже отрисован
//			// То это значит, что условие совершает прыжок назад
//			// И нужно прорисовать стрелочку
//			// От текущего блока к одному из предыдущих
//			arrow = new Arrow(node.getShape().getPointFromCenter(0, 1));
//			this.y += defaultGapY;
//			arrow.addPointFromPrevious(0, defaultGapY);
//			arrow.addPointFromPreviousChangingX(node.getNext().getShape().getXFromCenter(0.5));
//			arrow.addPoint(node.getNext().getShape().getPointFromCenter(0.5, 1));
//			this.layout.addArrow(arrow);
//		} else {
//			// Если первое условие не сработало, значит цикл while был завершен из-за второго условия
//			// И следующий блок имеет другой уровень, то есть его надо будет отрисовать позже
//			// В таком случае, добавляем форму текущего блока в очередь будущего блока
//			// Чтобы при его отрисовке можно было нарисовать эту стрелочку
//			node.getNext().addToConnectionQueue(node.getShape());
//			this.queue.add(node.getNext());
//		}
//	}
//}
