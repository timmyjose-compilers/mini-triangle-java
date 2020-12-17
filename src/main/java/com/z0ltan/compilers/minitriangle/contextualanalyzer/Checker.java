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
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Types;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class Checker implements Visitor {
  private IdentificationTable idTable;

  public Checker() {
    this.idTable = new IdentificationTable();
  }

  private void populateStandardEnvironment() {
  }

  @Override
  public Object visit(Program program, Object arg) {
    program.C.accept(this, null);
    return null;
  }

  @Override
  public Object visit(AssignCommand cmd, Object arg) {
    Type vType = (Type) cmd.V.accept(this, null);
    Type eType = (Type) cmd.E.accept(this, null);

    if (!cmd.V.variable) {
      ErrorReporter.report("The left side of an assignment command must be a variable",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    if (!vType.equals(eType)) {
      ErrorReporter.report("Both sides of an assignment command must have the same type",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    return null;
  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    Type eType = (Type) cmd.E.accept(this, null);

    if (!eType.equals(Types.BOOL)) {
      ErrorReporter.report("the condition of an if command must be a boolean expression, not a " + eType,
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(LetCommand cmd, Object arg) {
    idTable.openScope();
    cmd.D.accept(this, null);
    cmd.C.accept(this, null);
    idTable.closeScope();

    return null;
  }

  @Override
  public Object visit(WhileCommand cmd, Object arg) {
    Type eType = (Type) cmd.E.accept(this, null);

    if (!eType.equals(Types.BOOL)) {
      ErrorReporter.report("the condiiton of a while command must be a boolean, not a " + eType,
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    cmd.C.accept(this, null);

    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

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
  public Object visit(IntegerExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(UnaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryExpression expr, Object arg) {
    return null;
  }

  @Override 
  public Object visit(CallExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(VarDeclaration decl, Object arg) {
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
  public Object visit(FunctionDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialDeclaration decl, Object arg) {
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

  public void check(Program program) {
    idTable.openScope();
    populateStandardEnvironment();
    visit(program, null);
    idTable.closeScope();
  }
}
