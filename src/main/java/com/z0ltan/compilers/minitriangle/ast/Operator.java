package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class Operator extends Terminal {
  public Operator(String spelling, SourcePosition sourcePosition) {
    super(spelling, sourcePosition);
  }

  public Operator(String spelling) {
    super(spelling, null);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Operator)) {
      return false;
    }

    Operator other = (Operator)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "Operator { spelling = " + this.spelling + " }";
  }
}
