package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Expression extends Ast {
  public Expression(SourcePosition sourceSourcePosition) {
    super(sourceSourcePosition);
  }
}
