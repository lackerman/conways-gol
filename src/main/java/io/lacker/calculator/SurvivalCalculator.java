package io.lacker.calculator;

import io.lacker.classifier.StateClassifier;

public class SurvivalCalculator implements Calculator<Boolean> {

  private final StateClassifier<Boolean> classifier;

  public SurvivalCalculator(StateClassifier<Boolean> classifier) {
    this.classifier = classifier;
  }

  @Override
  public int getNeighbourCount(Boolean[][] universe, int row, int col) {
    int count = 0;
    int numRows = universe.length;
    int numCols = universe[0].length;

    for (int r = row - 1; r <= row + 1; r++) {
      for (int c = col - 1; c <= col + 1; c++) {
        if (isWithinBoard(numRows, numCols, r, c)) {
          if (getClassifier().isAlive(universe[r][c])) {
            if (!(row == r && col == c)) {
              count++;
            }
          }
        }
      }
    }
    return count;
  }

  @Override
  public Boolean shouldIndividualsSurviveNextIteration(Boolean[][] universe, int row, int col) {
    int numNeighbours = getNeighbourCount(universe, row, col);
    if (getClassifier().isAlive(universe[row][col])) {
      if (numNeighbours == 2 || numNeighbours == 3) {
        return true;
      }
    } else {
      if (numNeighbours == 3) {
        return true;
      }
    }
    return false;
  }

  private StateClassifier<Boolean> getClassifier() {
    return classifier;
  }
}
