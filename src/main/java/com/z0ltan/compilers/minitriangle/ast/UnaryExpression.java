package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class UnaryExpression extends Expression {
  public Operator O;
  public Expression E;

  public UnaryExpression(Operator O, Expression E, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.O = O;
    this.E = E;
  }

  public UnaryExpression(Operator O, Expression E) {
    super(null);
    this.O = O;
    this.E = E;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof UnaryExpression)) {
      return false;
    }

    UnaryExpression other = (UnaryExpression)o;
    return this.O.equals(other.O) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "UnaryExpression { O = " + this.O + ", E = " + this.E + " }";
  }
}
