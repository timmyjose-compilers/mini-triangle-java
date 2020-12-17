package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class OperatorDeclaration extends Declaration {
  public OperatorDeclaration(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
