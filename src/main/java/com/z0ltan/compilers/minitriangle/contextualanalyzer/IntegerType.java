package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class IntegerType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    if (o instanceof AnyType || o instanceof IntegerType) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return "Integer";
  }
}
