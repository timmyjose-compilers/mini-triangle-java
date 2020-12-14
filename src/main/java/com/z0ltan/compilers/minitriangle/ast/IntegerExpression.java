package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class IntegerExpression extends Expression {
  public IntegerLiteral I;

  public IntegerExpression(IntegerLiteral I) {
    this.I = I;
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
