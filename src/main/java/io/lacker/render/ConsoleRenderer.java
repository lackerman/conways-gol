package io.lacker.render;

import io.lacker.classifier.StateClassifier;

public class ConsoleRenderer<T> extends Renderer<T> {

  private static final char ALIVE = '#';
  private static final char DEAD = ' ';

  public ConsoleRenderer(StateClassifier<T> stateClassifier, int numRows, int numColumns) {
    super(stateClassifier, numRows, numColumns);
  }

  @Override
  public void render(T[][] board) {
    StringBuilder builder = new StringBuilder();
    buildRow(builder);
    for (int row = 0; row < getNumRows(); row++) {
      builder.append("|");
      for (int col = 0; col < getNumColumns(); col++) {
        builder.append(getStateClassifier().isAlive(board[row][col]) ? ALIVE : DEAD);
      }
      builder.append("|\n");
    }
    buildRow(builder);
    System.out.println(builder.toString());
  }

  private void buildRow(StringBuilder builder) {
    builder.append('+');
    for (int col = 0; col < getNumColumns(); col++) {
      builder.append('-');
    }
    builder.append('+').append("\n");
  }
}
