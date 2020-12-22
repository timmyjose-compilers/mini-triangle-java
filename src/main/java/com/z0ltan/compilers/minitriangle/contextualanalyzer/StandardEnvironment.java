package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.OperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.ProcedureDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FormalParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.EmptyCommand;
import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;

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

  private static final BinaryOperatorDeclaration notEqualToDeclaration = 
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.BOOL);

  private static final BinaryOperatorDeclaration equalToDeclaration = 
    new BinaryOperatorDeclaration(Types.INT, Types.INT, Types.BOOL);

  private static final ProcedureDeclaration getintDeclaration = declareGetint();

  private static final ProcedureDeclaration putintDeclaration = declarePutint();

  public static void load(IdentificationTable idTable) {
    idTable.enter("+", plusOperatorDeclaration);
    idTable.enter("-", minusOperationDeclaration);
    idTable.enter("*", starOperationDeclaration);
    idTable.enter("/", slashOperatorDeclaration);
    idTable.enter("<", lessThanOperatorDeclaration);
    idTable.enter(">", greaterThanOperatorDeclaration);
    idTable.enter("\\=", notEqualToDeclaration);
    idTable.enter("=", equalToDeclaration);
    idTable.enter("getint", getintDeclaration);
    idTable.enter("putint", putintDeclaration);
  }

  private static ProcedureDeclaration declareGetint() {
    SimpleTypeDenoter td = new SimpleTypeDenoter(new Identifier("Any"));
    td.type = Types.ANY;

    return new ProcedureDeclaration(new Identifier("getint"),
        new FormalParamDeclaration(new Identifier("input"), td),
        new EmptyCommand(new SourcePosition()));
  }

  private static ProcedureDeclaration declarePutint() {
    SimpleTypeDenoter td = new SimpleTypeDenoter(new Identifier("Any"));
    td.type = Types.ANY;

    return new ProcedureDeclaration(new Identifier("putint"), 
        new FormalParamDeclaration(new Identifier("string"), td), 
        new EmptyCommand(new SourcePosition()));
  }
}
