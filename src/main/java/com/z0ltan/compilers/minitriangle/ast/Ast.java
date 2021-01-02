package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public abstract class Ast {
  public SourcePosition sourcePosition;

  public Ast(SourcePosition sourcePosition) {
    this.sourcePosition = sourcePosition;
  }

  public abstract Object accept(Visitor visitor, Object arg);
}
