package org.autoflowchart.objects;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;

import java.util.Optional;

public class Element
{
	public Node next;

	/*
	Creates new node from statement and connects to this element.
	 */
	public Node connectStmt (Statement stmt, Nodes waitList, int level)
	{
		Node lastNode;
		if (stmt.isAssertStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isBlockStmt()) {
			lastNode = this.connectBlockStmt(stmt.asBlockStmt(), waitList, level);
		} else if (stmt.isBreakStmt()) {
			lastNode = this.connectBreakStmt(stmt.asBreakStmt(), waitList, level);
		} else if (stmt.isContinueStmt()) {
			lastNode = this.connectContinueStmt(stmt.asContinueStmt(), waitList, level);
		} else if (stmt.isDoStmt()) {
			lastNode = this.connectDoStmt(stmt.asDoStmt(), waitList, level);
		} else if (stmt.isEmptyStmt()) {
			lastNode = this.connectEmptyStmt(stmt.asEmptyStmt(), waitList, level);
		} else if (stmt.isExplicitConstructorInvocationStmt()) {
			throw new NotImplementedException();
		} else if (stmt.isExpressionStmt()) {
			lastNode = this.connectExpressionStmt(stmt.asExpressionStmt(), waitList, level);
		} else if (stmt.isForeachStmt()) {
			lastNode = this.connectForeachStmt(stmt.asForeachStmt(), waitList, level);
		} else if (stmt.isForStmt()) {
			lastNode = this.connectForStmt(stmt.asForStmt(), waitList, level);
		} else if (stmt.isIfStmt()) {
			lastNode = this.connectIfStmt(stmt.asIfStmt(), waitList, level);
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
			lastNode = this.connectWhileStmt(stmt.asWhileStmt(), waitList, level);
		} else {
			throw new RuntimeException("Unknown statement type.");
		}
		if (lastNode != null)
			lastNode.level = level;
		return lastNode;
	}

	Element connectStmts (NodeList<Statement> stmts, Nodes waitList, int level)
	{
		Element currentNode = this;
		for (Statement stmt : stmts)
		{
			Node newNode = currentNode.connectStmt(stmt, waitList, level);
			if (newNode == null)
				break;
			currentNode = newNode;
		}
		return currentNode;
	}

	Node connectBlockStmt (BlockStmt blockStmt, Nodes waitList, int level)
	{
		return this.connectStmts(blockStmt.getStatements(), waitList, level);
	}

	Node connectBreakStmt (BreakStmt breakStmt, Nodes waitList, int level)
	{
		this.waitsFor = 1;
		waitList.add(this);
		return null;
	}

	Node connectContinueStmt (ContinueStmt continueStmt, Nodes waitList, int level)
	{
		this.waitsFor = 2;
		waitList.add(this);
		return null;
	}

	Node connectDoStmt (DoStmt doStmt, Nodes waitList, int level)
	{
		Node currentNode = this.connectStmt(doStmt.getBody(), waitList, level + 1);
		Node firstNode = this.getNext();
		String condition  = doStmt.getCondition().toString();
		Node conditionNode = new Node(condition, level);
		conditionNode.setNext(firstNode);
		currentNode.setNext(conditionNode);
		return conditionNode;
	}

	Node connectEmptyStmt (EmptyStmt emptyStmt, Nodes waitList, int level)
	{
		return (Node) this;
	}

	Node connectExpressionStmt (ExpressionStmt expressionStmt, Nodes waitList, int level)
	{
		Node newNode = new Node(expressionStmt.toString(), level);
		this.setNext(newNode);
		return newNode;
	}

