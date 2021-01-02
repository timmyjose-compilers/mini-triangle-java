package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class VnameExpression extends Expression {
  public Vname V;
  
  public VnameExpression(Vname V, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.V = V;
  }
 
  public VnameExpression(Vname V) {
    super(null);
    this.V = V;
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    return visitor.visit(this, arg);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof VnameExpression)) {
      return false;
    }

    VnameExpression other = (VnameExpression)o;
    return this.V.equals(other.V);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "VnameExpression { V = " + this.V + ", type = " + this.type + " }";
  }
}
