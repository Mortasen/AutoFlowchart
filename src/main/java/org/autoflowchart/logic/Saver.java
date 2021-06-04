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
	BufferedImage img;
	Graphics2D g;

	public void save (Layout layout, String filepath) throws IOException
	{
		CustomizerOptions custom = new CustomizerOptions();
		this.save(layout, filepath, custom);
	}

	public void save (Layout layout, String filepath, CustomizerOptions custom) throws IOException
	{
		img = new BufferedImage(layout.width, layout.height, BufferedImage.TYPE_INT_RGB);
		g = img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setColor(Color.BLACK);
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 8);
		g.setFont(font);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int j=0;
		for ( Shape shape : layout.shapes)
		{
			Arrow arrow = layout.arrows.get(j);
			int x = shape.x;
			int y = shape.y;
			int width = shape.width;

			int height = shape.height;
			if (shape.type == ShapeType.ROUNDRECT)
			{
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
			}
			else if (shape.type == ShapeType.PARALLELOGRAM){

				DrawObject(shape, custom.outputShape, custom.outputColor, custom.outputOutlineColor, custom.outputSize);
			}
			else if (shape.type==ShapeType.OVAL){
				DrawObject(shape, custom.terminalShape, custom.terminalColor, custom.terminalOutlineColor, custom.terminalSize);
			}
			else if (shape.type==ShapeType.HEXAGON){
				DrawObject(shape, custom.processShape, custom.processColor, custom.processOutlineColor, custom.processSize);
			}
			else if(shape.type==ShapeType.FUNCTION){
				DrawObject(shape, custom.functionShape, custom.functionColor, custom.functionOutlineColor, custom.functionSize);
			}
			FontMetrics fm = img.getGraphics().getFontMetrics(font);
			int textX = shape.x + shape.textOffsetX;
			int textY = shape.y + shape.textOffsetY + 8;
			this.myDrawString(shape.text, textX, textY);
		}
		g.setColor(new Color(57, 11, 11));
		for (Arrow arrow : layout.arrows)
		{
			int[] xPoints = new int[arrow.xPoints.size()];
			for (int i = 0; i < arrow.xPoints.size(); i++) { xPoints[i] = arrow.xPoints.get(i); }
			int[] yPoints = new int[arrow.yPoints.size()];
			for (int i = 0; i < arrow.yPoints.size(); i++) { yPoints[i] = arrow.yPoints.get(i); }

			g.drawPolyline(xPoints, yPoints, xPoints.length);
			int x1 = xPoints[xPoints.length - 2];
			int y1 = yPoints[yPoints.length - 2];
			int x2 = xPoints[xPoints.length - 1];
			int y2 = yPoints[yPoints.length - 1];
			int dx = x2 - x1;
			int dy = y2 - y1;
			double angle = Math.atan2(dy, dx);
			int lx = (int)(x2 - 8 * Math.cos(angle - Math.PI / 8));
			int ly = (int)(y2 - 8 * Math.sin(angle - Math.PI / 8));
			int rx = (int)(x2 - 8 * Math.cos(angle + Math.PI / 8));
			int ry = (int)(y2 - 8 * Math.sin(angle + Math.PI / 8));
			g.drawLine(lx, ly, x2, y2);
			g.drawLine(rx, ry, x2, y2);
		}

		File outputFile = new File(filepath);
		ImageIO.write(img, "png", outputFile);
	}
	public void DrawObject(Shape shape, ShapeType type, Color BlockColor, Color LineColor, double Size) {
		int x = shape.x;
		int y = shape.y;
		int width = shape.width;

		int height = shape.height;
		//height*=Size;
		//width*=Size;
		if (type == ShapeType.ROUNDRECT) {
			g.setColor(BlockColor);
			g.fillRoundRect(x, y, width, height, height / 2, height / 2);

			g.setColor(LineColor);
			g.drawRoundRect(x, y, width, height, height / 2, height / 2);
		} else if (type == ShapeType.RECT) {
			g.setColor(BlockColor);
			g.fillRect(x, y, width, height);
			g.setColor(LineColor);
			g.drawRect(x, y, width, height);
		} else if (type == ShapeType.DIAMOND) {
			int[] xPoints = {x, x + width / 2, x + width, x + width / 2};
			int[] yPoints = {y + height / 2, y, y + height / 2, y + height};
			g.setColor(BlockColor);
			g.fillPolygon(xPoints, yPoints, 4);
			g.setColor(LineColor);
			g.drawPolygon(xPoints, yPoints, 4);
		} else if (type == ShapeType.PARALLELOGRAM) {
			int six = width / 6;
			int[] xPoints = {x, x + six, x + width, x + 5 * six};
			int[] yPoints = {y + height, y, y, y + height};
			g.setColor(BlockColor);
			g.fillPolygon(xPoints, yPoints, 4);
			g.setColor(LineColor);
			g.drawPolygon(xPoints, yPoints, 4);

		} else if (type == ShapeType.OVAL) {
			g.setColor(BlockColor);
			g.fillOval(x, y, width, height);
			g.setColor(LineColor);
			g.drawOval(x, y, width, height);
		} else if (type == ShapeType.HEXAGON) {
			int hghtHalf = height / 2;
			int wdthThird = width / 3;
			int[] xPoints = {x, x + wdthThird, x + 2 * wdthThird, x + width, x + 2 * wdthThird, x + wdthThird};
			int[] yPoints = {y + hghtHalf, y, y, y + hghtHalf, y + height, y + height};
			g.setColor(BlockColor);
			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(LineColor);
			g.drawPolygon(xPoints, yPoints, 6);
		} else if (type == ShapeType.FUNCTION) {
			g.setColor(BlockColor);
			g.fillRect(x, y, width, height);
			g.setColor(LineColor);
			g.drawRect(x, y, width, height);
			g.drawLine(x + 14, y, x + 14, y + height);
			g.drawLine(x + width - 14, y, x + width - 14, y + height);
		}
	}

	public void myDrawString (String text, int x, int y)
	{
		if (text.contains("\n")) {
			int npos = text.indexOf('\n');
			g.drawString(text.substring(0, npos), x, y);
			g.drawString(text.substring(npos + 1), x, y + 10);
		} else {
			g.drawString(text, x, y);
		}
	}
}