	Node connectForeachStmt (ForeachStmt foreachStmt, Nodes waitList, int level)
	{
		waitList = new Nodes();
		String variable = foreachStmt.getVariable().toString();
		String iterable = foreachStmt.getIterable().toString();

		Node checkNode = new Node( iterable + ".hasNext()", level);
		this.setNext(checkNode);

		Node updateNode = new Node( variable + " = " + iterable + ".next()", level);
		checkNode.setNext(updateNode);

		Node lastNode = updateNode.connectStmt(foreachStmt.getBody(), waitList, level + 1);
		lastNode.setNext(checkNode);

		Nodes nodesToConnect = new Nodes();
		nodesToConnect.add(checkNode);

		for (Node node : waitList.nodes)
		{
			// break with hanging, continue connect to update
			if (node.waitsFor == 1) {
				nodesToConnect.add(node);
			} else if (node.waitsFor == 2) {
				node.setNext(checkNode);
			}
		}

		return nodesToConnect;
	}

	Node connectForStmt (ForStmt forStmt, Nodes waitList, int level)
	{
		waitList = new Nodes();
		Node prevNode = this;

		NodeList<Expression> initExprs = forStmt.getInitialization();
		if (initExprs.size() > 0) {
			String init = initExprs.toString();
			init = init.substring(1, init.length() - 1) + ";";
			prevNode = new Node(init, level);
			this.setNext(prevNode);
		}

		Node currentNode;

		Optional<Expression> compareExpr = forStmt.getCompare();
		if (compareExpr.isPresent()) {
			Node compareNode = new Node(compareExpr.get().toString(), level);
			prevNode.setNext(compareNode);
			currentNode = compareNode;
		} else
			currentNode = prevNode;
		currentNode  = currentNode.connectStmt(forStmt.getBody(), waitList, level + 1);
		Node firstNode = prevNode.getNext();

		Node newCycleNode = firstNode;

		NodeList<Expression> updateExprs = forStmt.getUpdate();
		if (updateExprs.size() > 0) {
			String update = updateExprs.toString();
			update = update.substring(1, update.length() - 1) + ";";
			Node updateNode = new Node(update, level + 1);
			currentNode.setNext(updateNode);
			currentNode = updateNode;
			newCycleNode = updateNode;
		}
		currentNode.setNext(firstNode);

		Nodes nodes = new Nodes();
		if (compareExpr.isPresent())
			nodes.add(firstNode);

		for (Node node : waitList.nodes)
		{
			if (node.waitsFor == 1) {
				nodes.add(node);
			} else if (node.waitsFor == 2) {
				node.setNext(newCycleNode);
			}
		}

		return nodes;
	}

	Node connectIfStmt (IfStmt ifStmt, Nodes waitList, int level)
	{
		Node conditionNode = new Node(ifStmt.getCondition().toString(), level);
		this.setNext(conditionNode);
		Node lastNode1 = conditionNode.connectStmt(ifStmt.getThenStmt(), waitList, level + 1);

		Optional<Statement> elseStmt = ifStmt.getElseStmt();
		Node lastNode2;
		if (elseStmt.isPresent()) {
			lastNode2 = conditionNode.connectStmt(elseStmt.get(), waitList, level);
		} else {
			lastNode2 = conditionNode;
		}
		Nodes nodes = new Nodes();
		if (lastNode1 != null) nodes.add(lastNode1);
		if (lastNode2 != null) nodes.add(lastNode2);
		return nodes;
	}

	Node connectWhileStmt (WhileStmt whileStmt, Nodes waitList, int level)
	{
		waitList = new Nodes();
		Node prevNode = this;

		Node currentNode;

		Expression compareExpr = whileStmt.getCondition();
		Node compareNode = new Node(compareExpr.toString(), level);
		prevNode.setNext(compareNode);
		currentNode = compareNode;
		currentNode  = currentNode.connectStmt(whileStmt.getBody(), waitList, level + 1);
		Node firstNode = prevNode.getNext();

		Node newCycleNode = firstNode;

		currentNode.setNext(firstNode);

		Nodes nodes = new Nodes();
		nodes.add(firstNode);

		for (Node node : waitList.nodes)
		{
			if (node.waitsFor == 1) {
				nodes.add(node);
			} else if (node.waitsFor == 2) {
				node.setNext(newCycleNode);
			}
		}

		return nodes;
	}
}
