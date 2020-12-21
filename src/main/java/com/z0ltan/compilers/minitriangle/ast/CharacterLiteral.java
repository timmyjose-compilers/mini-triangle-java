package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class CharacterLiteral extends Terminal {
  public CharacterLiteral(String spelling, SourcePosition sourcePosition) {
    super(spelling, sourcePosition);
  }

  public CharacterLiteral(String spelling) {
    super(spelling, null);
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }
 
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CharacterLiteral)) {
      return false;
    }

    CharacterLiteral other = (CharacterLiteral)o;
    return this.spelling.equals(other.spelling);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CharacterLiteral { spelling = " + this.spelling + " }";
  }
}
