package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Declaration extends Ast {
  public Declaration(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
