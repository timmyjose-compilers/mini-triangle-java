package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class FunctionDeclaration extends Declaration {
  public Identifier I;
  public Param P;
  public TypeDenoter T;
  public Expression E;

  public FunctionDeclaration(Identifier I, Param P, TypeDenoter T, Expression E, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.P = P;
    this.T = T;
    this.E = E;
  }

  public FunctionDeclaration(Identifier I, Param P, TypeDenoter T, Expression E) {
    super(null);
    this.I = I;
    this.P = P;
    this.T = T;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof FunctionDeclaration)) {
      return false;
    }

    FunctionDeclaration other = (FunctionDeclaration)o;
    return this.I.equals(other.I) &&
      this.P.equals(other.P) &&
      this.T.equals(other.T) &&
      this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "FunctionDeclaration { I = " + this.I + ", P = " + this.P + ", T = " + this.T + ", E " + this.E + " }";
  }
}
