package com.z0ltan.compilers.minitriangle.encoder;

public class Instruction {
  public Opcode op; // the opcode
  public Register r; // the register for this instruction, if any 
  public byte n; // instruction-specific length
  public short d; // the operand - can be a displacement or a value

  static enum Opcode {
    CALL,
    CALLI,
    HALT,
    JUMP,
    JUMPI,
    JUMPIF,
    LOAD,
    LOADA,
    LOADI,
    LOADL,
    POP,
    PUSH,
    RETURN,
    STORE,
    STOREI,
  }

  static enum Register {
    CB,
    CT,
    SB,
    ST,
    LB,
    LT,
    L1, 
    L2,
    L3,
    L4,
    L5,
    L6,
    HB,
    HT,
    PB,
    PT,
    CP,
  }

  public Instruction(Opcode op, Register r, byte n, short d) {
    this.op = op;
    this.r = r;
    this.n = n;
    this.d = d;
  }
}
