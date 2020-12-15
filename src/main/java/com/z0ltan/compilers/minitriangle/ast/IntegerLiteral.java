package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class IntegerLiteral extends Terminal {
  public IntegerLiteral(String spelling, SourcePosition sourcePosition) {
    super(spelling, sourcePosition);
  }

  public IntegerLiteral(String spelling) {
    super(spelling, null);
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

