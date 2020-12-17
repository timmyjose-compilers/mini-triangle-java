package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class VarDeclaration extends Declaration {
  public Identifier I;
  public TypeDenoter T;

  public VarDeclaration(Identifier I, TypeDenoter T, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.T = T;
  }

  public VarDeclaration(Identifier I, TypeDenoter T) {
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
    if (o == null || !(o instanceof VarDeclaration)) {
      return false;
    }

    VarDeclaration other = (VarDeclaration)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "VarDeclaration { I = " + this.I + ", T = " + this.T + " }";
  }
}
