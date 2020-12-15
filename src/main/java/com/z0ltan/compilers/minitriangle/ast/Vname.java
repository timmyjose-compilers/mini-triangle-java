package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Vname extends Ast {
  public Vname(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
