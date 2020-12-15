package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class TypeDenoter extends Ast {
  public TypeDenoter(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
