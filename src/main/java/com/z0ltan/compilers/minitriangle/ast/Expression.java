package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;

public abstract class Expression extends Ast {
  public Type type;

  public Expression(SourcePosition sourceSourcePosition) {
    super(sourceSourcePosition);
  }
}
