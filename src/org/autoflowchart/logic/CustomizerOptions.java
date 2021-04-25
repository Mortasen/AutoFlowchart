package org.autoflowchart.logic;

import javafx.scene.paint.Color;

enum Shape
{
	SQUARE,
	RECTANGLE,
	ROUNDED,
	DIAMOND,
	PARALLELOGRAM,
	CIRCLE,
	ELLIPSE,
	HEXAGON,
	FUNCTION,
}

public class CustomizerOptions
{
	Color terminalColor;
	Color terminalOutlineColor;
	Shape terminalShape;
	double terminalSize;
	double terminalOutlineSize;

	Color processColor;
	Color processOutlineColor;
	Shape processShape;
	double processSize;
	double processOutlineSize;

	Color decisionColor;
	Color decisionOutlineColor;
	Shape decisionShape;
	double decisionSize;
	double decisionOutlineSize;

	Color functionColor;
	Color functionOutlineColor;
	Shape functionShape;
	double functionSize;
	double functionOutlineSize;

	Color outputColor;
	Color outputOutlineColor;
	Shape outputShape;
	double outputSize;
	double outputOutlineSize;
}
