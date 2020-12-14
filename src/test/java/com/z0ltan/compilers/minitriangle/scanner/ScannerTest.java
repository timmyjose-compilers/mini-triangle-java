package com.z0ltan.compilers.minitriangle;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import com.z0ltan.compilers.minitriangle.scanner.TokenType;
import com.z0ltan.compilers.minitriangle.scanner.Token;
import com.z0ltan.compilers.minitriangle.scanner.Scanner;

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
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.RETURN, "return"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.END, "end"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.BEGIN, "begin"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.BECOMES, ":="),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.INTEGER_LITERAL, "1"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.RETURN, "return"),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.OPERATOR, "+"),
          new ScannerTestCase(TokenType.IDENTIFIER, "y"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
          new ScannerTestCase(TokenType.END, "end"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
          new ScannerTestCase(TokenType.IDENTIFIER, "getint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "x"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
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
          new ScannerTestCase(TokenType.IDENTIFIER, "putint"),
          new ScannerTestCase(TokenType.LEFT_PAREN, "("),
          new ScannerTestCase(TokenType.IDENTIFIER, "fact"),
          new ScannerTestCase(TokenType.RIGHT_PAREN, ")"),
          new ScannerTestCase(TokenType.SEMICOLON, ";"),
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
    Scanner scanner = new Scanner(Paths.get("samples/isprime.mt"));
    for (Token token = scanner.scan(); token.kind != TokenType.EOF; token = scanner.scan()) {
      System.out.println(token);
    }
  }

  public void testIsprimeDegenerate() {
  }

  public void testScope() {
    Scanner scanner = new Scanner(Paths.get("samples/scope.mt"));
    for (Token token = scanner.scan(); token.kind != TokenType.EOF; token = scanner.scan()) {
      System.out.println(token);
    }

  }

  public void testScopeDegenerate() {

  }
  public void testScopeGiven() {
    Scanner scanner = new Scanner(Paths.get("samples/scope_given.mt"));
    for (Token token = scanner.scan(); token.kind != TokenType.EOF; token = scanner.scan()) {
      System.out.println(token);
    }

  }

  public void testScopeGivenDegenerate() {

  }
}
