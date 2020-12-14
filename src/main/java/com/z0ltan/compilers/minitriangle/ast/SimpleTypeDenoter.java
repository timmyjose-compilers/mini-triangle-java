package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class SimpleTypeDenoter extends TypeDenoter {
  public Identifier I;

  public SimpleTypeDenoter(Identifier I) {
    this.I = I;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SimpleTypeDenoter)) {
      return false;
    }

    SimpleTypeDenoter other = (SimpleTypeDenoter)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SimpleTypeDenoter { I = " + this.I + " }";
  }
}