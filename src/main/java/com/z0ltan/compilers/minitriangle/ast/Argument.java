package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Argument extends Ast {
  public Argument(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}

