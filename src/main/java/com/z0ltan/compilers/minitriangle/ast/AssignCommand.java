package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class AssignCommand extends Command {
  public Vname V;
  public Expression E;

  public AssignCommand(Vname V, Expression E, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.V = V;
    this.E = E;
  }

  public AssignCommand(Vname V, Expression E) {
    super(null);
    this.V = V;
    this.E = E;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof AssignCommand)) {
      return false;
    }

    AssignCommand other = (AssignCommand)o;
    return this.V.equals(other.V) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "AssignCommand { V = " + this.V + ", E = " + this.E + " }";
  }
}
