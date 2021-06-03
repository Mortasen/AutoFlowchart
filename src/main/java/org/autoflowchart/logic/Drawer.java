package org.autoflowchart.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.autoflowchart.objects.*;
import org.autoflowchart.objects.Shape;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Drawer
{
	public static final int OFFSET_X = 100;
	public static final int OFFSET_Y = 100;

	public void draw (Layout layout, GraphicsContext g) throws IOException
	{
		//BufferedImage img = new BufferedImage(1000, 2000, BufferedImage.TYPE_INT_RGB);
		//Graphics2D g = img.createGraphics();
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, img.getWidth(), img.getHeight());
		//g.setColor(Color.BLACK);
		g.setStroke(Color.BLACK);
		Font font = new Font("DejaVu Sans Mono", 8);
		g.setFont(font);
		//g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Shape shape : layout.shapes)
		{
			int x = shape.x + OFFSET_X;
			int y = shape.y + OFFSET_Y;
			int width = shape.width;
			int height = shape.height;
			if (shape.type == ShapeType.ROUNDRECT)
			{
				g.strokeRoundRect(x, y, width, height, height, height);
			} else if (shape.type == ShapeType.RECT) {
				g.strokeRect(x, y, width, height);
			} else if (shape.type == ShapeType.DIAMOND) {
				double[] xPoints = { x, x + width / 2.0, x + width, x + width / 2.0 };
				double[] yPoints = { y + height / 2.0, y, y + height / 2.0, y + height };
				g.strokePolygon(xPoints, yPoints, 4);
			}
			int textX = shape.x + shape.textOffsetX + OFFSET_X;
			int textY = shape.y + shape.textOffsetY + OFFSET_Y;
			g.fillText(shape.text, textX, textY);
		}

		for (Arrow arrow : layout.arrows)
		{
			double[] xPoints = new double[arrow.xPoints.size()];
			for (int i = 0; i < arrow.xPoints.size(); i++) { xPoints[i] = arrow.xPoints.get(i) + OFFSET_X; }
			double[] yPoints = new double[arrow.yPoints.size()];
			for (int i = 0; i < arrow.yPoints.size(); i++) { yPoints[i] = arrow.yPoints.get(i) + OFFSET_Y; }

			g.strokePolyline(xPoints, yPoints, xPoints.length);
			double x = xPoints[xPoints.length - 1];
			double y = yPoints[yPoints.length - 1];
			g.strokeOval(x - 2, y - 2, 4, 4);
		}
	}
}
