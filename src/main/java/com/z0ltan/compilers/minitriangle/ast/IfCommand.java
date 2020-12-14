package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class IfCommand extends Command {
  public Expression E;
  public Command C1;
  public Command C2;

  public IfCommand(Expression E, Command C1, Command C2) {
    this.E = E;
    this.C1 = C1;
    this.C2 = C2;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof IfCommand)) {
      return false;
    }

    IfCommand other = (IfCommand)o;
    return this.E.equals(other.E) && this.C1.equals(other.C1) && this.C2.equals(other.C2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "IfCommand { E = " + this.E + ", C1 = " + this.C1 + ", C2 = " + this.C2 + " }";
  }
}
