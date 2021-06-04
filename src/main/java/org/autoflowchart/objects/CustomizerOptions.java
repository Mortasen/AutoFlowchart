package org.autoflowchart.objects;

//import javafx.scene.paint.Color;

import java.awt.*;

public class CustomizerOptions
{

	public float OutlineSize;
	//старт конец
	public Color terminalColor; //старт конец

	public Color terminalOutlineColor;
	public ShapeType terminalShape;
	public double terminalSize;


	public Color processColor; //прямоугольник
	public Color processOutlineColor;
	public ShapeType processShape;
	public double processSize;

	public Color decisionColor; //иф
	public Color decisionOutlineColor;
	public ShapeType decisionShape;
	public double decisionSize;

	public Color functionColor; //функция
	public Color functionOutlineColor;
	public ShapeType functionShape;
	public double functionSize;

	public Color outputColor; //паралеллограм
	public	Color outputOutlineColor;
	public ShapeType outputShape;
	public double outputSize;

	public CustomizerOptions() {
		terminalColor = new Color(255, 192, 255);
		terminalOutlineColor = new Color(0, 0, 0);
	    terminalShape = ShapeType.ROUNDRECT;
		 terminalSize = 1;

		 processColor = new Color(192, 255, 192); //прямоугольник
		 processOutlineColor = new Color(0, 0, 0);
		 processShape = ShapeType.RECT;
		 processSize = 1;

		 decisionColor = new Color(255, 255, 192); //иф
		 decisionOutlineColor = new Color(0, 0, 0);
		 decisionShape = ShapeType.DIAMOND;
		 decisionSize = 1;

		 functionColor = new Color(192, 192, 255); //функция
		 functionOutlineColor = new Color(0, 0, 0);
		 functionShape = ShapeType.FUNCTION;
		 functionSize = 1;

		 outputColor = new Color(192, 255, 255); //паралеллограм
		 outputOutlineColor = new Color(0, 0, 0);
		 outputShape = ShapeType.PARALLELOGRAM;
		 outputSize = 1;

		OutlineSize = 1.5F;

	}
}
