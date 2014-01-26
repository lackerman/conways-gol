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

package za.co.scrinium.conways.game;


import za.co.scrinium.conways.calculator.SurvivalCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 9:28 AM
 * <p/>
 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in
 * one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that
 * are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
 */
public class ConwayGOL {

  private Boolean[][] universe;
  private SurvivalCalculator<Boolean> calculator;

  /**
   * Creates an empty initialised Conway's Game of Life universe with a randomly generated population
   *
   * @param aSurvivalCalculator The calculator to use to determine whether or not an individual survives
   * @param aNumRows            The number of Rows (Height) of the 2D Universe
   * @param aNumCols            The number of Columns (Width) of the 2D universe
   */
  public ConwayGOL(SurvivalCalculator<Boolean> aSurvivalCalculator, int aNumRows, int aNumCols) {
    calculator = aSurvivalCalculator;
    universe = new Boolean[aNumRows][aNumCols];
    initUniverse();
    initRandomPopulation();
  }

  /**
   * Uses a predefined Universe to Construct Conway's Game of Life
   *
   * @param aSurvivalCalculator The calculator to use to determine whether or not an individual survives
   * @param aUniverse           A already initialised universe
   */
  public ConwayGOL(SurvivalCalculator<Boolean> aSurvivalCalculator, Boolean[][] aUniverse) {
    assert null != aUniverse;
    universe = aUniverse;
    calculator = aSurvivalCalculator;
  }

  public void initUniverse() {
    for (int row = 0; row < getNumRows(); row++) {
      for (int col = 0; col < getNumCols(); col++) {
        setIndividualState(row, col, false);
      }
    }
  }

  public void initRandomPopulation() {
    // Initialise a random population
    Random random = new Random(System.currentTimeMillis());
    // Random number of individuals to generate size
    int size = getNumRows() * getNumCols() / 5;
    for (int i = 0; i < size; i++) {
      setIndividualState(random.nextInt(getNumRows()), random.nextInt(getNumCols()), true);
    }
  }

  public void evolve() {
    List<Coordinates> coordinates = new ArrayList<Coordinates>();
    SurvivalCalculator.Transition transition;
    for (int row = 0; row < getNumRows(); row++) {
      for (int col = 0; col < getNumCols(); col++) {
        transition = getCalculator().getIndividualTransition(getUniverse(), row, col, getNumRows(), getNumCols());
        if (SurvivalCalculator.Transition.DIE != transition) {
          coordinates.add(new Coordinates(row, col));
        }
      }
    }
    initUniverse();
    for (Coordinates coordinate : coordinates) {
      setIndividualState(coordinate.getRow(), coordinate.getCol(), true);
    }
  }

  private void setIndividualState(int aRow, int aCol, boolean aState) {
    getUniverse()[aRow][aCol] = aState;
  }

  public Boolean[][] getUniverse() {
    return universe;
  }

  private int getNumRows() {
    return getUniverse().length;
  }

  private int getNumCols() {
    return getUniverse()[0].length;
  }

  private SurvivalCalculator<Boolean> getCalculator() {
    return calculator;
  }

  private final class Coordinates {

    private final int row;
    private final int col;

    private Coordinates(int aRow, int aCol) {
      row = aRow;
      col = aCol;
    }

    int getRow() {
      return row;
    }

    int getCol() {
      return col;
    }
  }
}
