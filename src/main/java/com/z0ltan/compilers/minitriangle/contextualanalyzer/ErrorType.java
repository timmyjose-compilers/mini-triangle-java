package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class ErrorType extends Type {
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof ErrorType)) {
      return false;
    }

    return true;
  }
}
