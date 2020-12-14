package com.z0ltan.compilers.minitriangle.parser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import com.z0ltan.compilers.minitriangle.ast.Program;
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
    Program expectedAst = new Program(null);
    Parser parser = new Parser(Paths.get("samples/empty.mt"));
    Program program = parser.parse();
    assertEquals(expectedAst, program);
  }

  public void testIntro() {

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


