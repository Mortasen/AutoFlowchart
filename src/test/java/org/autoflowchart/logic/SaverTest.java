package org.autoflowchart.logic;

import org.autoflowchart.objects.BlockDEPRECATED;
import org.autoflowchart.objects.Layout;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SaverTest
{

	@Test
	void save () throws IOException
	{
		Layout layout = new Layout();
		BlockDEPRECATED blockDEPRECATED = new BlockDEPRECATED();
		Saver saver = new Saver();
		saver.save(layout, "src/test/resources/img.png");
	}
}