package io.lacker.calculator;

public interface Calculator<T> {

  default boolean isWithinBoard(int numRows, int numCols, int row, int col) {
    return !(col < 0 || col >= numCols || row < 0 || row >= numRows);
  }

  /**
   * Determines the number of alive individuals surrounding the individual at the
   * specified row and column index
   *
   * @param board    The board on which the game is being played
   * @param rowIndex The row index of the surrounded individual
   * @param colIndex The column index of the surrounded individual
   */
  int getNeighbourCount(T[][] board, int rowIndex, int colIndex);

  /**
   * Determines the state of the individual in the next iteration
   * <p>
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   *
   * @param board  The board on which the game is being played
   * @param row    The row index of the individual being tested
   * @param column The column index of the individual being tested
   * @return An flag indicating whether or not the individual should survive the next iteration
   */
  Boolean shouldIndividualsSurviveNextIteration(T[][] board, int row, int column);
}
