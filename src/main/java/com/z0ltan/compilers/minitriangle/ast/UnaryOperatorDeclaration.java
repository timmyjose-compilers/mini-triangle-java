package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof UnaryOperatorDeclaration)) {
      return false;
    }

    UnaryOperatorDeclaration other = (UnaryOperatorDeclaration)o;
    return this.OperandType.equals(other.OperandType) && this.ResultType.equals(other.ResultType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "UnaryOperatorDeclaration { OperandType = " + this.OperandType + ", ResultType = " + this.ResultType + " }";
  }
}
