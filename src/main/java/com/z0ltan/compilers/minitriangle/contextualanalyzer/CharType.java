package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class CharType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof CharType)) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "Char";
  }
}

