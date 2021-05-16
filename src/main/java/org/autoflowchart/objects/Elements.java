package org.autoflowchart.objects;

import java.util.ArrayList;

class Elements extends Element
{
	public ArrayList<Element> elements = new ArrayList<Element>();

	public Node getNode () {
		throw new NotImplementedException();
	}

	public void add (Element element)
	{
		if (element != null)
			this.elements.add(element);
	}

	public Node getNext ()
	{
		return this.elements.get(0).getNext();
	}

	public void setNext (Node next)
	{
		for (Element element : this.elements)
			element.setNext(next);
	}
}