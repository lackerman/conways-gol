/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Leo Ackerman
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package za.co.scrinium.conways.calculator;

import za.co.scrinium.conways.classifier.StateClassifier;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 11:32 AM
 */
public class SurvivalCalculator<T> {

  public enum Transition {
    DIE,
    REBORN,
    SURVIVE
  }

  private final StateClassifier<T> classifier;

  public SurvivalCalculator(StateClassifier<T> aClassifier) {
    classifier = aClassifier;
  }

  private int getNumAlive(T[][] aBoard, int aRowIndex, int aColIndex, int aNumRows, int aNumCols) {
    int count = 0;
    for (int row = aRowIndex - 1; row <= aRowIndex + 1; row++) {
      for (int col = aColIndex - 1; col <= aColIndex + 1; col++) {
        if (isWithinBoard(aNumRows, aNumCols, row, col)) {
          if (getClassifier().isAlive(aBoard[row][col])) {
            count++;
          }
        }
      }
    }
    return count;
  }

  /**
   * Calls getNeighbourCount and subtracts 1 if individual is currently alive because it
   * would have been included in the total
   */
  public int getNeighbourCount(T[][] aBoard, int aRowIndex, int aColIndex, int aNumRows, int aNumCols) {
    int numNeighboursIncludingSelf = getNumAlive(aBoard, aRowIndex, aColIndex, aNumRows, aNumCols);
    if (getClassifier().isAlive(aBoard[aRowIndex][aColIndex])) {
      return numNeighboursIncludingSelf - 1;
    }
    return numNeighboursIncludingSelf;
  }

  /**
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   *
   * @param aBoard    The board on which the game is being played
   * @param aRowIndex The row index of the individual being tested
   * @param aColIndex The column index of the individual being tested
   * @param aNumRows  The number of rows in the board
   * @param aNumCols  The number of columns in the board
   * @return An enum indicating whether the individual should SURVIVE, DIE or be REBORN
   */
  public Transition getIndividualTransition(T[][] aBoard, int aRowIndex, int aColIndex, int aNumRows, int aNumCols) {
    int numNeighbours = getNeighbourCount(aBoard, aRowIndex, aColIndex, aNumRows, aNumCols);
    if (getClassifier().isAlive(aBoard[aRowIndex][aColIndex])) {
      if (numNeighbours == 2 || numNeighbours == 3) {
        return Transition.SURVIVE;
      }
    } else {
      if (numNeighbours == 3) {
        return Transition.REBORN;
      }
    }
    return Transition.DIE;
  }

  private boolean isWithinBoard(int aNumRows, int aNumCols, int aRow, int aCol) {
    return !(aCol < 0 || aCol >= aNumCols || aRow < 0 || aRow >= aNumRows);
  }

  private StateClassifier<T> getClassifier() {
    return classifier;
  }
}
