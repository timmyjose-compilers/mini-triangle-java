package com.z0ltan.compilers.minitriangle.ast;

import java.util.Objects;

public class LetCommand extends Command {
  public Declaration D;
  public Command C;

  public LetCommand(Declaration D, Command C) {
    this.D = D;
    this.C = C;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof LetCommand)) {
      return false;
    }

    LetCommand other = (LetCommand)o;
    return this.D.equals(other.D) && this.C.equals(other.C);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this);
  }

  @Override
  public String toString() {
    return "LetCommand { D = " + this.D + ", C = " + this.C + " }";
  }

}
