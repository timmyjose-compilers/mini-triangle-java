package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.FormalParam;
import com.z0ltan.compilers.minitriangle.ast.SequentialParam;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
import com.z0ltan.compilers.minitriangle.ast.Declaration;
import com.z0ltan.compilers.minitriangle.ast.ConstDeclaration;
import com.z0ltan.compilers.minitriangle.ast.VarDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.OperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.UnaryExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Type;
import com.z0ltan.compilers.minitriangle.contextualanalyzer.Types;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class Checker implements Visitor {
  private IdentificationTable idTable;

  public Checker() {
    this.idTable = new IdentificationTable();
  }

  @Override
  public Object visit(Program program, Object arg) {
    program.C.accept(this, null);
    return null;
  }

  @Override
  public Object visit(AssignCommand cmd, Object arg) {
    Type vType = (Type) cmd.V.accept(this, null);
    Type eType = (Type) cmd.E.accept(this, null);

    if (!cmd.V.variable) {
      ErrorReporter.report("The left side of an assignment command must be a variable",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    if (!vType.equals(eType)) {
      ErrorReporter.report("Both sides of an assignment command must have the same type",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    return null;
  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    cmd.I.accept(this, null);
    cmd.E.accept(this, null);

    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    Type eType = (Type) cmd.E.accept(this, null);

    if (!eType.equals(Types.BOOL)) {
      ErrorReporter.report("the condition of an if command must be a boolean expression, not a " + eType,
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(LetCommand cmd, Object arg) {
    idTable.openScope();
    cmd.D.accept(this, null);
    cmd.C.accept(this, null);
    idTable.closeScope();

    return null;
  }

  @Override
  public Object visit(WhileCommand cmd, Object arg) {
    Type eType = (Type) cmd.E.accept(this, null);

    if (!eType.equals(Types.BOOL)) {
      ErrorReporter.report("the condiiton of a while command must be a boolean, not a " + eType,
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    cmd.C.accept(this, null);

    return null;
  }

  @Override
  public Object visit(SequentialCommand cmd, Object arg) {
    cmd.C1.accept(this, null);
    cmd.C2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(FormalParam param, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialParam param, Object arg) {
    return null;
  }

  @Override
  public Object visit(CallArgument callarg, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialArgument callarg, Object arg) {
    return null;
  }

  @Override
  public Object visit(IntegerExpression expr, Object arg) {
    expr.type = Types.INT;
    return expr.type;
  }

  @Override
  public Object visit(VnameExpression expr, Object arg) {
    Type vType = (Type) expr.V.accept(this, null);
    expr.type = vType;

    return expr.type;
  }

  @Override
  public Object visit(UnaryExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryExpression expr, Object arg) {
    Type e1Type = (Type) expr.E1.accept(this, null);
    Type e2Type = (Type) expr.E2.accept(this, null);

    OperatorDeclaration opdecl = (OperatorDeclaration)expr.O.accept(this, null);
    if (opdecl == null) {
      ErrorReporter.report("no such operator - " + "\"" + expr.O.spelling +  "\"",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
    } else if (opdecl instanceof BinaryOperatorDeclaration) {
      BinaryOperatorDeclaration binopdecl = (BinaryOperatorDeclaration)opdecl;
      if (!e1Type.equals(binopdecl.Operand1Type)) {
        ErrorReporter.report("the left hand side of the binary expression has the wrong type - " + "\"" + e1Type + "\"",
            expr.sourcePosition.start.line,
            expr.sourcePosition.start.column);
      }

      if (!e2Type.equals(binopdecl.Operand2Type)) {
        ErrorReporter.report("the right hand side of the binary expression has the wrong type - " + "\"" + e2Type + "\"",
            expr.sourcePosition.start.line,
            expr.sourcePosition.start.column);
      }

      expr.type = binopdecl.ResultType;
    } else {
      ErrorReporter.report("\"" + expr.O.spelling + "\"" + " is not a binary operator",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
    }

    return expr.type;
  }

  @Override 
  public Object visit(CallExpression expr, Object arg) {
    return null;
  }

  @Override
  public Object visit(ConstDeclaration decl, Object arg) {
    decl.E.accept(this, null);
    idTable.enter(decl.I.spelling, decl);

    return null;
  }

  @Override
  public Object visit(VarDeclaration decl, Object arg) {
    decl.T.accept(this, null);
    idTable.enter(decl.I.spelling, decl);

    return null;
  }

  @Override
  public Object visit(UnaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(BinaryOperatorDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(FunctionDeclaration decl, Object arg) {
    return null;
  }

  @Override
  public Object visit(SequentialDeclaration decl, Object arg) {
    decl.D1.accept(this, null);
    decl.D2.accept(this, null);

    return null;
  }

  @Override
  public Object visit(SimpleTypeDenoter td, Object arg) {
    switch (td.I.spelling) {
      case "Boolean":
        td.type = Types.BOOL;
        break;

      case "Integer":
        td.type = Types.INT;
        break;

      default:
        ErrorReporter.report("unknown simple type \"" + td.I.spelling + "\"",
            td.sourcePosition.start.line,
            td.sourcePosition.start.column);
    }

    return td.type;
  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {
    Declaration decl = (Declaration) vname.I.accept(this, null);
    
    if (decl == null) {
      ErrorReporter.report("\"" + vname.I.spelling + "\" is undeclared",
          vname.sourcePosition.start.line,
          vname.sourcePosition.start.column);
    }

    if (decl instanceof ConstDeclaration) {
      vname.type = ((ConstDeclaration) decl).E.type;
      vname.variable = false;
    } else if (decl instanceof VarDeclaration) {
      vname.type = ((VarDeclaration) decl).T.type;
      vname.variable = true;
    } else if (decl instanceof FunctionDeclaration) {
      ErrorReporter.report("\"" + vname.I.spelling + "\"" + " is declared as a function - you cannot assign to a function",
          vname.sourcePosition.start.line,
          vname.sourcePosition.start.column);
    } else if (decl instanceof OperatorDeclaration) {
      ErrorReporter.report("\"" + vname.I.spelling + "\"" + " is declared as an operator - you cannot assign to an operator",
          vname.sourcePosition.start.line,
          vname.sourcePosition.start.column);
    }

    return vname.type;
  }

  @Override
  public Object visit(Identifier id, Object arg) {
    id.decl = idTable.retrieve(id.spelling);
    return id.decl;
  }

  @Override
  public Object visit(Operator op, Object arg) {
    Declaration decl = idTable.retrieve(op.spelling);
    if (!(decl instanceof OperatorDeclaration)) {
      op.decl = null;
    } else {
      op.decl = (OperatorDeclaration)decl;
    }

    return op.decl;
  }

  @Override
  public Object visit(IntegerLiteral intlit, Object arg) {
    return null;
  }

  public void check(Program program) {
    if (program == null) {
      return;
    }

    idTable.openScope();
    StandardEnvironment.load(idTable);
    visit(program, null);
    idTable.closeScope();
  }
}
