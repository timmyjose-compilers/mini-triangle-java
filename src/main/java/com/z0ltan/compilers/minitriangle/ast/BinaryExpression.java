package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class BinaryExpression extends Expression {
  public Expression E1;
  public Operator O;
  public Expression E2;

  public BinaryExpression(Expression E1, Operator O, Expression E2) {
    this.E1 = E1;
    this.O = O;
    this.E2 = E2;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof BinaryExpression)) {
      return false;
    }

    BinaryExpression other = (BinaryExpression)o;
    return this.E1.equals(other.E1) && this.O.equals(other.O) && this.E2.equals(other.E2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "BinaryExpression { E1 = " + this.E1 + ", O = " + this.O + ", E2 = " + this.E2 + " }";
  }
}
