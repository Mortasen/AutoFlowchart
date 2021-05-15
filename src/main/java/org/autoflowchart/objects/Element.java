package org.autoflowchart.objects;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;

import java.util.Optional;

public class Element
{
	private Node node;
	protected Node next;
	protected int waitsFor;

	public Element () {}

	public Element (Node node)
	{
		this.node = node;
	}

	public Node getNode ()
	{
		return this.node;
	}

	public void setNode (Node node)
	{
		this.node = node;
	}

	public Node getNext ()
	{
		return next;
	}

	public void setNext (Node next)
	{
		this.next = next;
	}

	public int getWaitsFor ()
	{
		return waitsFor;
	}

	public void setWaitsFor (int waitsFor)
	{
		this.waitsFor = waitsFor;
	}

	/*
	Creates new node from statement, connects to this element and returns the last node.
	 */
	public Element connectStmt (Statement stmt, Elements waitList, int level)
	{
		Element lastElement;
		// Determine what kind of statement is stmt, and call corresponding method
		if (stmt.isAssertStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isBlockStmt()) {
			lastElement = this.connectBlockStmt(stmt.asBlockStmt(), waitList, level);
		} else if (stmt.isBreakStmt()) {
			lastElement = this.connectBreakStmt(stmt.asBreakStmt(), waitList, level);
		} else if (stmt.isContinueStmt()) {
			lastElement = this.connectContinueStmt(stmt.asContinueStmt(), waitList, level);
		} else if (stmt.isDoStmt()) {
			lastElement = this.connectDoStmt(stmt.asDoStmt(), waitList, level);
		} else if (stmt.isEmptyStmt()) {
			lastElement = this.connectEmptyStmt(stmt.asEmptyStmt(), waitList, level);
		} else if (stmt.isExplicitConstructorInvocationStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isExpressionStmt()) {
			lastElement = this.connectExpressionStmt(stmt.asExpressionStmt(), waitList, level);
		} else if (stmt.isForeachStmt()) {
			lastElement = this.connectForeachStmt(stmt.asForeachStmt(), waitList, level);
		} else if (stmt.isForStmt()) {
			lastElement = this.connectForStmt(stmt.asForStmt(), waitList, level);
		} else if (stmt.isIfStmt()) {
			lastElement = this.connectIfStmt(stmt.asIfStmt(), waitList, level);
		} else if (stmt.isLabeledStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isLocalClassDeclarationStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isReturnStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isSwitchStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isSynchronizedStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isThrowStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isTryStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isUnparsableStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isWhileStmt()) {
			lastElement = this.connectWhileStmt(stmt.asWhileStmt(), waitList, level);
		} else {
			throw new RuntimeException("Unknown statement type.");
		}

		return lastElement;
	}

	/*
	Sequentially connects several nodes to this and returns the last node.
	 */
	Element connectStmts (NodeList<Statement> stmts, Elements waitList, int level)
	{
		Element currentNode = this;
		for (Statement stmt : stmts)
		{
			Element newElement = currentNode.connectStmt(stmt, waitList, level);
			if (newElement == null)
				break;
			currentNode = newElement;
		}
		return currentNode;
	}

	Element connectBlockStmt (BlockStmt blockStmt, Elements waitList, int level)
	{
		return this.connectStmts(blockStmt.getStatements(), waitList, level);
	}

	Element connectBreakStmt (BreakStmt breakStmt, Elements waitList, int level)
	{
		this.waitsFor = 1;
		waitList.add(this);
		return null;
	}

	Element connectContinueStmt (ContinueStmt continueStmt, Elements waitList, int level)
	{
		this.waitsFor = 2;
		waitList.add(this);
		return null;
	}

	Element connectDoStmt (DoStmt doStmt, Elements waitList, int level)
	{
		Element currentElement = this.connectStmt(doStmt.getBody(), waitList, level + 1);
		Node firstNode = this.getNext();
		String condition  = doStmt.getCondition().toString();
		Node conditionNode = new Node(condition, level);
		conditionNode.setNext(firstNode);
		currentElement.setNext(conditionNode);
		return conditionNode;
	}

	Element connectEmptyStmt (EmptyStmt emptyStmt, Elements waitList, int level)
	{
		return this;
	}

	Element connectExpressionStmt (ExpressionStmt expressionStmt, Elements waitList, int level)
	{
		Node newNode = new Node(expressionStmt.toString(), level);
		this.setNext(newNode);
		return newNode;
	}

