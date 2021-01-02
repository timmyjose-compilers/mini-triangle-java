package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class FormalParamDeclaration extends ParamDeclaration {
  public Identifier I;
  public TypeDenoter T;

  public FormalParamDeclaration(Identifier I, TypeDenoter T, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.T = T;
  }

  public FormalParamDeclaration(Identifier I, TypeDenoter T) {
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
    if (o == null || !(o instanceof FormalParamDeclaration)) {
      return false;
    }

    FormalParamDeclaration other = (FormalParamDeclaration)o;
    return this.I.equals(other.I) && this.T.equals(other.T);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "FormalParamDeclaration { I = " + this.I + ", T = " + this.T + " }";
  }
}
