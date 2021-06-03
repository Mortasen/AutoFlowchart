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

	public CustomizerOptions(){
		terminalColor = new Color(0, 0, 255);
		terminalOutlineColor = new Color(0, 122, 78);
	    terminalShape = ShapeType.OVAL;
		 terminalSize = 2;

		 processColor = new Color(103, 224, 43);; //прямоугольник
		 processOutlineColor = new Color(184, 63, 144);;
		 processShape = ShapeType.DIAMOND;
		 processSize = 1.5;

		 decisionColor = new Color(10, 10, 42);; //иф
		 decisionOutlineColor = new Color(88, 88, 179);;
		 decisionShape = ShapeType.PARALLELOGRAM;
		 decisionSize = 2;

		 functionColor = new Color(27, 154, 86);; //функция
		 functionOutlineColor = new Color(52, 52, 8);;
		 functionShape = ShapeType.HEXAGON;
		 functionSize = 1.5;

		 outputColor = new Color(203, 30, 92);; //паралеллограм
		 outputOutlineColor = new Color(50, 6, 25);;
		 outputShape = ShapeType.FUNCTION;
		 outputSize = 1;

		OutlineSize = 1.5F;

	}
}
