package com.z0ltan.compilers.minitriangle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.z0ltan.compilers.minitriangle.scanner.TokenType;
import com.z0ltan.compilers.minitriangle.scanner.Token;
import com.z0ltan.compilers.minitriangle.scanner.Scanner;
import com.z0ltan.compilers.minitriangle.error.MiniTriangleException;

public class App {
  private static final String PROMPT = ">> ";

  public static void main(String[] args) {
    if (args.length == 0) {
      runRepl();
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      usage();
    }
  }

  private static void runRepl() {
    System.out.println("Welcome to the Mini-Triangle repl. Press Ctrl+D (or Ctrl+C) to quit...\n");

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        System.out.print(PROMPT);
        System.out.flush();

        String line = reader.readLine();
        if (line == null) {
          break;
        }

        try {
          Scanner scanner = new Scanner(line);
          for (Token token = scanner.scan(); token.kind != TokenType.EOF; token = scanner.scan()) {
            System.out.println(token);
          }
        } catch (MiniTriangleException ex) {
          System.err.println("Error while scanning: " + ex.getLocalizedMessage());
        }
      }
    }catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private static void runFile(String filepath) {
    try {
      Scanner scanner = new Scanner(Paths.get(filepath));
      for (Token token = scanner.scan(); token.kind != TokenType.EOF; token = scanner.scan()) {
        System.out.println(token);
      }
    } catch (Exception ex) {
      throw new MiniTriangleException(ex);
    }
  }

  private static void usage() {
    System.err.println("Usage: mini-triangle [file]");
    System.exit(1);
  }
}
