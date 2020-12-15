package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Param extends Ast {
  public Param(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
