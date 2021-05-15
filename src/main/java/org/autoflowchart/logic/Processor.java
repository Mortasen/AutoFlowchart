package org.autoflowchart.logic;

import org.autoflowchart.objects.Block;
import org.autoflowchart.objects.Layout;
import org.autoflowchart.objects.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Processor
{
	public Layout process (String code) {
		Labeler labeler = new Labeler();
		Designer designer = new Designer();
		Node firstNode = Parser.parse(code);
		Block firstBlock = labeler.labelNodes(firstNode);
		Layout layout = designer.generateLayout(firstBlock);
		return layout;
	}

	public Layout process (Path filepath) throws IOException
	{
		String code = Files.readString(filepath);
		return this.process(code);
	}
}
