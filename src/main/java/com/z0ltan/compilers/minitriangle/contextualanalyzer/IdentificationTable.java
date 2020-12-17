package com.z0ltan.compilers.minitriangle.contextualanalyzer;

import java.util.Map;
import java.util.HashMap;

import com.z0ltan.compilers.minitriangle.ast.Declaration;

public class IdentificationTable {
  private int currentLevel;

  private final Map<Integer, Map<String, Declaration>> idTable;

  public IdentificationTable() {
    this.currentLevel = -1;
    this.idTable = new HashMap<>();
    this.idTable.put(currentLevel, new HashMap<>());
  }

  public void enter(String id, Declaration decl) {
    if (idTable.get(currentLevel) == null) {
      idTable.put(currentLevel, new HashMap<>());
    }
    idTable.get(currentLevel).put(id, decl);
  }

  public Declaration retrieve(String id) {
    int level = currentLevel;

    while (level >= 0) {
      if ((idTable.get(level) != null) && idTable.get(level).containsKey(id)) {
        return idTable.get(level).get(id);
      }
      level--;
    }

    return null;
  }

  public void openScope() {
    currentLevel++;
    idTable.put(currentLevel, new HashMap<>());
  }

  public void closeScope() {
    if (idTable.get(currentLevel) != null) {
      idTable.get(currentLevel).clear();
      idTable.remove(currentLevel);
    }
    currentLevel--;
  }

  public void display() {
    int level = currentLevel;

    while (level >= 0) {
      System.out.println("Level " + level);

      if (idTable.get(level) == null) {
        System.out.println("No entries for level " + level);
      } else {
        for (Map.Entry<String, Declaration> entry : idTable.get(level).entrySet()) {
          System.out.printf("%s => %s\n", entry.getKey(), entry.getValue());
        }
      }
      level--;
    }
  }
}
