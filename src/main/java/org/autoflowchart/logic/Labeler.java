/*
package org.autoflowchart.logic;

import org.autoflowchart.objects.BlockDEPRECATED;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Labeler
{
	public BlockDEPRECATED labelNodes (Node firstNode)
	{
		// Берём первый узел и превращаем в блок
		BlockDEPRECATED firstBlockDEPRECATED = this.labelNode(firstNode);
		// Меняем тип этого узла на скруглённый
		firstBlockDEPRECATED.type = ShapeType.ROUNDRECT;
		// Обходим всю ветку начиная с последующего узла
		BlockDEPRECATED lastBlockDEPRECATED = this.traverseBranch(firstNode.getNext(), firstBlockDEPRECATED);
		// Меняем тип последнего узла на скруглённый
		lastBlockDEPRECATED.type = ShapeType.ROUNDRECT;
		// Возвращаем первый узел
		return firstBlockDEPRECATED;
	}

	public BlockDEPRECATED traverseBranch (Node node, BlockDEPRECATED blockDEPRECATED)
	{
		// Превращает node в блок, привязывает к blockDEPRECATED
		// Повторяет пока не наткнётся на узел у которого нет следующего
		// Или на узел, который уже превращён в блок
		Node nextNode;
		BlockDEPRECATED toReturn = null;
		while (node != null)
		{
			if (node.getBlock() != null) {
				blockDEPRECATED.setNext(node.getBlock());
				blockDEPRECATED = null;
				break;
			}
			BlockDEPRECATED newBlockDEPRECATED = this.labelNode(node);
			blockDEPRECATED.setNext(newBlockDEPRECATED);
			node.setBlock(newBlockDEPRECATED);
			blockDEPRECATED = newBlockDEPRECATED;
			if (node.getNextFalse() != null)
			{
				BlockDEPRECATED lastBlockDEPRECATED = traverseBranch(node.getNext(), blockDEPRECATED);
				if (toReturn == null)
					toReturn = lastBlockDEPRECATED;
				node = node.getNextFalse();
			} else {
				node = node.getNext();
			}
		}
		if (toReturn != null)
			return toReturn;
		return blockDEPRECATED;
	}

	*/
/*public Block labelNextNode (Node node)
	{
		Node nextNode;
		if (node.nextFalse != null)
		{
			Block blockDEPRECATED = this.labelNode(node);
			this.traverseIfBranch(node.next);
			nextNode = node.nextFalse;
		} else {
			nextNode = node.next;
		}
		Block blockDEPRECATED = this.labelNode(nextNode);
		node = nextNode;

	}*//*


	public BlockDEPRECATED labelNode (Node node)
	{
		String line1 = node.getText();
		int level = node.getLevel();
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
		if (node.getNextFalse() != null)
			type = ShapeType.DIAMOND;
		else
			type = ShapeType.RECT;

		BlockDEPRECATED blockDEPRECATED = new BlockDEPRECATED(Designer.defaultHeight, type, text, level);
		blockDEPRECATED.textOffsetX = textOffsetX;
		blockDEPRECATED.textOffsetY = textOffsetY;

		return blockDEPRECATED;
	}
}
*/
