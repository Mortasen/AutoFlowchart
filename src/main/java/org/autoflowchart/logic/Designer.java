package org.autoflowchart.logic;

import org.autoflowchart.objects.*;
import org.autoflowchart.utils.WidthMap;

import java.util.LinkedList;
import java.util.List;

public class Designer
{
	public final static int DEFAULT_WIDTH = 300;
	public final static int DEFAULT_HEIGHT = 100;
	public final static int DEFAULT_GAP_X = 100;
	public final static int DEFAULT_GAP_Y = 100;

	int x = 0;
	int y = 0;
	int level = 0;
	Layout layout = new Layout();
	WidthMap widthMap = new WidthMap();

	List<Node> queue = new LinkedList<Node>();

	public Layout generateLayout (Node firstNode)
	{
		this.placeNode(firstNode);
		return this.layout;
	}

	public void placeNode (Node node)
	{
		node.swapNodes();
		Node nextNode = this.createShapeAndConnect(node);
		nextNode.swapNodes();
		if (node.getFalseNode() != null)
			this.placeIfNodes(node);

		while (nextNode != null && !node.isNextJump() && nextNode.getLevel() == this.level && nextNode.getShape() == null) {
			this.createShapeAndConnect(nextNode);
			Arrow arrow = new Arrow(node.getShape().getPointFromCenter(0, 1));
			arrow.addPoint(nextNode.getShape().getPointFromCenter(0, -1));
			this.layout.addArrow(arrow);
			this.widthMap.addArrow(arrow);
			node = nextNode;
			if (node.getFalseNode() != null)
				this.placeIfNodes(node);
			nextNode = node.getNext();
			if (nextNode != null)
				nextNode.swapNodes();
		}

		if (nextNode != null) {
			if (nextNode.getShape() != null) {
				Arrow arrow = new Arrow(node.getConnectionPoint());
				arrow.addPointFromPrevious(0, DEFAULT_GAP_Y);
				this.y += DEFAULT_GAP_Y;
				if (nextNode.getLevel() < this.level) {
					arrow.addPointFromPreviousChangingX(nextNode.getShape().getXFromCenter(0.5));
					arrow.addPoint(nextNode.getShape().getPointFromCenter(0.5, 1));
				} else {
					arrow.addPointFromPreviousChangingX(node.getShape().getXFromCenter(-1) - DEFAULT_GAP_X / 2);
					arrow.addPointFromPreviousChangingY(nextNode.getShape().getYFromCenter(-1) - DEFAULT_GAP_Y);
					arrow.addPointFromPreviousChangingX(nextNode.getShape().getXFromCenter(-0.5));
					arrow.addPoint(nextNode.getShape().getPointFromCenter(-0.5, -1));
				}
				this.layout.addArrow(arrow);
				this.widthMap.addArrow(arrow);
			} else if (node.isNextJump() || nextNode.getLevel() != this.level) {
				nextNode.addToConnectionQueue(node);
				this.queue.add(nextNode);
			}
		}

		if (!this.queue.isEmpty()) {
			for (int i = 0; i < this.queue.size(); i++) {
				Node queueNode = this.queue.get(i);
				if (queueNode.getLevel() == this.level && queueNode.getShape() == null) {
					this.queue.remove(queueNode);
					i--;
					queueNode.swapNodes();
					this.placeNode(queueNode);
				}
			}
		}
	}

	public void placeIfNodes (Node node)
	{
		FalseNode falseNode = node.getFalseNode();
		if (falseNode.isNextJump()) {
			falseNode.getNext().addToConnectionQueue(falseNode);
			this.queue.add(falseNode.getNext());
		} else {
			this.level += 1;
			Arrow arrow = new Arrow(falseNode.getConnectionPoint());
			arrow.addPointFromPrevious(DEFAULT_WIDTH / 2 + DEFAULT_GAP_X, 0);
			arrow.addPointFromPreviousChangingY(this.y);
			this.layout.addArrow(arrow);
			this.placeNode(node.getNextFalse());
			this.level -= 1;
		}
	}

	public Node createShapeAndConnect (Node node)
	{
		this.x = this.level * (DEFAULT_WIDTH + DEFAULT_GAP_X);
		Shape shape = new Shape(this.x, this.y, node);
		this.y += DEFAULT_HEIGHT + DEFAULT_GAP_Y;
		node.setShape(shape);
		this.layout.addShape(shape);
		this.widthMap.addShape(shape);

		if (node.connectionQueue != null) {
			int x = 0;
			for (Element queueNode : node.connectionQueue) {
				Arrow arrow = new Arrow(queueNode.getConnectionPoint());
				if (x == 0) {
					x = arrow.xPoints.get(0);
					if (queueNode.getNode() != queueNode) {
						arrow.addPointFromPrevious(DEFAULT_GAP_X / 2, 0);
						x = queueNode.getShape().getXFromCenter(1) + DEFAULT_GAP_X / 2;
					} else {
						arrow.addPointFromPrevious(0, DEFAULT_GAP_Y / 2);
					}
					int y = arrow.yPoints.get(0);
					int maxWidth = this.widthMap.findMaxWidth(y, node.getShape().getYFromCenter(0));
					if (maxWidth > x - DEFAULT_GAP_X / 2) {
						x = maxWidth + DEFAULT_GAP_X / 2;
						arrow.addPointFromPreviousChangingX(x);
					}
					arrow.addPointFromPreviousChangingY(node.getShape().getYFromCenter(-1) - DEFAULT_GAP_Y / 2);
					arrow.addPointFromPreviousChangingX(node.getShape().getXFromCenter(0.5));
					arrow.addPoint(node.getShape().getPointFromCenter(0.5, -1));
					this.layout.addArrow(arrow);
					this.widthMap.addArrow(arrow);
				} else {
					if (queueNode.getNode() == queueNode) {
						arrow.addPointFromPrevious(0, DEFAULT_GAP_Y / 2);
					}
					arrow.addPointFromPreviousChangingX(x);
					this.layout.addArrow(arrow);
					this.widthMap.addArrow(arrow);
				}
			}
		}

		return node.getNext();
	}

}
