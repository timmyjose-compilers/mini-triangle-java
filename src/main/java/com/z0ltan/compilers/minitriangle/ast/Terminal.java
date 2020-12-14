package com.z0ltan.compilers.minitriangle.ast;

public abstract class Terminal extends Ast {
  public String spelling;

  public Terminal(String spelling) {
    this.spelling = spelling;
  }
}
