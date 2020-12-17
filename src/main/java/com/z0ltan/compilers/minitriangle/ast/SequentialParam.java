package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class SequentialParam extends Param {
  public Param P1;
  public Param P2;

  public SequentialParam(Param P1, Param P2, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.P1 = P1;
    this.P2 = P2;
  }

  public SequentialParam(Param P1, Param P2) {
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
    if (o == null || !(o instanceof SequentialParam)) {
      return false;
    }

    SequentialParam other = (SequentialParam)o;
    return this.P1.equals(other.P1) && this.P2.equals(other.P2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialParam { P1 = " + this.P1 + ", P2 = " + this.P2 + " }";
  }
}
