package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class ConstDeclaration extends Declaration {
  public Identifier I;
  public Expression E;

  public ConstDeclaration(Identifier I, Expression E, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.E = E;
  }

  public ConstDeclaration(Identifier I, Expression E) {
    super(null);
    this.I = I;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof ConstDeclaration)) {
      return false;
    }

    ConstDeclaration other = (ConstDeclaration)o;
    return this.I.equals(other.I) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "ConstDeclaration { I = " + this.I + ", E = " + this.E + " }";
  }
}
