package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.FormalParam;
import com.z0ltan.compilers.minitriangle.ast.SequentialParam;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
import com.z0ltan.compilers.minitriangle.ast.ConstDeclaration;
import com.z0ltan.compilers.minitriangle.ast.VarDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.UnaryExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;

public class AstPrinter implements Visitor {
  @Override
  public Object visit(Program program, Object arg) {
    int startPos = (int)arg;

    String header = "Program {";
    String field1 = "C = ";

    printWithNewlineAt(startPos, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    program.C.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(AssignCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(WhileCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(LetCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "LetCommand {";
    String field1 = "D = ";
    String field2 = ", C = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    cmd.D.accept(this, offset);
    printAt(offset, field2);
    cmd.C.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(FormalParam param, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialParam param, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallArgument callarg, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialArgument callarg, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "ConstDeclaration {";
    String field1 = "I = ";
    String field2 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    decl.I.accept(this, offset);
    printAt(offset, field2);
    decl.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(VarDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "VarDeclaration {";
    String field1 = "I = ";
    String field2 = ", T = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    decl.I.accept(this, offset);
    printAt(offset, field2);
    decl.T.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(FunctionDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(UnaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "SequentialDeclaration {";
    String field1 = "D1 = ";
    String field2 = ", D2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    decl.D1.accept(this, offset);
    printAt(offset, field2);
    decl.D2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(IntegerExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "IntegerExpression {";
    String field1 = "I = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    expr.I.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "VnameExpression {";
    String field1 = "V = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    expr.V.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(CallExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(UnaryExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "UnaryExpression {";
    String field1 = "O = ";
    String field2 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    expr.O.accept(this, offset);
    printAt(offset, field2);
    expr.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(BinaryExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "BinaryExpression {";
    String field1 = "E1 = ";
    String field2 = ", O = ";
    String field3 = ", E2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    expr.E1.accept(this, offset);
    printAt(offset, field2);
    expr.O.accept(this, offset);
    printAt(offset, field3);
    expr.E2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SimpleTypeDenoter td, Object arg) {
    return null;
  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {
    return null;
  }

  @Override
  public Object visit(Identifier id, Object arg) {
    return null;
  }

  @Override
  public Object visit(Operator op, Object arg) {
    return null;
  }

  @Override
  public Object visit(IntegerLiteral intlit, Object arg) {
    return null;
  }

  public void print(Program program) {
    if (program == null) {
      return;
    }

    visit(program, Integer.valueOf(0));
  }

  private void printAt(int offset, String text) {
    for (int i = 0; i < offset; i++) {
      System.out.print(" ");
    }
    System.out.print(text);
    System.out.flush();
  }

  private void printWithNewlineAt(int offset, String text) {
    printAt(offset, text);
    System.out.println();
  }
}

