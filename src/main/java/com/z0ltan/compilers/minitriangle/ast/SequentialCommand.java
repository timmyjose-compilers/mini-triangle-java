package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;

public class SequentialCommand extends Command {
  public Command C1;
  public Command C2;

  public SequentialCommand(Command C1, Command C2, SourcePosition sourcePosition) {
    super(sourcePosition);
    this.C1 = C1;
    this.C2 = C2;
  }

  public SequentialCommand(Command C1, Command C2) {
    super(null);
    this.C1 = C1;
    this.C2 = C2;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof SequentialCommand)) {
      return false;
    }

    SequentialCommand other = (SequentialCommand)o;
    return this.C1.equals(other.C1) && this.C2.equals(other.C2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "SequentialCommand { C1 = " + this.C1 + ", C2 = " + this.C2 + " }";
  }
}
