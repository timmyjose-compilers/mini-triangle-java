package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Ast {
  public SourcePosition sourcePosition;

  public Ast(SourcePosition sourcePosition) {
    this.sourcePosition = sourcePosition;
  }
}
