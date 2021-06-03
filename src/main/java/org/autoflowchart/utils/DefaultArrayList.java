package org.autoflowchart.utils;

import java.util.ArrayList;

public class DefaultArrayList<T> extends ArrayList<T>
{
	public T set (int index, T el)
	{
		if (this.size() <= index) {
			while (this.size() < index)
			{
				this.add(null);
			}
			this.add(el);
		} else {
			this.set(index, el);
		}
		return el;
	}
}
