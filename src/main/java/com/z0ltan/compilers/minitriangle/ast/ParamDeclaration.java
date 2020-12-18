package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class ParamDeclaration extends Declaration {
  public ParamDeclaration(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
