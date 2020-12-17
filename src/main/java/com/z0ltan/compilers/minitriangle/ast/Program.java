package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class Program extends Ast {
  public Command C;

  public Program(Command C, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.C = C;
  }

  public Program(Command C) {
    super(null);
    this.C = C;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Program)) {
      return false;
  }

    Program other = (Program)o;
    return this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "Program { C = " + this.C + " }";
  }
}
