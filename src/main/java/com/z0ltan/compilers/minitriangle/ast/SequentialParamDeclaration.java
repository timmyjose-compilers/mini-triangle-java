package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class SequentialParamDeclaration extends ParamDeclaration {
  public ParamDeclaration P1;
  public ParamDeclaration P2;

  public SequentialParamDeclaration(ParamDeclaration P1, ParamDeclaration P2, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.P1 = P1;
    this.P2 = P2;
  }

  public SequentialParamDeclaration(ParamDeclaration P1, ParamDeclaration P2) {
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
    if (o == null || !(o instanceof SequentialParamDeclaration)) {
      return false;
    }

    SequentialParamDeclaration other = (SequentialParamDeclaration)o;
    return this.P1.equals(other.P1) && this.P2.equals(other.P2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialParamDeclaration { P1 = " + this.P1 + ", P2 = " + this.P2 + " }";
  }
}
