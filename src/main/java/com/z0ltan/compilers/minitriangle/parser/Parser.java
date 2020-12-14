package com.z0ltan.compilers.minitriangle.parser;

import java.nio.file.Path;

import com.z0ltan.compilers.minitriangle.scanner.TokenType;
import com.z0ltan.compilers.minitriangle.scanner.Token;
import com.z0ltan.compilers.minitriangle.scanner.Scanner;
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
    acceptIt();
    if (currentToken.kind == TokenType.EOF) {
      return null;
    }

    Program program = parseProgram();
    accept(TokenType.EOF);
    return program;
  }
}
