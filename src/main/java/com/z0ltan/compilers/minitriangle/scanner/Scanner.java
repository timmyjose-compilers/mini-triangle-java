package com.z0ltan.compilers.minitriangle.scanner;

import java.nio.file.Path;
import com.z0ltan.compilers.minitriangle.error.ErrorReporter;

public class Scanner {
  private SourceFile source;
  private Char currentChar;
  private TokenType currentKind;
  private StringBuffer currentSpelling;
  private Position currentPosition;

  public Scanner(String filepath) {
    this.source = new SourceFile(filepath);
    this.currentChar = Char.nullCharacter();
    this.currentPosition = Position.illegalPosition();
    initialize();
  }

  public Scanner(Path filepath) {
    this.source = new SourceFile(filepath);
    this.currentChar = Char.nullCharacter();
    this.currentPosition = Position.illegalPosition();
    initialize();
  }

  private void initialize() {
    this.currentChar = source.nextCharacter();
    this.currentPosition = new Position(1, 1);
  }

  private void skipIt() {
    currentChar = source.nextCharacter();
  }

  private void skip(char expectedChar) {
    if (currentChar.character != expectedChar) {
      ErrorReporter.report("expected to skip character " + expectedChar + ", but got character " + currentChar.character,
          currentChar.position.line,
          currentChar.position.line);
    }

    currentChar = source.nextCharacter();
  }

  private void takeIt() {
    currentSpelling.append(currentChar.character);
    currentChar = source.nextCharacter();
  }

  private void take(char expectedChar) {
    if (currentChar.character != expectedChar) {
      ErrorReporter.report("expected to take character " + expectedChar + ", but got character " + currentChar.character,
          currentChar.position.line,
          currentChar.position.column);
    }

    currentSpelling.append(currentChar.character);
    currentChar = source.nextCharacter();
  }

  private void scanSeparator() {
    switch (currentChar.character) {
      case '!':
        {
          skipIt();
          while (currentChar.character != '\n') {
            skipIt();
          }
          skip('\n');
        }
        break;

      case ' ':
      case '\t':
      case '\n':
        skipIt();
        break;
    }
  }

  TokenType scanToken() {
    TokenType kind = TokenType.ILLEGAL;
    currentPosition = currentChar.position;

    switch (currentChar.character) {
      case '\u0000':
        {
          takeIt();
          kind = TokenType.EOF;
        }
        break;

      case '(':
        {
          takeIt();
          kind = TokenType.LEFT_PAREN;
        }
        break;

      case ')':
        {
          takeIt();
          kind = TokenType.RIGHT_PAREN;
        }
        break;

      case '~':
        {
          takeIt();
          kind = TokenType.IS;
        }
        break;

      case '+':
      case '-':
      case '*':
      case '/':
      case '<':
      case '>':
      case '=':
        {
          takeIt();
          kind = TokenType.OPERATOR;
        }
        break;

      case '\\':
        {
          takeIt();
          if (currentChar.character == '=') {
            takeIt();
            kind = TokenType.OPERATOR;
          } else {
            ErrorReporter.report("unexpected character " + currentChar.character + ", expected to take character '=' for the 'not-equal' operator",
                currentChar.position.line,
                currentChar.position.column);
          }
        }
        break;

      case ',':
        {
          takeIt();
          kind = TokenType.COMMA;
        }
        break;

      case ';':
        {
          takeIt();
          kind = TokenType.SEMICOLON;
        }
        break;

      case ':':
        {
          takeIt();
          if (currentChar.character == '=') {
            takeIt();
            kind = TokenType.BECOMES;
          } else {
            kind = TokenType.COLON;
          }
        }
        break;

      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
      case 'g':
      case 'h':
      case 'i':
      case 'j':
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'p':
      case 'q':
      case 'r':
      case 's':
      case 't':
      case 'u':
      case 'v':
      case 'w':
      case 'x':
      case 'y':
      case 'z':
      case 'A':
      case 'B':
      case 'C':
      case 'D':
      case 'E':
      case 'F':
      case 'G':
      case 'H':
      case 'I':
      case 'J':
      case 'K':
      case 'L':
      case 'M':
      case 'N':
      case 'O':
      case 'P':
      case 'Q':
      case 'R':
      case 'S':
      case 'T':
      case 'U':
      case 'V':
      case 'W':
      case 'X':
      case 'Y':
      case 'Z':
        {
          takeIt();
          while (Character.isLetter(currentChar.character) || Character.isDigit(currentChar.character)) {
            takeIt();
          }
          kind = TokenType.IDENTIFIER;
        }
        break;

      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        {
          takeIt();
          while (Character.isDigit(currentChar.character)) {
            takeIt();
          }
          kind = TokenType.INTEGER_LITERAL;
        }
        break;

      default:
        ErrorReporter.report("unexpected character " + currentChar.character,
            currentChar.position.line,
            currentChar.position.column);
    }

    return kind;
  }

  public Token scan() {
    while (currentChar.character == '!' || currentChar.character == ' ' || currentChar.character == '\n') {
      scanSeparator();
    }

    currentSpelling = new StringBuffer();
    currentKind = scanToken();

    return new Token(currentKind, currentSpelling.toString(), currentPosition);
  }
}
