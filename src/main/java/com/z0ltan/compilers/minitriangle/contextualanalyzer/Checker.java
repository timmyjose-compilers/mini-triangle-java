package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import com.z0ltan.compilers.minitriangle.ast.Program;
import com.z0ltan.compilers.minitriangle.ast.AssignCommand;
import com.z0ltan.compilers.minitriangle.ast.CallCommand;
import com.z0ltan.compilers.minitriangle.ast.IfCommand;
import com.z0ltan.compilers.minitriangle.ast.LetCommand;
import com.z0ltan.compilers.minitriangle.ast.WhileCommand;
import com.z0ltan.compilers.minitriangle.ast.SequentialCommand;
import com.z0ltan.compilers.minitriangle.ast.ParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FormalParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialParamDeclaration;
import com.z0ltan.compilers.minitriangle.ast.CallArgument;
import com.z0ltan.compilers.minitriangle.ast.SequentialArgument;
import com.z0ltan.compilers.minitriangle.ast.Declaration;
import com.z0ltan.compilers.minitriangle.ast.ConstDeclaration;
import com.z0ltan.compilers.minitriangle.ast.VarDeclaration;
import com.z0ltan.compilers.minitriangle.ast.ProcedureDeclaration;
import com.z0ltan.compilers.minitriangle.ast.FunctionDeclaration;
import com.z0ltan.compilers.minitriangle.ast.OperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.UnaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.BinaryOperatorDeclaration;
import com.z0ltan.compilers.minitriangle.ast.SequentialDeclaration;
import com.z0ltan.compilers.minitriangle.ast.IntegerExpression;
import com.z0ltan.compilers.minitriangle.ast.CharacterExpression;
import com.z0ltan.compilers.minitriangle.ast.VnameExpression;
import com.z0ltan.compilers.minitriangle.ast.CallExpression;
import com.z0ltan.compilers.minitriangle.ast.UnaryExpression;
import com.z0ltan.compilers.minitriangle.ast.BinaryExpression;
import com.z0ltan.compilers.minitriangle.ast.SimpleTypeDenoter;
import com.z0ltan.compilers.minitriangle.ast.SimpleVname;
import com.z0ltan.compilers.minitriangle.ast.Identifier;
import com.z0ltan.compilers.minitriangle.ast.Operator;
import com.z0ltan.compilers.minitriangle.ast.IntegerLiteral;
import com.z0ltan.compilers.minitriangle.ast.CharacterLiteral;
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
      ErrorReporter.reportWithNoExit("The left side of an assignment command must be a variable",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    if (!vType.equals(eType)) {
      ErrorReporter.reportWithNoExit("Both sides of an assignment command must have the same type",
          cmd.sourcePosition.start.line,
          cmd.sourcePosition.start.column);
    }

    return null;
  }

  @Override
  public Object visit(CallCommand cmd, Object arg) {
    cmd.I.accept(this, null);
    Declaration decl = (Declaration)cmd.I.accept(this, null);
    cmd.A.accept(this, ((ProcedureDeclaration)decl).P);

    return null;
  }

  @Override
  public Object visit(IfCommand cmd, Object arg) {
    Type eType = (Type) cmd.E.accept(this, null);

    if (!eType.equals(Types.BOOL)) {
      ErrorReporter.reportWithNoExit("the condition of an if command must be a boolean expression, not a " + eType,
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
      ErrorReporter.reportWithNoExit("the condiiton of a while command must be a boolean, not a " + eType,
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
  public Object visit(FormalParamDeclaration param, Object arg) {
    param.T.accept(this, null);
    System.out.println("Entering " + param.I.spelling);
    idTable.enter(param.I.spelling, param);
    idTable.display();

    return null;
  }

  @Override
  public Object visit(SequentialParamDeclaration param, Object arg) {
    param.P1.accept(this, arg);
    param.P2.accept(this, arg);

    return null;
  }

  @Override
  public Object visit(CallArgument callarg, Object arg) {
    ParamDeclaration decl = (ParamDeclaration)arg;

    if (!(decl instanceof FormalParamDeclaration)) {
      ErrorReporter.report("expected a single argument, got multiple arguments in function call",
          callarg.sourcePosition.start.line,
          callarg.sourcePosition.start.column);
    } else {
      FormalParamDeclaration formalParamDecl = (FormalParamDeclaration)decl;
      Type argType = (Type)callarg.E.accept(this, null);
      if (!argType.equals(formalParamDecl.T.type)) {
        ErrorReporter.report("type mismatch in function call - expected " + argType.toString() +
            ", got " + formalParamDecl.T.type,
            callarg.sourcePosition.start.line,
            callarg.sourcePosition.start.column);
      }
    }

    return null;
  }

  @Override
  public Object visit(SequentialArgument callarg, Object arg) {
    ParamDeclaration decl = (ParamDeclaration)arg;
    if (!(decl instanceof SequentialParamDeclaration)) {
      ErrorReporter.report("function call declaration and invocation have different number of parameters",
          callarg.sourcePosition.start.line,
          callarg.sourcePosition.start.column);
    } else {
      SequentialParamDeclaration seqParamDecl = (SequentialParamDeclaration)decl;
      callarg.A1.accept(this, seqParamDecl.P1);
      callarg.A2.accept(this, seqParamDecl.P2);
    }

    return null;
  }

  @Override
  public Object visit(IntegerExpression expr, Object arg) {
    expr.type = Types.INT;
    return expr.type;
  }

  @Override
  public Object visit(CharacterExpression expr, Object arg) {
    expr.type = Types.CHAR;
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
    Type eType = (Type)expr.E.accept(this, null);

    OperatorDeclaration opdecl = (OperatorDeclaration)expr.O.accept(this, null);
    if (opdecl == null) {
      ErrorReporter.reportWithNoExit("no such operator - \"" + expr.O.spelling +  "\"",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    } else if (opdecl instanceof UnaryOperatorDeclaration) {
      UnaryOperatorDeclaration unopdecl = (UnaryOperatorDeclaration)opdecl;
      if (!eType.equals(unopdecl.OperandType)) {
        ErrorReporter.reportWithNoExit("\"" + eType.toString() + "\" is not a supported type for unary expressions",
            expr.sourcePosition.start.line,
            expr.sourcePosition.start.column);
        expr.type = Types.ERROR;
        return expr.type;
      } 
      expr.type = unopdecl.ResultType;
    } else {
      ErrorReporter.reportWithNoExit("unsupported operator for unary expression - " + "\"" + expr.O.spelling + "\"",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    }

    return expr.type;
  }

  @Override
  public Object visit(BinaryExpression expr, Object arg) {
    Type e1Type = (Type) expr.E1.accept(this, null);
    Type e2Type = (Type) expr.E2.accept(this, null);

    OperatorDeclaration opdecl = (OperatorDeclaration)expr.O.accept(this, null);
    if (opdecl == null) {
      ErrorReporter.reportWithNoExit("no such operator - " + "\"" + expr.O.spelling +  "\"",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    } else if (opdecl instanceof BinaryOperatorDeclaration) {
      BinaryOperatorDeclaration binopdecl = (BinaryOperatorDeclaration)opdecl;
      if (!e1Type.equals(binopdecl.Operand1Type)) {
        ErrorReporter.reportWithNoExit("the left hand side of the binary expression has the wrong type - " + "\"" + e1Type + "\"",
            expr.sourcePosition.start.line,
            expr.sourcePosition.start.column);
        expr.type = Types.ERROR;
        return expr.type;
      }

      if (!e2Type.equals(binopdecl.Operand2Type)) {
        ErrorReporter.reportWithNoExit("the right hand side of the binary expression has the wrong type - " + "\"" + e2Type + "\"",
            expr.sourcePosition.start.line,
            expr.sourcePosition.start.column);
        expr.type = Types.ERROR;
        return expr.type;
      }

      expr.type = binopdecl.ResultType;
    } else {
      ErrorReporter.reportWithNoExit("\"" + expr.O.spelling + "\"" + " is not a binary operator",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    }

    return expr.type;
  }

  @Override 
  public Object visit(CallExpression expr, Object arg) {
    Declaration decl = (Declaration)expr.I.accept(this, null);

    if (decl == null) {
      ErrorReporter.reportWithNoExit("\"" + expr.I.spelling + "\" is undeclared",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    } else if (decl instanceof ProcedureDeclaration) {
      ProcedureDeclaration procDecl = (ProcedureDeclaration)decl;
      expr.A.accept(this, procDecl.P);
      expr.type = Types.VOID;
    } else if (decl instanceof FunctionDeclaration) {
      FunctionDeclaration funcDecl = (FunctionDeclaration)decl;
      expr.A.accept(this, funcDecl.P);
      expr.type = funcDecl.T.type;
    } else {
      ErrorReporter.reportWithNoExit("\"" + decl + "\" was not expected in a call expression",
          expr.sourcePosition.start.line,
          expr.sourcePosition.start.column);
      expr.type = Types.ERROR;
    }

    return expr.type;
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
    // this is done as part of loading the standard environment
    // this is a place-holder for custom operators, if supported
    return null;
  }

  @Override
  public Object visit(BinaryOperatorDeclaration decl, Object arg) {
    // this is done as part of loading the standard environment
    // this is a place-holder for custom operators, if supported
    return null;
  }

  @Override
  public Object visit(ProcedureDeclaration decl, Object arg) {
    idTable.enter(decl.I.spelling, decl); 
    idTable.openScope();
    decl.P.accept(this, null);
    decl.C.accept(this, null);
    idTable.closeScope();

    return null;
  }

  @Override
  public Object visit(FunctionDeclaration decl, Object arg) {
    Type tdType = (Type)decl.T.accept(this, null);
    idTable.enter(decl.I.spelling, decl);
    idTable.openScope();
    decl.P.accept(this, decl);
    Type eType = (Type)decl.E.accept(this, null);
    idTable.closeScope();

    if (!eType.equals(tdType)) {
      ErrorReporter.reportWithNoExit("function's declared type \"" + tdType.toString() + 
          "\" does not match the actual type \"" + eType.toString() + "\"",
          decl.sourcePosition.start.line,
          decl.sourcePosition.start.column);
    }

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
      case "Any":
        td.type = Types.ANY;
        break;

      case "Boolean":
        td.type = Types.BOOL;
        break;

      case "Integer":
        td.type = Types.INT;
        break;

      case "Char":
        td.type = Types.CHAR;
        break;

      default:
        ErrorReporter.reportWithNoExit("unknown simple type \"" + td.I.spelling + "\"",
            td.sourcePosition.start.line,
            td.sourcePosition.start.column);
    }

    return td.type;
  }

  @Override
  public Object visit(SimpleVname vname, Object arg) {
    Declaration decl = (Declaration) vname.I.accept(this, null);

    idTable.display();
    if (decl == null) {
      System.out.println("vname = " + vname);
      ErrorReporter.reportWithNoExit("\"" + vname.I.spelling + "\" is undeclared",
          vname.sourcePosition.start.line,
          vname.sourcePosition.start.column);
    }

    if (decl instanceof ConstDeclaration) {
      vname.type = ((ConstDeclaration) decl).E.type;
      vname.variable = false;
    } else if (decl instanceof VarDeclaration) {
      vname.type = ((VarDeclaration) decl).T.type;
      vname.variable = true;
    } else if (decl instanceof FormalParamDeclaration) {
      vname.type = ((FormalParamDeclaration)decl).T.type;
      vname.variable = false;
    }  else if (decl instanceof FunctionDeclaration) {
      vname.type = ((FunctionDeclaration) decl).T.type;
      vname.variable = false;
    } else if (decl instanceof OperatorDeclaration) {
      ErrorReporter.reportWithNoExit("\"" + vname.I.spelling + "\"" + " is declared as an operator - you cannot assign to an operator",
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

  @Override
  public Object visit(CharacterLiteral charlit, Object arg) {
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
