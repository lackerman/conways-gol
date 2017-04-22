package io.lacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Loader {

  static Boolean[][] loadUniverse(String filename) {
    Scanner scanner = new Scanner(Loader.class.getResourceAsStream(filename));
    List<String> lines = new ArrayList<>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }

    int numRows = lines.size();
    int numCols = lines.get(0).length();
    Boolean[][] universe = new Boolean[numRows][numCols];
    String line;
    for (int row = 0; row < numRows; row++) {
      line = lines.get(row);
      for (int col = 0; col < numCols; col++) {
        universe[row][col] = line.charAt(col) == '*';
      }
    }
    return universe;
  }
}
