package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class CallArgument extends Argument {
  public Expression E;

  public CallArgument(Expression E, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.E = E;
  }

  public CallArgument(Expression E) {
    super(null);
    this.E = E;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CallArgument)) {
      return false;
    }

    CallArgument other = (CallArgument)o;
    return this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CallArgument { E = " + this.E + " }";
  }
}

