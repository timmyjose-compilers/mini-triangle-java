package com.z0ltan.compilers.minitriangle.contextualanalyzer;

public class Types {
  public static final Type ANY = new AnyType();
  public static final Type BOOL = new BooleanType();
  public static final Type INT = new IntegerType();
  public static final Type CHAR = new CharType();
  public static final Type VOID = new VoidType();
  public static final Type ERROR = new ErrorType();
}
