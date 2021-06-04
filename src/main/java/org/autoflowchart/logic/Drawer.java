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

	GraphicsContext g;

	public void draw (Layout layout, GraphicsContext g) throws IOException
	{
		CustomizerOptions custom = new CustomizerOptions();
		this.draw(layout, g, custom);
	}

	public void draw (Layout layout, GraphicsContext g, CustomizerOptions custom) throws IOException
	{
		this.g = g;
		g.setFill(Color.WHITE);
		g.fillRect(0, 0, layout.width + OFFSET_X * 2, layout.height + OFFSET_Y * 2);
		g.setFill(Color.BLACK);
		Font font = new Font(Node.FONT_NAME, Node.FONT_SIZE);
		g.setFont(font);

		float outline = custom.OutlineSize;
		int j = 0;
		for (Shape shape : layout.shapes) {
			Arrow arrow = layout.arrows.get(j);

			if (shape.type == ShapeType.ROUNDRECT) {
				DrawObject(shape, custom.terminalShape, custom.terminalColor, custom.terminalOutlineColor, custom.terminalSize);

				//				int xx = (int) (arrow.xPoints.get(j) * custom.terminalSize);
				//				int yy = (int) (arrow.yPoints.get(j) * custom.terminalSize);
				//				arrow.xPoints.set(j, xx);
				//				arrow.yPoints.set(j, yy);
				//				//xx = (int) (arrow.xPoints.get(j+1) * custom.terminalSize);
				//				//xx = (int) (arrow.xPoints.get(j) * custom.terminalSize);
				//			//	yy += (layout.arrows.yPoints.get(j+1) - arrow.yPoints.get(j));
				//			//	yy = (int) (arrow.yPoints.get(j+1) * custom.terminalSize);
				//				yy = (int) (arrow.yPoints.get(j) * custom.terminalSize) - (int) (yy/ custom.terminalSize);
				//				arrow.xPoints.set(j+1, xx);
				//				arrow.yPoints.set(j+1, yy);
				//				layout.arrows.set(j, arrow);
				//				shape = layout.shapes.get(j+1);
				//				shape.x*=custom.terminalSize;
				//				shape.y*=custom.terminalSize;
				//				layout.shapes.set(j+1, shape);
				//				j++;
			} else if (shape.type == ShapeType.RECT) {
				DrawObject(shape, custom.processShape, custom.processColor, custom.processOutlineColor, custom.processSize);
			} else if (shape.type == ShapeType.DIAMOND) {
				DrawObject(shape, custom.decisionShape, custom.decisionColor, custom.decisionOutlineColor, custom.decisionSize);
			} else if (shape.type == ShapeType.PARALLELOGRAM) {

				DrawObject(shape, custom.outputShape, custom.outputColor, custom.outputOutlineColor, custom.outputSize);
			} else if (shape.type == ShapeType.OVAL) {
				DrawObject(shape, custom.terminalShape, custom.terminalColor, custom.terminalOutlineColor, custom.terminalSize);
			} else if (shape.type == ShapeType.HEXAGON) {
				DrawObject(shape, custom.processShape, custom.processColor, custom.processOutlineColor, custom.processSize);
			} else if (shape.type == ShapeType.FUNCTION) {
				DrawObject(shape, custom.functionShape, custom.functionColor, custom.functionOutlineColor, custom.functionSize);
			}
			g.setFill(Color.BLACK);
			int textX = shape.x + shape.textOffsetX + OFFSET_X;
			int textY = shape.y + shape.textOffsetY + OFFSET_Y + 8;
			g.fillText(shape.text, textX, textY);
		}
		g.setStroke(Color.rgb(0, 0, 0));
		for (Arrow arrow : layout.arrows) {
			double[] xPoints = new double[arrow.xPoints.size()];
			for (int i = 0; i < arrow.xPoints.size(); i++) {
				xPoints[i] = arrow.xPoints.get(i) + OFFSET_X;
			}
			double[] yPoints = new double[arrow.yPoints.size()];
			for (int i = 0; i < arrow.yPoints.size(); i++) {
				yPoints[i] = arrow.yPoints.get(i) + OFFSET_Y;
			}

			g.strokePolyline(xPoints, yPoints, xPoints.length);
			double x1 = xPoints[xPoints.length - 2];
			double y1 = yPoints[yPoints.length - 2];
			double x2 = xPoints[xPoints.length - 1];
			double y2 = yPoints[yPoints.length - 1];
			double dx = x2 - x1;
			double dy = y2 - y1;
			double angle = Math.atan2(dy, dx);
			int lx = (int) (x2 - 8 * Math.cos(angle - Math.PI / 8));
			int ly = (int) (y2 - 8 * Math.sin(angle - Math.PI / 8));
			int rx = (int) (x2 - 8 * Math.cos(angle + Math.PI / 8));
			int ry = (int) (y2 - 8 * Math.sin(angle + Math.PI / 8));
			g.strokeLine(lx, ly, x2, y2);
			g.strokeLine(rx, ry, x2, y2);
		}
	}

	public void DrawObject (Shape shape, ShapeType type, java.awt.Color awtBlockColor, java.awt.Color awtLineColor, double Size)
	{
		int r = awtBlockColor.getRed();
		int g0 = awtBlockColor.getGreen();
		int b = awtBlockColor.getBlue();
		Color BlockColor = Color.rgb(r, g0, b);
		int r1 = awtLineColor.getRed();
		int g1 = awtLineColor.getGreen();
		int b1 = awtLineColor.getBlue();
		Color LineColor = Color.rgb(r1, g1, b1);

		int x = shape.x + OFFSET_X;
		int y = shape.y + OFFSET_Y;
		int width = shape.width;

		int height = shape.height;
		//height*=Size;
		//width*=Size;
		g.setFill(BlockColor);
		g.setStroke(LineColor);
		if (type == ShapeType.ROUNDRECT) {
			g.fillRoundRect(x, y, width, height, height / 2, height / 2);
			g.strokeRoundRect(x, y, width, height, height / 2, height / 2);
		} else if (type == ShapeType.RECT) {
			g.fillRect(x, y, width, height);
			g.strokeRect(x, y, width, height);
		} else if (type == ShapeType.DIAMOND) {
			double[] xPoints = { x, x + width / 2, x + width, x + width / 2 };
			double[] yPoints = { y + height / 2, y, y + height / 2, y + height };
			g.fillPolygon(xPoints, yPoints, 4);
			g.strokePolygon(xPoints, yPoints, 4);
		} else if (type == ShapeType.PARALLELOGRAM) {
			int six = width / 6;
			double[] xPoints = { x, x + six, x + width, x + 5 * six };
			double[] yPoints = { y + height, y, y, y + height };
			g.fillPolygon(xPoints, yPoints, 4);
			g.strokePolygon(xPoints, yPoints, 4);
			//		} else if (type == ShapeType.OVAL) {
			//			g.setFill(BlockColor);
			//			g.fillOval(x, y, width, height);
			//			g.setFill(LineColor);
			//			g.drawOval(x, y, width, height);
			//		} else if (type == ShapeType.HEXAGON) {
			//			int hghtHalf = height / 2;
			//			int wdthThird = width / 3;
			//			int[] xPoints = {x, x + wdthThird, x + 2 * wdthThird, x + width, x + 2 * wdthThird, x + wdthThird};
			//			int[] yPoints = {y + hghtHalf, y, y, y + hghtHalf, y + height, y + height};
			//			g.setFill(BlockColor);
			//			g.fillPolygon(xPoints, yPoints, 6);
			//			g.setFill(LineColor);
			//			g.drawPolygon(xPoints, yPoints, 6);
			//		} else if (type == ShapeType.FUNCTION) {
			//			g.setFill(BlockColor);
			//			g.fillRect(x, y, width, height);
			//			g.setFill(LineColor);
			//			g.drawRect(x, y, width, height);
			//			g.drawLine(x + 14, y, x + 14, y + height);
			//			g.drawLine(x + width - 14, y, x + width - 14, y + height);
			//		}
		}
	}
}