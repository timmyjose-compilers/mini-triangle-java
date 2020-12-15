package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class Identifier extends Terminal {
  public Identifier(String spelling, SourcePosition sourcePosition) {
    super(spelling, sourcePosition);
  }

  public Identifier(String spelling) {
    super(spelling, null);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Identifier)) {
      return false;
    }

    Identifier other = (Identifier)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "Identifier { spelling = " + this.spelling + " }";
  }
}

