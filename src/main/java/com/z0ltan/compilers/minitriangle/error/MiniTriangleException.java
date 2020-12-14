package com.z0ltan.compilers.minitriangle.error;

public class MiniTriangleException extends RuntimeException {
  public MiniTriangleException(String message) {
    super(message);
  }

  public MiniTriangleException(Exception ex) {
    super(ex);
  }
}
