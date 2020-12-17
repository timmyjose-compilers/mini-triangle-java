package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class SequentialArgument extends Argument {
  public Argument P1;
  public Argument P2;

  public SequentialArgument(Argument P1, Argument P2, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.P1 = P1;
    this.P2 = P2;
  }

  public SequentialArgument(Argument P1, Argument P2) {
    super(null);
    this.P1 = P1;
    this.P2 = P2;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SequentialArgument)) {
      return false;
    }

    SequentialArgument other = (SequentialArgument)o;
    return this.P1.equals(other.P1) && this.P2.equals(other.P2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialArgument { P1 = " + this.P1 + ", P2 = " + this.P2 + " }";
  }
}

