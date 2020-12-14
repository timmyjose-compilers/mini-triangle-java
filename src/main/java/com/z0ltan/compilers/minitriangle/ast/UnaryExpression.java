package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class UnaryExpression extends Expression {
  public Operator O;
  public Expression E;

  public UnaryExpression(Operator O, Expression E) {
    this.O = O;
    this.E = E;
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