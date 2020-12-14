package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class Program extends Ast {
  public Command C;

  public Program(Command C) {
    this.C = C;
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
