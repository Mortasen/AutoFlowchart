package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Layout;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaverTest
{

	@Test
	void save () throws IOException
	{
		Layout layout = new Layout();
		Block block = new Block();
		Saver saver = new Saver();
		saver.save(layout, "src/test/resources/img.png");
	}
}