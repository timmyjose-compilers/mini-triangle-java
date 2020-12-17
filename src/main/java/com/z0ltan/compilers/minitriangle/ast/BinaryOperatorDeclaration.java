package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof BinaryOperatorDeclaration)) {
      return false;
    }

    BinaryOperatorDeclaration other = (BinaryOperatorDeclaration)o;
    return this.Operand1Type.equals(other.Operand1Type) &&
      this.Operand2Type.equals(other.Operand2Type) &&
      this.ResultType.equals(other.ResultType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "BinaryOperatorDeclaration { Operand1Type = " + this.Operand1Type + 
      ", Operand2Type = " + this.Operand2Type + 
      ", ResultType = " + this.ResultType + " }";
  }
}
