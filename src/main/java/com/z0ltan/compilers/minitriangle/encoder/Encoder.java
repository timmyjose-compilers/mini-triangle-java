package com.z0ltan.compilers.minitriangle.encoder;

import java.util.List;
import java.util.ArrayList;

import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.ParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FormalParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
import com.z0ltan.compilers.minitriangle.ast.Declaration;
import com.z0ltan.compilers.minitriangle.ast.ConstDeclaration;
import com.z0ltan.compilers.minitriangle.ast.VarDeclaration;
import com.z0ltan.compilers.minitriangle.ast.ProcedureDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.OperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.CharacterExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.UnaryExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;
import com.z0ltan.compilers.minitriangle.ast.CharacterLiteral;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Types;

import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class Encoder implements Visitor {
  @Override
  public Object visit(Program program, Object arg) {
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
    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    return null;
  }

  @Override
  public Object visit(FormalParamDeclaration param, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialParamDeclaration param, Object arg) {
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
    return null;
  }

  @Override
  public Object visit(VarDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(ProcedureDeclaration decl, Object arg) {
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
    return null;
  }

  @Override
  public Object visit(IntegerExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(CharacterExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallExpression expr, Object arg) {
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

  @Override
  public Object visit(CharacterLiteral charlit, Object arg) {
    return null;
  }
}
