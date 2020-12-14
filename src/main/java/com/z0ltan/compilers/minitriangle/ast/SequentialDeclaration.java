package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class SequentialDeclaration extends Declaration {
  public Declaration D1;
  public Declaration D2;

  public SequentialDeclaration(Declaration D1, Declaration D2) {
    this.D1 = D1;
    this.D2 = D2;
  }
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SequentialDeclaration)) {
      return false;
    }

    SequentialDeclaration other = (SequentialDeclaration)o;
    return this.D1.equals(other.D1) && this.D2.equals(other.D2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialDeclaration { D1 = " + this.D1 + ", D2 = " + this.D2 + " }";
  }
}
