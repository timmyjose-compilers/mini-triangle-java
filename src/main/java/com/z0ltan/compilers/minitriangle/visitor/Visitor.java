package com.z0ltan.compilers.minitriangle.visitor;

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
import com.z0ltan.compilers.minitriangle.ast.ProcedureDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
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

public interface Visitor {
  Object visit(Program program, Object arg);
  Object visit(AssignCommand cmd, Object arg);
  Object visit(CallCommand cmd, Object arg);
  Object visit(IfCommand cmd, Object arg);
  Object visit(WhileCommand cmd, Object arg);
  Object visit(LetCommand cmd, Object arg);
  Object visit(SequentialCommand cmd, Object arg);
  Object visit(FormalParamDeclaration param, Object arg);
  Object visit(SequentialParamDeclaration param, Object arg);
  Object visit(CallArgument callarg, Object arg);
  Object visit(SequentialArgument callarg, Object arg);
  Object visit(ConstDeclaration decl, Object arg);
  Object visit(VarDeclaration decl, Object arg);
  Object visit(ProcedureDeclaration decl, Object arg);
  Object visit(FunctionDeclaration decl, Object arg);
  Object visit(UnaryOperatorDeclaration decl, Object arg);
  Object visit(BinaryOperatorDeclaration decl, Object arg);
  Object visit(SequentialDeclaration decl, Object arg);
  Object visit(IntegerExpression expr, Object arg);
  Object visit(CharacterExpression expr, Object arg);
  Object visit(VnameExpression expr, Object arg);
  Object visit(CallExpression expr, Object arg);
  Object visit(UnaryExpression expr, Object arg);
  Object visit(BinaryExpression expr, Object arg);
  Object visit(SimpleTypeDenoter td, Object arg);
  Object visit(SimpleVname vname, Object arg);
  Object visit(Identifier id, Object arg);
  Object visit(Operator op, Object arg);
  Object visit(IntegerLiteral intlit, Object arg);
  Object visit(CharacterLiteral charlit, Object arg);
}
