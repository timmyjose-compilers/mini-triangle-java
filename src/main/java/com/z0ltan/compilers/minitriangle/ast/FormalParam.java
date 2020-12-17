package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class FormalParam extends Param {
  public Identifier I;
  public TypeDenoter T;

  public FormalParam(Identifier I, TypeDenoter T, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.T = T;
  }

  public FormalParam(Identifier I, TypeDenoter T) {
    super(null);
    this.I = I;
    this.T = T;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof FormalParam)) {
      return false;
    }

    FormalParam other = (FormalParam)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "FormalParam { I = " + this.I + ", T = " + this.T + " }";
  }
}
