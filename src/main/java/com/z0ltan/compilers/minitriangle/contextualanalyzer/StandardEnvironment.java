package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.OperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;

public class StandardEnvironment {
  private static final OperatorDeclaration plusOperatorDeclaration = 
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.INT);

  private static final OperatorDeclaration minusOperationDeclaration = 
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.INT);

  private static final BinaryOperatorDeclaration starOperationDeclaration = 
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.INT);

  private static final BinaryOperatorDeclaration slashOperatorDeclaration =
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.INT);

  private static final BinaryOperatorDeclaration lessThanOperatorDeclaration = 
    new BinaryOperatorDeclaration(Types.BOOL, Types.BOOL, Types.BOOL);

  private static final BinaryOperatorDeclaration greaterThanOperatorDeclaration = 
    new BinaryOperatorDeclaration(Types.BOOL, Types.BOOL, Types.BOOL);

  public static void load(IdentificationTable idTable) {
    idTable.enter("+", plusOperatorDeclaration);
    idTable.enter("-", minusOperationDeclaration);
    idTable.enter("*", starOperationDeclaration);
    idTable.enter("/", slashOperatorDeclaration);
    idTable.enter("<", lessThanOperatorDeclaration);
    idTable.enter(">", greaterThanOperatorDeclaration);
  }
}
