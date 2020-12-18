package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.nio.file.Paths;

import com.z0ltan.compilers.minitriangle.parser.Parser;
import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.Command;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.Declaration;
import com.z0ltan.compilers.minitriangle.ast.VarDeclaration;
import com.z0ltan.compilers.minitriangle.ast.ConstDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.Vname;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.TypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.Expression;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.ParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FormalParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.Argument;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class CheckerTest extends TestCase {
  public CheckerTest( String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CheckerTest.class);
  }

  public void testEmpty() {
    Parser parser = new Parser(Paths.get("samples/empty.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testIntro() {
    Parser parser = new Parser(Paths.get("samples/intro.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testFactorial() {
    Parser parser = new Parser(Paths.get("samples/factorial.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testFunction() {
    Parser parser = new Parser(Paths.get("samples/function.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testScope() {
    Parser parser = new Parser(Paths.get("samples/scope.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testScopeGiven() {
    Parser parser = new Parser(Paths.get("samples/scope_given.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }

  public void testPrecedence() {
    Parser parser = new Parser(Paths.get("samples/precedence.mt"));
    Program program = parser.parse();

    Checker checker = new Checker();
    checker.check(program);

    AstPrinter printer = new AstPrinter();
    printer.print(program);
  }
}

