package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class BooleanType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof BooleanType)) {
      return false;
    }

    return true;
  }
}
