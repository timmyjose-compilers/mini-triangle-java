package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;

public class UnaryOperatorDeclaration extends OperatorDeclaration {
  public Type OperandType;
  public Type ResultType;

  public UnaryOperatorDeclaration(Type operandType, Type ResultType, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.OperandType = OperandType;
    this.ResultType = ResultType;
  }

  public UnaryOperatorDeclaration(Type OperandType, Type ResultType) {
    super(null);
    this.OperandType = OperandType;
    this.ResultType = ResultType;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }
}
