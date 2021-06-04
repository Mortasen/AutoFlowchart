package org.autoflowchart.logic;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import org.autoflowchart.objects.Node;
import org.autoflowchart.objects.ShapeType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public class Parser
{
	public static Node parse (FileInputStream fileInputStream) throws FileNotFoundException
	{
		CompilationUnit compilationUnit = JavaParser.parse(fileInputStream);

		BlockStmt blockStmt = findMain(compilationUnit);

		return assembleFunction(findMain(compilationUnit));
	}

	public static Node parse (String code)
	{
		CompilationUnit compilationUnit = JavaParser.parse(code);

		return assembleFunction(findMain(compilationUnit));
	}

	public static BlockStmt findMain (CompilationUnit compilationUnit)
	{
		List<ClassOrInterfaceDeclaration> classes;
		classes = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
		ClassOrInterfaceDeclaration mainClass = null;
		for (int i = 0; i < classes.size(); i++) {
			ClassOrInterfaceDeclaration class_ = classes.get(i);
			if (class_.isPublic() && !class_.isLocalClassDeclaration()) {
				mainClass = class_;
				break;
			}
		}
		if (mainClass == null)
			System.out.println("MAIN CLASS NOT FOUND!");
		List<MethodDeclaration> methods;
		methods = compilationUnit.findAll(MethodDeclaration.class);
		MethodDeclaration mainMethod = null;
		for (int i = 0; i < methods.size(); i++) {
			MethodDeclaration method = methods.get(i);
			if (method.getName().toString().equals("main")) {
				mainMethod = method;
				break;
			}
		}
		if (mainMethod == null)
			System.out.println("MAIN METHOD NOT FOUND!");
		List<ExpressionStmt> lines;
		Optional<BlockStmt> blockStmt = mainMethod.findFirst(BlockStmt.class);
		return blockStmt.orElse(null);
	}

	public static Node assembleFunction (BlockStmt blockStmt)
	{
		Node firstNode = new Node("main()");
		firstNode.setType(ShapeType.ROUNDRECT);
		Node penultNode = (Node) firstNode.connectStmt(blockStmt, null, 0);
		Node lastNode = new Node("return;");
		lastNode.setType(ShapeType.ROUNDRECT);
		penultNode.setNext(lastNode);

		return firstNode;
	}
}