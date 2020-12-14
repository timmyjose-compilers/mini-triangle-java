package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class CallCommand extends Command {
  public Identifier I;
  public Expression E;

  public CallCommand(Identifier I, Expression E) {
    this.I = I;
    this.E = E;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CallCommand)) {
      return false;
    }

    CallCommand other = (CallCommand)o;
    return this.I.equals(other.I) && this.E.equals(other.E);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CallCommand { I = " + this.I + ", E = " + this.E + " }";
  }
}
