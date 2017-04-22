package io.lacker.game;

import io.lacker.calculator.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in
 * one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that
 * are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
 */
public class ConwayGOL {

  private final Boolean[][] universe;
  private final Calculator<Boolean> calculator;

  /**
   * Creates an empty initialised Conway's Game of Life universe with a randomly generated population
   *
   * @param calculator The calculator to use to determine whether or not an individual survives
   * @param numRows    The number of Rows (Height) of the 2D Universe
   * @param numCols    The number of Columns (Width) of the 2D universe
   */
  public ConwayGOL(Calculator<Boolean> calculator, int numRows, int numCols) {
    this.calculator = calculator;
    this.universe = new Boolean[numRows][numCols];
    initUniverse();
    initRandomPopulation();
  }

  /**
   * Uses a predefined Universe to Construct Conway's Game of Life
   *
   * @param calculator The calculator to use to determine whether or not an individual survives
   * @param universe   A already initialised universe
   */
  public ConwayGOL(Calculator<Boolean> calculator, Boolean[][] universe) {
    assert null != universe;
    this.universe = universe;
    this.calculator = calculator;
  }

  private void initUniverse() {
    for (int row = 0; row < getNumRows(); row++) {
      for (int col = 0; col < getNumCols(); col++) {
        setIndividualState(row, col, false);
      }
    }
  }

  private void initRandomPopulation() {
    // Initialise a random population
    Random random = new Random(System.currentTimeMillis());
    // Random number of individuals to generate size
    int size = getNumRows() * getNumCols() / 5;
    for (int i = 0; i < size; i++) {
      setIndividualState(random.nextInt(getNumRows()), random.nextInt(getNumCols()), true);
    }
  }

  public void evolve() {
    List<Coordinates> coordinates = new ArrayList<>();
    for (int row = 0; row < getNumRows(); row++) {
      for (int col = 0; col < getNumCols(); col++) {
        if (getCalculator().shouldIndividualsSurviveNextIteration(getUniverse(), row, col)) {
          coordinates.add(new Coordinates(row, col));
        }
      }
    }
    initUniverse();
    for (Coordinates coordinate : coordinates) {
      setIndividualState(coordinate.getRow(), coordinate.getCol(), true);
    }
  }

  private void setIndividualState(int row, int col, boolean state) {
    getUniverse()[row][col] = state;
  }

  public Boolean[][] getUniverse() {
    return universe;
  }

  public int getNumRows() {
    return getUniverse().length;
  }

  public int getNumCols() {
    return getUniverse()[0].length;
  }

  private Calculator<Boolean> getCalculator() {
    return calculator;
  }

  private final class Coordinates {

    private final int row;
    private final int col;

    private Coordinates(int row, int col) {
      this.row = row;
      this.col = col;
    }

    int getRow() {
      return row;
    }

    int getCol() {
      return col;
    }
  }
}
