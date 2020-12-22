package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class ProcedureDeclaration extends Declaration {
  public Identifier I;
  public ParamDeclaration P;
  public Command C;

  public ProcedureDeclaration(Identifier I, ParamDeclaration P, Command C, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.P = P;
    this.C = C;
  }

  public ProcedureDeclaration(Identifier I, ParamDeclaration P, Command C) {
    super(null);
    this.I = I;
    this.P = P;
    this.C = C;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof ProcedureDeclaration)) {
      return false;
    }

    ProcedureDeclaration other = (ProcedureDeclaration)o;
    return this.I.equals(other.I) &&
      this.P.equals(other.P) &&
      this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "ProcedureDeclaration { I = " + this.I + ", P = " + this.P + ", C " + this.C + " }";
  }
}

