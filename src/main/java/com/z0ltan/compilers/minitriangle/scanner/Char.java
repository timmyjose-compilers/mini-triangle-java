package com.z0ltan.compilers.minitriangle.scanner;

public class Char {
  public char character;
  public Position position;

  private static final char NUL = '\u0000';

  public Char(char character, int line, int column) {
    this.character = character;
    this.position = new Position(line, column);
  }

  public static Char nullCharacter(int line, int column) {
    return new Char(NUL, line, column);
  }

  public static Char nullCharacter() {
    return nullCharacter(-1, -1);
  }

  @Override
  public String toString() {
    return "Char { character = \'" + this.character + "\', position = " + this.position + " }";
  }
}