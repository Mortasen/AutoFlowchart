package org.autoflowchart.logic;

import org.autoflowchart.objects.BlockDEPRECATED;
import org.autoflowchart.objects.Layout;
import org.autoflowchart.objects.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Processor
{
	public Layout process (String code) {
		//Labeler labeler = new Labeler();
		Designer designer = new Designer();
		Node firstNode = Parser.parse(code);
		//BlockDEPRECATED firstBlockDEPRECATED = labeler.labelNodes(firstNode);
		Layout layout = designer.generateLayout(firstNode);
		return layout;
	}

	public Layout process (Path filepath) throws IOException
	{
		String code = Files.readString(filepath);
		return this.process(code);
	}
}