	Elements connectForeachStmt (ForeachStmt foreachStmt, Elements waitList, int level)
	{
		waitList = new Elements();
		String variable = foreachStmt.getVariable().toString();
		String iterable = foreachStmt.getIterable().toString();

		Node checkNode = new Node( iterable + ".hasNext()", level);
		this.setNext(checkNode);

		Node updateNode = new Node( variable + " = " + iterable + ".next()", level);
		checkNode.setNext(updateNode);

		Element lastElement = updateNode.connectStmt(foreachStmt.getBody(), waitList, level + 1);
		lastElement.setNext(checkNode);

		Elements elementsToConnect = new Elements();
		elementsToConnect.add(checkNode);

		for (Element element : waitList.elements)
		{
			// break with hanging, continue connect to update
			if (element.waitsFor == 1) {
				elementsToConnect.add(element);
			} else if (element.waitsFor == 2) {
				element.setNext(checkNode);
			}
		}

		return elementsToConnect;
	}

	Element connectForStmt (ForStmt forStmt, Elements waitList, int level)
	{
		waitList = new Elements();
		Node prevNode = this.node;

		NodeList<Expression> initExprs = forStmt.getInitialization();
		if (initExprs.size() > 0) {
			String init = initExprs.toString();
			init = init.substring(1, init.length() - 1) + ";";
			prevNode = new Node(init, level);
			this.setNext(prevNode);
		}

		Element currentElement;

		Optional<Expression> compareExpr = forStmt.getCompare();
		if (compareExpr.isPresent()) {
			Node compareNode = new Node(compareExpr.get().toString(), level);
			prevNode.setNext(compareNode);
			currentElement = compareNode;
		} else
			currentElement = prevNode;
		currentElement  = currentElement.connectStmt(forStmt.getBody(), waitList, level + 1);
		Node firstNode = prevNode.getNext();

		Node newCycleNode = firstNode;

		NodeList<Expression> updateExprs = forStmt.getUpdate();
		if (updateExprs.size() > 0) {
			String update = updateExprs.toString();
			update = update.substring(1, update.length() - 1) + ";";
			Node updateNode = new Node(update, level + 1);
			currentElement.setNext(updateNode);
			currentElement = updateNode;
			newCycleNode = updateNode;
		}
		currentElement.setNext(firstNode);

		Elements elements = new Elements();
		if (compareExpr.isPresent())
			elements.add(firstNode);

		for (Element element : waitList.elements)
		{
			if (element.waitsFor == 1) {
				elements.add(element);
			} else if (element.waitsFor == 2) {
				element.setNext(newCycleNode);
			}
		}

		return elements;
	}

	Element connectIfStmt (IfStmt ifStmt, Elements waitList, int level)
	{
		Node conditionNode = new Node(ifStmt.getCondition().toString(), level);
		this.setNext(conditionNode);
		Element lastElement1 = conditionNode.connectStmt(ifStmt.getThenStmt(), waitList, level + 1);

		Optional<Statement> elseStmt = ifStmt.getElseStmt();
		Element lastElement2;
		if (elseStmt.isPresent()) {
			lastElement2 = conditionNode.connectStmt(elseStmt.get(), waitList, level);
		} else {
			lastElement2 = conditionNode;
		}
		Elements elements = new Elements();
		if (lastElement1 != null) elements.add(lastElement1);
		if (lastElement2 != null) elements.add(lastElement2);
		return elements;
	}

	Element connectWhileStmt (WhileStmt whileStmt, Elements waitList, int level)
	{
		waitList = new Elements();
		Node prevNode = this.node;

		Expression compareExpr = whileStmt.getCondition();
		Node compareNode = new Node(compareExpr.toString(), level);
		prevNode.setNext(compareNode);
		Element currentElement = compareNode;
		currentElement  = currentElement.connectStmt(whileStmt.getBody(), waitList, level + 1);
		Node firstNode = prevNode.getNext();

		Node newCycleNode = firstNode;

		currentElement.setNext(firstNode);

		Elements elements = new Elements();
		elements.add(firstNode);

		for (Element element : waitList.elements)
		{
			if (element.waitsFor == 1) {
				elements.add(element);
			} else if (element.waitsFor == 2) {
				element.setNext(newCycleNode);
			}
		}

		return elements;
	}
}
