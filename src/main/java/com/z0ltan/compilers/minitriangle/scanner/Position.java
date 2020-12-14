package com.z0ltan.compilers.minitriangle.scanner;

public class Position {
  public int line;
  public int column;

  public Position(int line, int column) {
    this.line = line;
    this.column = column;
  }

  public static Position illegalPosition() {
    return new Position(-1, -1);
  }

  @Override
  public String toString() {
    return "Position { line = " + this.line + ", column = " + this.column + " }";
  }
}
