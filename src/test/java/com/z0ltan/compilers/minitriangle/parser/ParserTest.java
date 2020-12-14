package com.z0ltan.compilers.minitriangle.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.assertEquals;

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

  public void testEmpty() {
    Program expectedAst = null;
    Parser parser = new Parser(Paths.get("samples/empty.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void testIntro() {
    Program expectedAst = new Program(new LetCommand(new SequentialDeclaration(new ConstDeclaration(new Identifier("m"), new IntegerExpression(new IntegerLiteral("7"))),
            new VarDeclaration(new Identifier("n"), new SimpleTypeDenoter(new Identifier("Integer")))),
          new SequentialCommand(new AssignCommand(new SimpleVname(new Identifier("n")), 
              new BinaryExpression(new BinaryExpression(new IntegerExpression(new IntegerLiteral("2")), new Operator("*"), 
                  new VnameExpression(new SimpleVname(new Identifier("m")))),
                new Operator("*"), new VnameExpression(new SimpleVname(new Identifier("n"))))),
            new CallCommand(new Identifier("putint"), new VnameExpression(new SimpleVname(new Identifier("n")))))));

    Parser parser = new Parser(Paths.get("samples/intro.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void testFactorial() {

  }

  public void testFunction() {

  }

  public void testScope() {

  }

  public void testScopeGiven() {

  }

  public void testPrecedence() {

  }
}


