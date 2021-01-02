package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class SimpleVname extends Vname {
  public Identifier I;

  public SimpleVname(Identifier I, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
  }

  public SimpleVname(Identifier I) {
    super(null);
    this.I = I;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SimpleVname)) {
      return false;
    }

    SimpleVname other = (SimpleVname)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SimpleVname { I = " + this.I + ", type = " + this.type + ", variable = " + this.variable + " }";
  }
}
