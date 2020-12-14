package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class AssignCommand extends Command {
  public Vname V;
  public Expression E;

  public AssignCommand(Vname V, Expression E) {
    this.V = V;
    this.E = E;
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
