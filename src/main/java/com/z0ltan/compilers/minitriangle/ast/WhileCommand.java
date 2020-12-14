package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class WhileCommand extends Command {
  public Expression E;
  public Command C;

  public WhileCommand(Expression E, Command C) {
    this.E = E;
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof WhileCommand)) {
      return false;
    }

    WhileCommand other = (WhileCommand)o;
    return this.E.equals(other.E) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "WhileCommand { E = " + this.E + ", C = " + this.C + " }";
  }
}
