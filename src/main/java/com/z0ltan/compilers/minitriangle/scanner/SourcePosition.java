package com.z0ltan.compilers.minitriangle.scanner;

public class SourcePosition {
  public static class Position {
    public int line;
    public int column;

    public Position(int line, int column) {
      this.line = line;
      this.column = column;
    }

    @Override
    public String toString() {
      return "Position { line = " + this.line + ", column = " + this.column + " }";
    }
  }
  public Position start;
  public Position finish;

  public SourcePosition() {
    this.start = null;
    this.finish = null;
  }

  public SourcePosition(Position start, Position finish) {
    this.start = start;
    this.finish = finish;
  }

  @Override
  public String toString() {
    return "SourcePosition { start = " + this.start + ", finish = " + this.finish + " }";
  }
}

