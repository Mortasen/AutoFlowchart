package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Labeler
{
	public Block labelNodes (Node firstNode)
	{
		// Берём первый узел и превращаем в блок
		Block firstBlock = this.labelNode(firstNode);
		// Меняем тип этого узла на скруглённый
		firstBlock.type = ShapeType.ROUNDRECT;
		// Обходим всю ветку начиная с последующего узла
		Block lastBlock = this.traverseBranch(firstNode.next, firstBlock);
		// Меняем тип последнего узла на скруглённый
		lastBlock.type = ShapeType.ROUNDRECT;
		// Возвращаем первый узел
		return firstBlock;
	}

	public Block traverseBranch (Node node, Block block)
	{
		// Превращает node в блок, привязывает к block
		// Повторяет пока не наткнётся на узел у которого нет следующего
		// Или на узел, который уже превращён в блок
		Node nextNode;
		while (node != null)
		{
			if (node.block != null) {
				block.setNext(node.block);
				block = node.block;
				break;
			}
			Block newBlock = this.labelNode(node);
			block.setNext(newBlock);
			node.block = newBlock;
			block = newBlock;
			if (node.nextFalse != null)
			{
				traverseBranch(node.next, block);
				node = node.nextFalse;
			} else {
				node = node.next;
			}
		}
		return block;
	}

	/*public Block labelNextNode (Node node)
	{
		Node nextNode;
		if (node.nextFalse != null)
		{
			Block block = this.labelNode(node);
			this.traverseIfBranch(node.next);
			nextNode = node.nextFalse;
		} else {
			nextNode = node.next;
		}
		Block block = this.labelNode(nextNode);
		node = nextNode;

	}*/

	public Block labelNode (Node node)
	{
		String text = node.text;
		int level = node.level;
		FontMetrics fontMetrics;
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 14);
		FontRenderContext fontRenderContext = new FontRenderContext(font.getTransform(), true, true);
		Rectangle2D textRect = font.getStringBounds(text, fontRenderContext);
		double width = textRect.getWidth();
		double height = textRect.getHeight();
		if (width >= Designer.defaultWidth) {
			double widthPerSymbol = (width / text.length());
			double breakPositionK = Designer.defaultWidth / width;
			int breakPosition = (int)(text.length() * breakPositionK - 1);
			String left = text.substring(breakPosition);
			if (left.length() > breakPosition)
			{
				left = left.substring(0, breakPosition - 3) + "...";
			}
			text = text.substring(0, breakPosition) + '\n' + left;
			textRect = font.getStringBounds(text, fontRenderContext);
			width = textRect.getWidth();
			height = textRect.getHeight();
		}

		int textOffsetX = (int)(Designer.defaultWidth / 2 - width / 2);
		int textOffsetY = (int)(Designer.defaultHeight / 2 - height / 2);

		ShapeType type;
		if (node.nextFalse != null)
			type = ShapeType.DIAMOND;
		else
			type = ShapeType.RECT;

		Block block = new Block(Designer.defaultHeight, type, text, level);
		block.textOffsetX = textOffsetX;
		block.textOffsetY = textOffsetY;

		return block;
	}
}
