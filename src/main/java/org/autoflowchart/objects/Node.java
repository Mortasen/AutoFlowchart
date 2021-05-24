package org.autoflowchart.objects;

import java.util.Objects;

public class Node extends Element
{
	private String text;
	private FalseNode falseNode;
	private int level;

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

	public Node (String text, int level, Node next, Node nextFalse)
	{
		this(text, level);
		this.setNext(next);
		this.setFalseNode(new FalseNode(nextFalse));
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

	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return getLevel() == node.getLevel() && Objects.equals(getText(), node.getText());
	}

	@Override
	public int hashCode ()
	{
		return Objects.hash(getText(), getLevel());
	}

}