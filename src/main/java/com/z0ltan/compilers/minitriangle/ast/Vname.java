package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;

public abstract class Vname extends Ast {
  public Type type;
  public boolean variable;

  public Vname(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
