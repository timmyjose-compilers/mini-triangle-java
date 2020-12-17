package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class WhileCommand extends Command {
  public Expression E;
  public Command C;

  public WhileCommand(Expression E, Command C, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.E = E;
    this.C = C;
  }

  public WhileCommand(Expression E, Command C) {
    super(null);
    this.E = E;
    this.C = C;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof WhileCommand)) {
      return false;
    }

    WhileCommand other = (WhileCommand)o;
    return this.E.equals(other.E) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "WhileCommand { E = " + this.E + ", C = " + this.C + " }";
  }
}
