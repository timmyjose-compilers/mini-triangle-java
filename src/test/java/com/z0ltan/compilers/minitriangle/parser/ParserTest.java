package com.z0ltan.compilers.minitriangle.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.nio.file.Paths;

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
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.Vname;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.TypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.Expression;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class ParserTest extends TestCase {
  public ParserTest( String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ParserTest.class);
  }

  public void testParser() {
    assertTrue(true);
  }

  public void xtestEmpty() {
    Parser parser = new Parser(Paths.get("samples/empty.mt"));
    Program program = parser.parse();
    assertNull(program);
  }

  public void xtestIntro() {
    Program expectedAst = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new ConstDeclaration(new Identifier("m"), new IntegerExpression(new IntegerLiteral("7"))),
              new VarDeclaration(new Identifier("n"), new SimpleTypeDenoter(new Identifier("Integer")))),
            new SequentialCommand(
              new AssignCommand(new SimpleVname(new Identifier("n")),
                new BinaryExpression(
                  new BinaryExpression(new IntegerExpression(new IntegerLiteral("2")), new Operator("*"), new VnameExpression(new SimpleVname(new Identifier("m")))), 
                  new Operator("*"),
                  new VnameExpression(new SimpleVname(new Identifier("n"))))),
              new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("n")))))));
    Parser parser = new Parser(Paths.get("samples/intro.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void xtestFactorial() {
    Program expectedAst = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
              new VarDeclaration(new Identifier("fact"), new SimpleTypeDenoter(new Identifier("Integer")))
              ),
            new SequentialCommand(
              new SequentialCommand(
                new SequentialCommand(
                  new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("5"))),
                  new AssignCommand(new SimpleVname(new Identifier("fact")), new IntegerExpression(new IntegerLiteral("1")))),
                new WhileCommand(new BinaryExpression(new VnameExpression(new SimpleVname(new Identifier("x"))), 
                    new Operator(">"), 
                    new IntegerExpression(new IntegerLiteral("0"))),
                  new SequentialCommand(
                    new AssignCommand(new SimpleVname(new Identifier("fact")), new BinaryExpression(new VnameExpression(new SimpleVname(new Identifier("fact"))),
                        new Operator("*"), new VnameExpression(new SimpleVname(new Identifier("x"))))),
                    new AssignCommand(new SimpleVname(new Identifier("x")), new BinaryExpression(new VnameExpression(new SimpleVname(new Identifier("x"))),
                        new Operator("-"), new IntegerExpression(new IntegerLiteral("1"))))))),
              new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("fact")))))));

    Parser parser = new Parser(Paths.get("samples/factorial.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }


  public void testFunction() {
    Program expectedAst = null;
    Parser parser = new Parser(Paths.get("samples/function.mt"));
    Program program = parser.parse();
    System.out.println(program);
  }

  public void xtestScope() {
    Program expectedAst = null;
    Parser parser = new Parser(Paths.get("samples/scope.mt"));
    Program program = parser.parse();
    //System.out.println(program);

  }

  public void xtestScopeGiven() {
    Program expectedAst = null;
    Parser parser = new Parser(Paths.get("samples/scope_given.mt"));
    Program program = parser.parse();
    //System.out.println(program);

  }

  public void xtestPrecedence() {
    Program expectedAst = null;
    Parser parser = new Parser(Paths.get("samples/precedence.mt"));
    Program program = parser.parse();
    //System.out.println(program);
  }
}
