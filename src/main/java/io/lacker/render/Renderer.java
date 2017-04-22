package io.lacker.render;

import io.lacker.classifier.StateClassifier;

public abstract class Renderer<T> {

  private final int numRows;
  private final int numColumns;
  private final StateClassifier<T> stateClassifier;

  Renderer(StateClassifier<T> stateClassifier, int numRows, int numColumns) {
    this.stateClassifier = stateClassifier;
    this.numRows = numRows;
    this.numColumns = numColumns;
  }

  public abstract void render(T[][] board);

  public void shutdown() {
  }

  int getNumRows() {
    return numRows;
  }

  int getNumColumns() {
    return numColumns;
  }

  StateClassifier<T> getStateClassifier() {
    return stateClassifier;
  }
}
