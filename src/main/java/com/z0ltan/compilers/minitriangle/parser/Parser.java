package com.z0ltan.compilers.minitriangle.parser;

import java.nio.file.Path;

import com.z0ltan.compilers.minitriangle.scanner.TokenType;
import com.z0ltan.compilers.minitriangle.scanner.Token;
import com.z0ltan.compilers.minitriangle.scanner.Scanner;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
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
import com.z0ltan.compilers.minitriangle.ast.ProcedureDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.Vname;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.TypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.Expression;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.CharacterExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.UnaryExpression;
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
import com.z0ltan.compilers.minitriangle.ast.CharacterLiteral;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class Parser {
  private Scanner scanner;
  private Token currentToken;
  private SourcePosition currentPosition;

  public Parser(String input) {
    this.scanner = new Scanner(input);
    initialize();
  }

  public Parser(Path filepath) {
    this.scanner = new Scanner(filepath);
    initialize();
  }

  private void initialize() {
    this.currentToken = scanner.scan();
    this.currentPosition = null;
  }

  private void start(SourcePosition sourcePosition) {
    sourcePosition.start = new SourcePosition.Position(currentToken.line, currentToken.column);
  }

  private void finish(SourcePosition sourcePosition) {
    sourcePosition.finish = new SourcePosition.Position(currentToken.line, currentToken.column);
  }

  private void acceptIt() {
    currentToken = scanner.scan();
  }

  private void accept(TokenType expectedKind) {
    if (currentToken.kind != expectedKind) {
      ErrorReporter.report("expected to accept token of kind " + expectedKind + ", got token of kind " + currentToken.kind,
          currentToken.line,
          currentToken.column);
    }

    currentToken = scanner.scan();
  }

  // program ::= single-Command
  private Program parseProgram() {
    SourcePosition progPos = new SourcePosition();
    start(progPos);
    Command cmd = parseSingleCommand();
    finish(progPos);

    return new Program(cmd, progPos);
  }

  // single-Command ::= AssignCommand | CallCommand | IfCommand | WhileCommand | LetCommand | "(" Command ")"
  private Command parseSingleCommand() {
    Command cmd = null;
    SourcePosition cmdPos = new SourcePosition();
    start(cmdPos);

    switch (currentToken.kind) {
      case IDENTIFIER: 
        {
          Identifier id = parseIdentifier();

          switch (currentToken.kind) {
            case BECOMES:
              {
                acceptIt();
                Expression expr = parseExpression();
                finish(cmdPos);
                cmd = new AssignCommand(new SimpleVname(id, cmdPos), expr, cmdPos);
              }
              break;

            case LEFT_PAREN:
              {
                acceptIt();
                if (currentToken.kind == TokenType.VAR) {
                  acceptIt();
                }
                Argument args = parseArgument();
                accept(TokenType.RIGHT_PAREN);
                finish(cmdPos);
                cmd = new CallCommand(id, args, cmdPos);
              }
              break;

            default:
              ErrorReporter.report("expected either a \":=\" or a \"(\" here, got unexpected token of kind " + currentToken.kind,
                  currentToken.line,
                  currentToken.column);
          }
        }
        break;

      case IF:
        {
          acceptIt();
          Expression expr = parseExpression();
          accept(TokenType.THEN);
          Command cmd2 = parseSingleCommand();
          accept(TokenType.ELSE);
          Command cmd3 = parseSingleCommand();
          finish(cmdPos);
          cmd = new IfCommand(expr, cmd2, cmd3, cmdPos);
        }
        break;

      case WHILE:
        {
          acceptIt();
          Expression expr = parseExpression();
          accept(TokenType.DO);
          Command cmd2 = parseSingleCommand();
          finish(cmdPos);
          cmd = new WhileCommand(expr, cmd2, cmdPos);
        }
        break;

      case LET:
        {
          acceptIt();
          Declaration decl = parseDeclaration();
          accept(TokenType.IN);
          Command cmd2 = parseCommand();
          finish(cmdPos);
          cmd = new LetCommand(decl, cmd2, cmdPos);
        }
        break;

      case BEGIN:
        {
          acceptIt();
          Command cmd2 = parseCommand();
          accept(TokenType.END);
          finish(cmdPos);
          cmd = cmd2;
        }
        break;

      default:
        ErrorReporter.report(currentToken.spelling + " cannot start a command",
            currentToken.line,
            currentToken.column);
    }

    return cmd;
  }

  // Command ::= single-Comamnd (";" single-Command)*
  private Command parseCommand() {
    SourcePosition cmdPos = new SourcePosition();
    start(cmdPos);

    Command cmd1 = parseSingleCommand();

    while (currentToken.kind == TokenType.SEMICOLON) {
      acceptIt();
      SourcePosition cmd2Pos = new SourcePosition();
      Command cmd2 = parseSingleCommand();
      finish(cmdPos);

      cmd1 = new SequentialCommand(cmd1, cmd2, cmdPos);
    }

    return cmd1;
  }

  // primary-Expression ::= IntegerExpression | VnameExpression | CallExpression | UnaryExpression | BinaryExpression 
  Expression parsePrimaryExpression() {
    SourcePosition exprPos = new SourcePosition();
    start(exprPos);
    Expression expr = null;

    switch (currentToken.kind) {
      case INTEGER_LITERAL:
        {
          IntegerLiteral il = parseIntegerLiteral();
          finish(exprPos);
          expr = new IntegerExpression(il, exprPos);
        }
        break;

      case CHARACTER_LITERAL:
        {
          CharacterLiteral cl = parseCharacterLiteral();
          finish(exprPos);
          expr = new CharacterExpression(cl, exprPos);
        }
        break;

      case IDENTIFIER:
        {
          Identifier id = parseIdentifier();
          if (currentToken.kind == TokenType.LEFT_PAREN) {
            acceptIt();
            Argument args = parseArgument();
            accept(TokenType.RIGHT_PAREN);
            finish(exprPos);
            expr = new CallExpression(id, args, exprPos);
          } else {
            finish(exprPos);
            expr = new VnameExpression(new SimpleVname(id));
          }
        }
        break;

      case OPERATOR:
        {
          Operator op = parseOperator();
          Expression expr2 = parsePrimaryExpression();
          finish(exprPos);
          expr = new UnaryExpression(op, expr2, exprPos);
        }
        break;

      case LEFT_PAREN:
        {
          acceptIt();
          Expression expr2 = parseExpression();
          accept(TokenType.RIGHT_PAREN);
          finish(exprPos);
          expr = expr2;
        }
        break;

      default:
        ErrorReporter.report(currentToken.spelling + " cannot start an expression",
            currentToken.line,
            currentToken.column);
    }

    return expr;
  }

  // argument ::= call-argument ("," call-argument)*
  private Argument parseArgument() {
    SourcePosition argPos = new SourcePosition();
    start(argPos);
    Argument arg1 = parseCallArgument();

    while (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      Argument arg2 = parseCallArgument();
      finish(argPos);
      arg1 = new SequentialArgument(arg1, arg2, argPos);
    }

    return arg1;
  }

  // call-argument ::= expression
  private Argument parseCallArgument() {
    SourcePosition argPos = new SourcePosition();
    start(argPos);
    Expression expr = parseExpression();
    finish(argPos);

    return new CallArgument(expr, argPos);
  }

  // vname ::= Identifier
  private Vname parseVname() {
    SourcePosition vnamePos = new SourcePosition();
    start(vnamePos);
    Identifier id = parseIdentifier();
    finish(vnamePos);

    return new SimpleVname(id, vnamePos);
  }

  // Expression ::= primary-Expression (Operator primary-Expression)*
  private Expression parseExpression() {
    SourcePosition exprPos = new SourcePosition();
    start(exprPos);
    Expression expr1 = parsePrimaryExpression();

    while (currentToken.kind == TokenType.OPERATOR) {
      Operator op = parseOperator();
      Expression expr2 = parsePrimaryExpression();
      finish(exprPos);
      expr1 = new BinaryExpression(expr1, op, expr2, exprPos);
    }

    return expr1;
  }

  // type-denoter ::= Identifier
  private TypeDenoter parseTypeDenoter() {
    SourcePosition tdPos = new SourcePosition();
    start(tdPos);
    Identifier id = parseIdentifier();
    finish(tdPos);

    return new SimpleTypeDenoter(id, tdPos);
  }

  // single-Declaration ::= ConstDeclaration | VarDeclaration | FunctionDeclaration
  private Declaration parseSingleDeclaration() {
    Declaration decl = null;
    SourcePosition declPos = new SourcePosition();
    start(declPos);

    switch (currentToken.kind) {
      case CONST:
        {
          acceptIt();
          Identifier id = parseIdentifier();
          accept(TokenType.IS);
          Expression expr = parseExpression();
          finish(declPos);
          decl = new ConstDeclaration(id, expr, declPos);
        }
        break;

      case VAR:
        {
          acceptIt();
          Identifier id = parseIdentifier();
          accept(TokenType.COLON);
          TypeDenoter td = parseTypeDenoter();
          finish(declPos);
          decl = new VarDeclaration(id, td, declPos);
        }
        break;

      case PROCEDURE:
        {
          acceptIt();
          Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          ParamDeclaration p = null;

          if (currentToken.kind == TokenType.RIGHT_PAREN) {
            acceptIt();
          } else {
            p = parseParamDeclaration();
            accept(TokenType.RIGHT_PAREN);
          }
          accept(TokenType.IS);
          Command cmd = parseCommand();
          finish(declPos);
          decl = new ProcedureDeclaration(id, p, cmd, declPos);
        }
        break;

      case FUNCTION:
        {
          acceptIt();
          Identifier id = parseIdentifier();
          accept(TokenType.LEFT_PAREN);
          ParamDeclaration p = null;

          if (currentToken.kind == TokenType.RIGHT_PAREN) {
            acceptIt();
          } else {
            p = parseParamDeclaration();
            accept(TokenType.RIGHT_PAREN);
          }
          accept(TokenType.COLON);
          TypeDenoter td = parseTypeDenoter();
          accept(TokenType.IS);
          Expression expr = parseExpression();
          finish(declPos);
          decl = new FunctionDeclaration(id, p, td, expr, declPos);
        }
        break;

      default:
        ErrorReporter.report(currentToken.spelling + " cannot start a declaration", 
            currentToken.line,
            currentToken.column);
    }

    return decl;
  }

  // param ::= formal-Param ("," formal-Param)*
  private ParamDeclaration parseParamDeclaration() {
    SourcePosition pPos = new SourcePosition();
    start(pPos);
    ParamDeclaration p1 = parseFormalParamDeclaration();

    while (currentToken.kind == TokenType.COMMA) {
      acceptIt();
      ParamDeclaration p2 = parseFormalParamDeclaration();
      finish(pPos);
      p1 = new SequentialParamDeclaration(p1, p2, pPos);
    }

    return p1;
  }

  // formal-param-declaration ::= Identifier ":" Type-denoter
  private ParamDeclaration parseFormalParamDeclaration() {
    SourcePosition fpPos = new SourcePosition();
    start(fpPos);
    Identifier id = parseIdentifier();
    accept(TokenType.COLON);
    TypeDenoter td = parseTypeDenoter();
    finish(fpPos);
    FormalParamDeclaration fp = new FormalParamDeclaration(id, td);

    return fp;
  }

  // declaration ::= single-Declaration (";" single-Declaration)*
  private Declaration parseDeclaration() {
    SourcePosition declPos = new SourcePosition();
    start(declPos);
    Declaration decl1 = parseSingleDeclaration();

    while (currentToken.kind == TokenType.SEMICOLON) {
      acceptIt();
      Declaration decl2 = parseSingleDeclaration();
      finish(declPos);
      decl1 = new SequentialDeclaration(decl1, decl2, declPos);
    }

    return decl1;
  }


  // identifier ::= letter (letter | digit)*
  private Identifier parseIdentifier() {
    SourcePosition idPos = new SourcePosition();
    start(idPos);
    finish(idPos);
    Identifier id = new Identifier(currentToken.spelling, idPos);
    acceptIt();

    return id;
  }

  // integer-literal ::= digit digit*
  private IntegerLiteral parseIntegerLiteral() {
    SourcePosition ilPos = new SourcePosition();
    start(ilPos);
    finish(ilPos);
    IntegerLiteral il = new IntegerLiteral(currentToken.spelling, ilPos);
    acceptIt();

    return il;
  }

  // character-literal ::= char
  private CharacterLiteral parseCharacterLiteral() {
    SourcePosition clPos = new SourcePosition();
    start(clPos);
    finish(clPos);
    CharacterLiteral cl = new CharacterLiteral(currentToken.spelling, clPos);
    acceptIt();

    return cl;
  }

  // operator ::= + | - | * | / | < | > | = | \= 
  private Operator parseOperator() {
    SourcePosition opPos = new SourcePosition();
    start(opPos);
    finish(opPos);
    Operator op = new Operator(currentToken.spelling, opPos);
    acceptIt();

    return op;
  }

  // start-symbol ::= program
  public Program parse() {
    if (currentToken.kind == TokenType.EOF) {
      return null;
    }

    Program program = parseProgram();
    accept(TokenType.EOF);

    return program;
  }
}
