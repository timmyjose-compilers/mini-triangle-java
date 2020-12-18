package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class CallExpression extends Expression {
  public Identifier I;
  public Argument A;

  public CallExpression(Identifier I, Argument A, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.A = A;
  }

  public CallExpression(Identifier I, Argument A) {
    super(null);
    this.I = I;
    this.A = A;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CallExpression)) {
      return false;
    }

    CallExpression other = (CallExpression)o;
    return this.I.equals(other.I) && this.A.equals(other.A);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CallExpression { I = " + this.I + ", A = " + this.A + ", type = " + this.type + " }";
  }
}

