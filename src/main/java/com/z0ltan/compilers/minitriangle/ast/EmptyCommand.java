package com.z0ltan.compilers.minitriangle.ast;

import com.z0ltan.compilers.minitriangle.scanner.SourcePosition;
import com.z0ltan.compilers.minitriangle.visitor.Visitor;

public class EmptyCommand extends Command {
  public EmptyCommand(SourcePosition sourcePosition) {
    super(sourcePosition);
  }

  @Override
  public Object accept(Visitor visitor, Object arg) {
    throw new UnsupportedOperationException();
  }
}
