package com.z0ltan.compilers.minitriangle.scanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import com.z0ltan.compilers.minitriangle.error.MiniTriangleException;

public class SourceFile {
  private List<Char> characters;
  private Char nullCharacter;
  private int idx;

  public SourceFile(Path filepath) {
    this.characters = new ArrayList<>();
    try {
      String input = Files.readString(filepath);
      loadLines(input);
    } catch (Exception ex) {
      throw new MiniTriangleException(ex);
    }
  }

  public SourceFile(String line) {
    this.characters = new ArrayList<>();
    this.idx = 0;
    loadLines(line);
  }

  private void loadLines(String input) {
    int line = 1;
    int column = 1;

    for (char c : input.toCharArray()) {
      if (c == '\n') {
        line++;
        column = 1;
      }
      this.characters.add(new Char(c, line, column));
      column++;
    }
    this.nullCharacter = Char.nullCharacter(line, column);
  }

  public Char nextCharacter() {
    if (idx >= this.characters.size()) {
      return nullCharacter;
    }
    return this.characters.get(idx++);
  }
}
