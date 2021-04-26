package org.autoflowchart.logic;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import org.autoflowchart.objects.Node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public class Parser
{
	public static void parse (String[] args) throws FileNotFoundException
	{
		for (int i = 0; i < 10; i++) {
			int x = i + 2;
			if (i == 3)
				continue;
			x += 4;
		}
		CompilationUnit cu = null;
		cu = JavaParser.parse(new FileInputStream("/files/Work/Scripting/Java/Programms/FlowchartGenerator/testcode2.java"));
		//printNode(cu, 0);
		List<ClassOrInterfaceDeclaration> classes;
		classes = cu.findAll(ClassOrInterfaceDeclaration.class);
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
		methods = cu.findAll(MethodDeclaration.class);
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
		//NodeList<Statement> statements = blockStmt.get().getStatements();

		Node firstNode = new Node("main()");
		Node penultNode = firstNode.connectStmt(blockStmt.get(), null, 0);
		Node lastNode = new Node("return;");
		penultNode.setNext(lastNode);
        /*
        System.out.println("LINES:");
        for (int i = 1; i < statements.size(); i++) {
            Statement stmt = statements.get(i);
            if (stmt.isAssertStmt()) {
            } else if (stmt.isBlockStmt()) {
                System.out.println("BLOCK STMT");
                System.out.println(stmt);
                BlockStmt temp;
                temp.
            } else if (stmt.isBreakStmt()) {
                System.out.println("BREAK STMT");
            }
        }*/
	}










    /*public static void printNode (Node node, int level)
    {
        List<Node> list = node.getChildNodes();
        System.out.println(new String(new char[level]).replace("\0", "  ") + node.toString());
        for (int i = 0; i < list.size(); i++) {
            printNode(list.get(i), level + 1);
        }
    }*/
}