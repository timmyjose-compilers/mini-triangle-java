package com.z0ltan.compilers.minitriangle.error;

public class ErrorReporter {
  public static void report(String message, int line, int column) {
    String error = null;

    if (line == -1 || column == -1) {
      error = message;
    } else {
      error = "At line " + line + ", column " + column + ", error = " + message;
    }

    throw new MiniTriangleException(error);
  }

  public static void reportWithNoExit(String message, int line, int column) {
    String error = null;

    if (line == -1 || column == -1) {
      error = message;
    } else {
      error = "At line " + line + ", column " + column + ", error = " + message;
    }
  }
}
