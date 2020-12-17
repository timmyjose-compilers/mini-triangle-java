package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class IntegerExpression extends Expression {
  public IntegerLiteral I;

  public IntegerExpression(IntegerLiteral I, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
  }

  public IntegerExpression(IntegerLiteral I) {
    super(null);
    this.I = I;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof IntegerExpression)) {
      return false;
    }

    IntegerExpression other = (IntegerExpression)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "IntegerExpression { I = " + this.I + " }";
  }
}
