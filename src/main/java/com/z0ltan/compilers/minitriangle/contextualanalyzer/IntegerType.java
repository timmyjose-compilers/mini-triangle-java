package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class IntegerType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof IntegerType)) {
      return false;
    }

    return true;
  }
}
