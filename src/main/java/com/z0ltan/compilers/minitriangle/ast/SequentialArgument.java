package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class SequentialArgument extends Argument {
  public Argument A1;
  public Argument A2;

  public SequentialArgument(Argument A1, Argument A2, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.A1 = A1;
    this.A2 = A2;
  }

  public SequentialArgument(Argument A1, Argument A2) {
    super(null);
    this.A1 = A1;
    this.A2 = A2;
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
    return this.A1.equals(other.A1) && this.A2.equals(other.A2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialArgument { A1 = " + this.A1 + ", A2 = " + this.A2 + " }";
  }
}

