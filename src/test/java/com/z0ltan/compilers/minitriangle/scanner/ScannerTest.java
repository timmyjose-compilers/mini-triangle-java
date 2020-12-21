package com.z0ltan.compilers.minitriangle.scanner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

public class ScannerTest extends TestCase {
  static class ScannerTestCase {
    TokenType expectedKind;
    String expectedSpelling;

    public ScannerTestCase(TokenType expectedKind, String expectedSpelling) {
      this.expectedKind = expectedKind;
      this.expectedSpelling = expectedSpelling;
    }
  }

  public ScannerTest( String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(ScannerTest.class);
  }

  public void testEmpty() {
    ScannerTestCase[] testCases = new ScannerTestCase[] { };
    Scanner scanner = new Scanner(Paths.get("samples/empty.mt"));

    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testNoop() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "c"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Char"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "c"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.CHARACTER_LITERAL, "&"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/noop.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testIntro() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.CONST, "const"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "7"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/intro.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testIntroDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.CONST, "const"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "7"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "m"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "n"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/intro_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testFunction() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "foo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "3"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "4"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "foo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/function.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);

  }

  public void testFunctionDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.FUNCTION, "func"),
          new ScannerTestCase(TokenType.IDENTIFIER, "foo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IS, "~"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "3"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "4"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "foo"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COMMA, ","),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/function_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testPrecedence() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "3"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER,"x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/precedence.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testPrecedenceDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "3"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER,"x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "z"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "sum"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/precedence_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testFactorial() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "5"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, ">"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/factorial.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testFactorialDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "5"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, ">"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.OPERATOR, "*"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "-"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/factorial_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testIsprime() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "count"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES,":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "10"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "count"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "<"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "\\="),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/isprime.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testIsprimeDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "count"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES,":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "10"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "/"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half1"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "count"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.WHILE, "while"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "<"),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.DO, "do"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "\\="),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IF, "if"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "i"),
          new ScannerTestCase(TokenType.OPERATOR, "="),
          new ScannerTestCase(TokenType.IDENTIFIER, "half"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.THEN, "then"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.ELSE, "else"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "0"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/isprime_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testScope() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "11"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "22"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN,  "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "100"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/scope.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testScopeDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "11"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "22"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN,  "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "100"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.END, "end"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/scope_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testScopeGiven() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN,  "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/scope_given.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);
  }

  public void testScopeGivenDegenerate() {
    ScannerTestCase[] testCases = new ScannerTestCase[] {
      new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN, "in"),
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "2"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.LET, "let"),
          new ScannerTestCase(TokenType.VAR, "var"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.COLON, ":"),
          new ScannerTestCase(TokenType.IDENTIFIER, "Integer"),
          new ScannerTestCase(TokenType.IN,  "in"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.END, "end"),
    };

    Scanner scanner = new Scanner(Paths.get("samples/scope_given_degenerate.mt"));
    for (int i = 0; i < testCases.length; i++) {
      Token token = scanner.scan();
      assertEquals(testCases[i].expectedKind, token.kind);
      assertEquals(testCases[i].expectedSpelling, token.spelling);
    }

    assertEquals(TokenType.EOF, scanner.scan().kind);

  }
}
