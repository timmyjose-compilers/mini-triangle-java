package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.FormalParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialParamDeclaration;
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
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    program.C.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(AssignCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "AssignCommand {";
    String field1 = "V = ";
    String field2 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    cmd.V.accept(this, offset);
    printAt(offset, field2);
    cmd.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;

  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "CallCommand {";
    String field1 = "I = ";
    String field2 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    cmd.I.accept(this, offset);
    printAt(offset, field2);
    cmd.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "IfCommand {";
    String field1 = "E = ";
    String field2 = ", C1 = ";
    String field3 = ", C2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    cmd.E.accept(this, offset);
    printAt(offset, field2);
    cmd.C1.accept(this, offset);
    printAt(offset, field3);
    cmd.C2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(WhileCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "WhileCommand {";
    String field1 = "E = ";
    String field2 = ", C = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 2;
    printAt(offset, field1);
    cmd.E.accept(this, offset);
    printAt(offset, field2);
    cmd.C.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(LetCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "LetCommand {";
    String field1 = "D = ";
    String field2 = ", C = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    cmd.D.accept(this, offset);
    printAt(offset, field2);
    cmd.C.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    int startPos = (int)arg;
    String header = "SequentialCommand {";
    String field1 = "C1 = ";
    String field2 = ", C2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    cmd.C1.accept(this, offset);
    printAt(offset, field2);
    cmd.C2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(FormalParamDeclaration param, Object arg) {
    int startPos = (int)arg;
    String header = "FormalParamDeclaration {";
    String field1 = "I = ";
    String field2 = ", T = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    param.I.accept(this, offset);
    printAt(offset, field2);
    param.T.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SequentialParamDeclaration param, Object arg) {
    int startPos = (int)arg;
    String header = "SequentialParamDeclaration {";
    String field1 = "P1 = ";
    String field2 = ", P2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    param.P1.accept(this, offset);
    printAt(offset, field2);
    param.P2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(CallArgument callarg, Object arg) {
    int startPos = (int)arg;
    String header = "CallArgument {";
    String field1 = "E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    callarg.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SequentialArgument callarg, Object arg) {
    int startPos = (int)arg;
    String header = "SequentialArgument {";
    String field1 = "A1 = ";
    String field2 = ", A2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    callarg.A1.accept(this, offset);
    printAt(offset, field2);
    callarg.A2.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(ConstDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "ConstDeclaration {";
    String field1 = "I = ";
    String field2 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
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
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    decl.I.accept(this, offset);
    printAt(offset, field2);
    decl.T.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(FunctionDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "FunctionDeclaration {";
    String field1 = "I = ";
    String field2 = ", P = ";
    String field3 = ", T = ";
    String field4 = ", E = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    decl.I.accept(this, offset);
    printAt(offset, field2);
    decl.P.accept(this, offset);
    printAt(offset, field3);
    decl.T.accept(this, offset);
    printAt(offset, field4);
    decl.E.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(UnaryOperatorDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "UnaryOperatorDeclaration {";
    String field1 = "OperandType = ";
    String field2 = ", ResultType = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    printAt(offset, decl.OperandType.toString());
    printAt(offset, field2);
    printAt(offset, decl.ResultType.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(BinaryOperatorDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "BinaryOperatorDeclaration {";
    String field1 = "Operand1Type = ";
    String field2 = "Operand2Type = ";
    String field3 = ", ResultType = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    printAt(offset, decl.Operand1Type.toString());
    printAt(offset, field2);
    printAt(offset, decl.Operand2Type.toString());
    printAt(offset, field3);
    printAt(offset, decl.ResultType.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SequentialDeclaration decl, Object arg) {
    int startPos = (int)arg;
    String header = "SequentialDeclaration {";
    String field1 = "D1 = ";
    String field2 = ", D2 = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
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
    String field2 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    expr.I.accept(this, offset);
    printAt(offset, field2);
    printWithNewlineAt(0, expr.type.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "VnameExpression {";
    String field1 = "V = ";
    String field2 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    expr.V.accept(this, offset);
    printAt(offset, field2);
    System.out.println("expr = " + expr);
    printWithNewlineAt(0, expr.type.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(CallExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "CallExpression {";
    String field1 = "V = ";
    String field2 = ", A = ";
    String field3 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    expr.I.accept(this, offset);
    printAt(offset, field2);
    expr.A.accept(this, offset);
    printAt(offset, field3);
    printWithNewlineAt(0, expr.type.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(UnaryExpression expr, Object arg) {
    int startPos = (int)arg;
    String header = "UnaryExpression {";
    String field1 = "O = ";
    String field2 = ", E = ";
    String field3 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    expr.O.accept(this, offset);
    printAt(offset, field2);
    expr.E.accept(this, offset);
    printAt(offset, field3);
    printWithNewlineAt(0, expr.type.toString());
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
    String field4 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    expr.E1.accept(this, offset);
    printAt(offset, field2);
    expr.O.accept(this, offset);
    printAt(offset, field3);
    expr.E2.accept(this, offset);
    printAt(offset, field4);
    printWithNewlineAt(0, expr.type.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SimpleTypeDenoter td, Object arg) {
    int startPos = (int)arg;
    String header = "SimpleTypeDenoter {";
    String field1 = "I = ";
    String field2 = ", type = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    td.I.accept(this, offset);
    printAt(offset, field2);
    printWithNewlineAt(0, td.type.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {
    int startPos = (int)arg;
    String header = "SimpleVname {";
    String field1 = "I = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    vname.I.accept(this, offset);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(Identifier id, Object arg) {
    int startPos = (int)arg;
    String header = "Identifier {";
    String field1 = "spelling = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    printWithNewlineAt(0, id.spelling);
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(Operator op, Object arg) {
    int startPos = (int)arg;
    String header = "Operator {";
    String field1 = "spelling = ";
    String field2 = ", decl = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    printWithNewlineAt(0, op.spelling);
    printAt(offset, field2);
    printWithNewlineAt(0, op.decl.toString());
    printWithNewlineAt(startPos, "}");

    return null;
  }

  @Override
  public Object visit(IntegerLiteral intlit, Object arg) {
    int startPos = (int)arg;
    String header = "IntegerLiteral {";
    String field1 = "spelling = ";

    printWithNewlineAt(0, header);
    int offset = startPos + header.length() / 3;
    printAt(offset, field1);
    printWithNewlineAt(0, intlit.spelling);
    printWithNewlineAt(startPos, "}");

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

