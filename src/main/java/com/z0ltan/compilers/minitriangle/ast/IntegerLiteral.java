package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class IntegerLiteral extends Terminal {
  public IntegerLiteral(String spelling) {
    super(spelling);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof IntegerLiteral)) {
      return false;
    }

    IntegerLiteral other = (IntegerLiteral)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "IntegerLiteral { spelling = " + this.spelling + " }";
  }
}

