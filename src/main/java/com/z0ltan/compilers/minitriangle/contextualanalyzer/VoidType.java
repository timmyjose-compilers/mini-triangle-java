package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class VoidType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof VoidType)) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "Void";
  }
}


