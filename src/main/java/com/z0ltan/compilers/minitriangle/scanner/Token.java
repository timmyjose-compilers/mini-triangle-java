package com.z0ltan.compilers.minitriangle.scanner;

import java.util.Map;
import java.util.HashMap;

public class Token {
  public TokenType kind;
  public String spelling;
  public int line;
  public int column;

  private static final Map<String, TokenType> keywords;

  static {
    keywords = new HashMap<>();

    keywords.put("begin", TokenType.BEGIN);
    keywords.put("const", TokenType.CONST);
    keywords.put("do", TokenType.DO);
    keywords.put("else", TokenType.ELSE);
    keywords.put("end", TokenType.END);
    keywords.put("func", TokenType.FUNCTION);
    keywords.put("if", TokenType.IF);
    keywords.put("in", TokenType.IN);
    keywords.put("let", TokenType.LET);
    keywords.put("return", TokenType.RETURN);
    keywords.put("then", TokenType.THEN);
    keywords.put("var", TokenType.VAR);
    keywords.put("while", TokenType.WHILE);
  }

  public static boolean isKeyword(String spelling) {
    return Token.keywords.containsKey(spelling);
  }

  public TokenType getKeywordKind(String spelling) {
    return Token.keywords.get(spelling);
  }

  public Token(TokenType kind, String spelling, int line, int column) {
    this.spelling = spelling;
    if (kind == TokenType.IDENTIFIER && isKeyword(spelling)) {
      this.kind = getKeywordKind(spelling);
    } else {
      this.kind = kind;
    }
    this.line = line;
    this.column = column;
  }

  @Override
  public String toString() {
    return "<kind= " + this.kind + ", spelling=" + this.spelling + ", line=" + this.line + ", column=" + this.column + ">";
  }
}
