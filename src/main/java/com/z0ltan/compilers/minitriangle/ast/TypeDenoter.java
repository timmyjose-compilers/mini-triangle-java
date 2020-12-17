package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class TypeDenoter extends Ast {
  public Type type;

  public TypeDenoter(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
