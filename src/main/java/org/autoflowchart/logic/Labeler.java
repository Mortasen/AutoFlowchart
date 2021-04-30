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
		Block toReturn = null;
		while (node != null)
		{
			if (node.block != null) {
				block.setNext(node.block);
				block = null;
				break;
			}
			Block newBlock = this.labelNode(node);
			block.setNext(newBlock);
			node.block = newBlock;
			block = newBlock;
			if (node.nextFalse != null)
			{
				Block lastBlock = traverseBranch(node.next, block);
				if (toReturn == null)
					toReturn = lastBlock;
				node = node.nextFalse;
			} else {
				node = node.next;
			}
		}
		if (toReturn != null)
			return toReturn;
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
		String line1 = node.text;
		int level = node.level;
		FontMetrics fontMetrics;
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 14);
		FontRenderContext fontRenderContext = new FontRenderContext(font.getTransform(), true, true);
		Rectangle2D textRect = font.getStringBounds(line1, fontRenderContext);
		double width = textRect.getWidth();
		double height = textRect.getHeight();
		String text;

		if (width >= Designer.defaultWidth) {
			double widthPerSymbol = (width / line1.length());
			double breakPositionK = Designer.defaultWidth / width;
			int maxSymbols = (int)(line1.length() * breakPositionK - 1);
			String line2 = line1.substring(maxSymbols);
			if (line2.length() > maxSymbols)
			{
				line2 = line2.substring(0, maxSymbols - 3) + "...";
			}
			line1 = line1.substring(0, maxSymbols);
			textRect = font.getStringBounds(line1, fontRenderContext);
			width = textRect.getWidth();
			height = textRect.getHeight() * 2;
			text = line1 + "\n" + line2;
		} else
			text = line1;

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
