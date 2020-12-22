package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class AnyType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "Any";
  }
}



