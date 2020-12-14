package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class SimpleVname extends Vname {
  public Identifier I;

  public SimpleVname(Identifier I) {
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SimpleVname)) {
      return false;
    }

    SimpleVname other = (SimpleVname)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SimpleVname { I = " + this.I + " }";
  }
}
