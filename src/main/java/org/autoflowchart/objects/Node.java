package org.autoflowchart.objects;

import org.autoflowchart.logic.Designer;
import org.autoflowchart.utils.Point;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Node extends Element
{
	private String text;
	private FalseNode falseNode;
	private int level;
	private int height;
	private ShapeType type;
	private int textOffsetX;
	private int textOffsetY;
	private Shape shape;
	public List<Element> connectionQueue;



	public Node () {}

	public Node (String text)
	{
		this.setText(text);
	}

	public Node (String text, int level)
	{
		this(text);
		this.setLevel(level);
	}

	public Node (String text, int level, boolean nextJump) {
		this(text, level);
		this.nextJump = nextJump;
	}

	public Node (String text, int level, Node next, Node nextFalse)
	{
		this(text, level);
		this.setNext(next);
		this.setFalseNode(new FalseNode(nextFalse));
	}

	public Node (int height, ShapeType type, String text, int level)
	{
		this(text, level);
		this.height = height;
		this.type = type;
	}

	public Node (int height, ShapeType type)
	{
		this.height = height;
		this.type = type;
	}

	public Node (int height, ShapeType type, String text, int level, Node next, Node nextFalse)
	{
		this(height, type, text, level);
		this.next = next;
		if (nextFalse != null)
			this.setNextFalse(nextFalse);
	}

	public Node (int height, ShapeType type, Node node, Node next, Node nextFalse)
	{
		this(height, type);
		this.next = next;
		this.setNextFalse(nextFalse);
	}



	public Node getNode () {
		return this;
	}

	public void setNext (Node next)
	{
		if (this.next == null && this.getNextFalse() == null) {
			if (this.waitsFor == 0)
				this.next = next;
			else
				this.setNextFalse(next);
		} else if (this.next == null)
			this.next = next;
		else
			this.setNextFalse(next);
	}

	public boolean isNextJump ()
	{
		return nextJump;
	}

	public void setNextJump (boolean nextJump)
	{
		if (this.next == null && this.getNextFalse() == null) {
			this.nextJump = nextJump;
		} else
			this.createAndGetFalseNode().setNextJump(nextJump);
	}

	public void setSetNextJump (boolean nextJump)
	{
		this.nextJump = nextJump;
	}

	public String getText ()
	{
		return text;
	}

	public void setText (String text)
	{
		this.text = text;
	}

	public FalseNode getFalseNode ()
	{
		return falseNode;
	}

	public void setFalseNode (FalseNode falseNode)
	{
		this.falseNode = falseNode;
	}

	public FalseNode createAndGetFalseNode () {
		if (this.falseNode == null)
			this.falseNode = new FalseNode(this);
		return this.falseNode;
	}

	public int getLevel ()
	{
		return level;
	}

	public void setLevel (int level)
	{
		this.level = level;
	}

	public Node getNextFalse () {
		if (this.falseNode != null)
			return this.falseNode.getNext();
		else
			return null;
	}

	public void setNextFalse (Node next) {
		if (this.falseNode != null)
			this.falseNode.setNext(next);
		else
			this.falseNode = new FalseNode(this, next);
	}

	public void addToConnectionQueue (Element element)
	{
		if (this.connectionQueue == null)
			this.connectionQueue = new LinkedList<Element>();
		this.connectionQueue.add(element);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return nextJump == node.nextJump && height == node.height && level == node.level && type == node.type && Objects.equals(text, node.text) && Objects.equals(shape, node.shape);
	}

	public boolean completelyEquals (Object o)
	{
		if (this.equals(o)) {
			Node node = (Node) o;
			return textOffsetX == node.textOffsetX && textOffsetY == node.textOffsetY;
		}
		return false;
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(height, type, text, shape, level);
	}

	public void label ()
	{
		String line1 = this.getText();
		int level = this.getLevel();
		FontMetrics fontMetrics;
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 14);
		FontRenderContext fontRenderContext = new FontRenderContext(font.getTransform(), true, true);
		Rectangle2D textRect = font.getStringBounds(line1, fontRenderContext);
		double width = textRect.getWidth();
		double height = textRect.getHeight();
		String text;

		if (width >= Designer.DEFAULT_WIDTH) {
			double widthPerSymbol = (width / line1.length());
			double breakPositionK = Designer.DEFAULT_WIDTH / width;
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

		int textOffsetX = (int)(Designer.DEFAULT_WIDTH / 2 - width / 2);
		int textOffsetY = (int)(Designer.DEFAULT_HEIGHT / 2 - height / 2);

		ShapeType type;
		if (this.getNextFalse() != null)
			type = ShapeType.DIAMOND;
		else
			type = ShapeType.RECT;

		/*BlockDEPRECATED blockDEPRECATED = new BlockDEPRECATED(Designer.defaultHeight, type, text, level);
		blockDEPRECATED.textOffsetX = textOffsetX;
		blockDEPRECATED.textOffsetY = textOffsetY;*/

		this.height = Designer.DEFAULT_HEIGHT;
		this.type = type;
		this.text = text;
		this.level = level;
		this.textOffsetX = textOffsetX;
		this.textOffsetY = textOffsetY;
	}

	public int getHeight ()
	{
		return height;
	}

	public void setHeight (int height)
	{
		this.height = height;
	}

	public ShapeType getType ()
	{
		return type;
	}

	public void setType (ShapeType type)
	{
		this.type = type;
	}

	public int getTextOffsetX ()
	{
		return textOffsetX;
	}

	public void setTextOffsetX (int textOffsetX)
	{
		this.textOffsetX = textOffsetX;
	}

	public int getTextOffsetY ()
	{
		return textOffsetY;
	}

	public void setTextOffsetY (int textOffsetY)
	{
		this.textOffsetY = textOffsetY;
	}

	public Shape getShape ()
	{
		return shape;
	}

	public void setShape (Shape shape)
	{
		this.shape = shape;
	}

	public Point getConnectionPoint ()
	{
		return this.shape.getPointFromCenter(0, 1);
	}
}