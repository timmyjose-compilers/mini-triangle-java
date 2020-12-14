package com.z0ltan.compilers.minitriangle.parser;

import java.nio.file.Path;

import com.z0ltan.compilers.minitriangle.scanner.TokenType;
import com.z0ltan.compilers.minitriangle.scanner.Token;
import com.z0ltan.compilers.minitriangle.scanner.Scanner;
import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class Parser {
  private Scanner scanner;
  private Token currentToken;

  public Parser(String input) {
    this.scanner = new Scanner(input);
    this.currentToken = null;
  }

  public Parser(Path filepath) {
    this.scanner = new Scanner(filepath);
    this.currentToken = null;
  }

  private void acceptIt() {
    currentToken = scanner.scan();
  }

  private void accept(TokenType expectedKind) {
    if (currentToken.kind != expectedKind) {
      ErrorReporter.report("expected to accept token of kind " + expectedKind + ", got token of kind " + currentToken.kind,
          currentToken.position.line,
          currentToken.position.column);
    }

    currentToken = scanner.scan();
  }

  // program ::= single-Command
  Program parseProgram() {
    return null;
  }

  // start-symbol ::= program
  public Program parse() {
    Program program = parseProgram();
    accept(TokenType.EOF);
    return program;
  }
}
