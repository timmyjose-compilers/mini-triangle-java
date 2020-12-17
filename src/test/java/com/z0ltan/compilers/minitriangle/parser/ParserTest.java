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
import com.z0ltan.compilers.minitriangle.ast.Param;
import com.z0ltan.compilers.minitriangle.ast.FormalParam;
import com.z0ltan.compilers.minitriangle.ast.SequentialParam;
import com.z0ltan.compilers.minitriangle.ast.Argument;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
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

  public void testEmpty() {
    Parser parser = new Parser(Paths.get("samples/empty.mt"));
    Program program = parser.parse();
    assertNull(program);
  }

  public void testIntro() {
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

  public void testFactorial() {
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
    Program expectedAst = new Program(
        new LetCommand(
          new SequentialDeclaration(
            new SequentialDeclaration(
              new SequentialDeclaration(
                new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
                new VarDeclaration(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer")))),
              new VarDeclaration(new Identifier("z"), new SimpleTypeDenoter(new Identifier("Integer")))),
            new FunctionDeclaration(
              new Identifier("foo"), 
              new SequentialParam(
                new FormalParam(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
                new FormalParam(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer")))),
              new SimpleTypeDenoter(new Identifier("Integer")),
              new BinaryExpression(
                new BinaryExpression(
                  new VnameExpression(new SimpleVname(new Identifier("x"))), 
                  new Operator("+"), 
                  new IntegerExpression(new IntegerLiteral("1"))),
                new Operator("+"), 
                new VnameExpression(new SimpleVname(new Identifier("y")))))),
          new SequentialCommand(
            new SequentialCommand(
              new SequentialCommand(
                new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("3"))),
                new AssignCommand(new SimpleVname(new Identifier("y")), new IntegerExpression(new IntegerLiteral("4")))),
              new AssignCommand(new SimpleVname(new Identifier("z")), 
                new CallExpression(new SimpleVname(new Identifier("foo")), 
                  new SequentialArgument(new CallArgument(new VnameExpression(new SimpleVname(new Identifier("x")))),
                    new CallArgument(new VnameExpression(new SimpleVname(new Identifier("y")))))))),
            new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("z")))))));
    Parser parser = new Parser(Paths.get("samples/function.mt"));
    Program program = parser.parse();
  }

  public void testScope() {
    Program expectedAst =
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
              new VarDeclaration(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer")))),
            new SequentialCommand(
              new SequentialCommand(
                new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("11"))),
                new AssignCommand(new SimpleVname(new Identifier("y")), new IntegerExpression(new IntegerLiteral("22")))),
              new LetCommand(
                new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
                new SequentialCommand(
                  new SequentialCommand(
                    new LetCommand(
                      new VarDeclaration(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer"))),
                      new SequentialCommand(
                        new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("100"))),
                        new AssignCommand(new SimpleVname(new Identifier("x")), new VnameExpression(new SimpleVname(new Identifier("y")))))),
                    new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("y"))))),
                  new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("x")))))))));

    Parser parser = new Parser(Paths.get("samples/scope.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void testScopeGiven() {
    Program expectedAst = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
              new VarDeclaration(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer")))),
            new SequentialCommand(
              new SequentialCommand(
                new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("1"))),
                new AssignCommand(new SimpleVname(new Identifier("y")), new IntegerExpression(new IntegerLiteral("2")))),
              new LetCommand(
                new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
                new SequentialCommand(
                  new AssignCommand(new SimpleVname(new Identifier("x")), new VnameExpression(new SimpleVname(new Identifier("y")))),
                  new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("x")))))))));

    Parser parser = new Parser(Paths.get("samples/scope_given.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void testPrecedence() {
    Program expectedAst = 
      new Program(
          new LetCommand(
            new SequentialDeclaration(
              new VarDeclaration(new Identifier("sum"), new SimpleTypeDenoter(new Identifier("Integer"))),
              new SequentialDeclaration(
                new VarDeclaration(new Identifier("x"), new SimpleTypeDenoter(new Identifier("Integer"))),
                new SequentialDeclaration(
                  new VarDeclaration(new Identifier("y"), new SimpleTypeDenoter(new Identifier("Integer"))),
                  new VarDeclaration(new Identifier("z"), new SimpleTypeDenoter(new Identifier("Integer")))))),
            new SequentialCommand(
              new AssignCommand(new SimpleVname(new Identifier("sum")), new IntegerExpression(new IntegerLiteral("0"))),
              new SequentialCommand(
                new AssignCommand(new SimpleVname(new Identifier("x")), new IntegerExpression(new IntegerLiteral("1"))),
                new SequentialCommand(
                  new AssignCommand(new SimpleVname(new Identifier("y")), new IntegerExpression(new IntegerLiteral("2"))),
                  new SequentialCommand(
                    new AssignCommand(new SimpleVname(new Identifier("z")), new IntegerExpression(new IntegerLiteral("3"))),
                    new SequentialCommand(
                      new AssignCommand(
                        new SimpleVname(new Identifier("sum")),
                        new BinaryExpression(
                          new BinaryExpression(
                            new VnameExpression(new SimpleVname(new Identifier("x"))), 
                            new Operator("+"), 
                            new VnameExpression(new SimpleVname(new Identifier("y")))),
                          new Operator("*"),
                          new VnameExpression(new SimpleVname(new Identifier("z"))))),
                        new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("sum")))))))))));

    Parser parser = new Parser(Paths.get("samples/precedence.mt"));
    Program program = parser.parse();
  }
}
