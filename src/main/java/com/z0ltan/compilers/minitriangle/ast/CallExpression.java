package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class CallExpression extends Expression {
  public Vname V;
  public Argument A;

  public CallExpression(Vname V, Argument A, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.V = V;
    this.A = A;
  }

  public CallExpression(Vname V, Argument A) {
    super(null);
    this.V = V;
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
    return this.V.equals(other.V) && this.A.equals(other.A);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CallExpression { V = " + this.V + ", A = " + this.A + " }";
  }
}

