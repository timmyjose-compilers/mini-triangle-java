package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class CharacterExpression extends Expression {
  public CharacterLiteral I;

  public CharacterExpression(CharacterLiteral I, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
  }

  public CharacterExpression(CharacterLiteral I) {
    super(null);
    this.I = I;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CharacterExpression)) {
      return false;
    }

    CharacterExpression other = (CharacterExpression)o;
    return this.I.equals(other.I);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CharacterExpression { I = " + this.I + ", type = " + this.type + " }";
  }
}

