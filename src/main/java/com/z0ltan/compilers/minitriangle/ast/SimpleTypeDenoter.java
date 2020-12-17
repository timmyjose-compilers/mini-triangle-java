package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class SimpleTypeDenoter extends TypeDenoter {
  public Identifier I;

  public SimpleTypeDenoter(Identifier I, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
  }

  public SimpleTypeDenoter(Identifier I) {
    super(null);
    this.I = I;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SimpleTypeDenoter)) {
      return false;
    }

    SimpleTypeDenoter other = (SimpleTypeDenoter)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SimpleTypeDenoter { I = " + this.I + " }";
  }
}
