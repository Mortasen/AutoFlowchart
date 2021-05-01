package org.autoflowchart.logic;

import org.autoflowchart.objects.*;
import org.autoflowchart.objects.Shape;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Saver
{
	public void save (Layout layout, String filepath) throws IOException
	{
		BufferedImage img = new BufferedImage(1000, 2000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setColor(Color.BLACK);
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 14);
		g.setFont(font);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Shape shape : layout.shapes)
		{
			int x = shape.x / 2;
			int y = shape.y / 2;
			int width = shape.width / 2;
			int height = shape.height / 2;
			if (shape.type == ShapeType.ROUNDRECT)
			{
				g.drawRoundRect(x, y, width, height, height / 2, height / 2);
			} else if (shape.type == ShapeType.RECT) {
				g.drawRect(x, y, width, height);
			} else if (shape.type == ShapeType.DIAMOND) {
				int[] xPoints = { x, x + width / 2, x + width, x + width / 2 };
				int[] yPoints = { y + height / 2, y, y + height / 2, y + height };
				g.drawPolygon(xPoints, yPoints, 4);
			}
			g.drawString(shape.text, x, y + 20);
		}

		for (Arrow arrow : layout.arrows)
		{
			int[] xPoints = new int[arrow.xPoints.size()];
			for (int i = 0; i < arrow.xPoints.size(); i++) { xPoints[i] = arrow.xPoints.get(i) / 2; }
			int[] yPoints = new int[arrow.yPoints.size()];
			for (int i = 0; i < arrow.yPoints.size(); i++) { yPoints[i] = arrow.yPoints.get(i) / 2; }

			g.drawPolyline(xPoints, yPoints, xPoints.length);
			int x = xPoints[xPoints.length - 1];
			int y = yPoints[yPoints.length - 1];
			g.drawOval(x - 2, y - 2, 4, 4);
		}

		File outputFile = new File(filepath);
		ImageIO.write(img, "png", outputFile);
	}
}
