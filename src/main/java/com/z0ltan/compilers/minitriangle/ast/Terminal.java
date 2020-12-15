package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public abstract class Terminal extends Ast {
  public String spelling;

  public Terminal(String spelling, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.spelling = spelling;
  }
}
