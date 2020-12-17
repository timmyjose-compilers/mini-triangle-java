package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;

public class BinaryOperatorDeclaration extends OperatorDeclaration {
  public Type Operand1Type;
  public Type Operand2Type;
  public Type ResultType;

  public BinaryOperatorDeclaration(Type Operand1Type, Type Operand2Type, Type ResultType, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.Operand1Type = Operand1Type;
    this.Operand2Type = Operand2Type;
    this.ResultType = ResultType;
  }

  public BinaryOperatorDeclaration(Type Operand1Type, Type Operand2Type, Type ResultType) {
    super(null);
    this.Operand1Type = Operand1Type;
    this.Operand2Type = Operand2Type;
    this.ResultType = ResultType;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }
}
