package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Command extends Ast {
  public Command(SourcePosition sourcePosition) {
    super(sourcePosition);
  }
}
