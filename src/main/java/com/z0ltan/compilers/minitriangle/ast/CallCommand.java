package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Visitor;

public class CallCommand extends Command {
  public Identifier I;
  public Argument A;

  public CallCommand(Identifier I, Argument A, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.I = I;
    this.A = A;
  }

  public CallCommand(Identifier I, Argument A) {
    super(null);
    this.I = I;
    this.A = A;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CallCommand)) {
      return false;
    }

    CallCommand other = (CallCommand)o;
    return this.I.equals(other.I) && this.A.equals(other.A);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "CallCommand { I = " + this.I + ", A = " + this.A + " }";
  }
}
