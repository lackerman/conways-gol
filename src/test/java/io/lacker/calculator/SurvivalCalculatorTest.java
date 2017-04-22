package io.lacker.calculator;

import io.lacker.classifier.BooleanStateClassifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SurvivalCalculatorTest {

  private static final boolean A = true; // ALIVE - shortened to neaten display
  private static final boolean D = false; // DEAD

  private SurvivalCalculator survivalCalculator;

  @Before
  public void setup() {
    survivalCalculator = new SurvivalCalculator(new BooleanStateClassifier());
  }

  @Test
  public void testNoNeighbours() throws Exception {
    Boolean[][] board = {
      {D, D, D},
      {D, D, D},
      {D, D, D}
    };
    Assert.assertEquals(0, survivalCalculator.getNeighbourCount(board, 1, 1));
  }

  @Test
  public void testAllNeighbours() throws Exception {
    Boolean[][] board = {
      {A, A, A},
      {A, A, A},
      {A, A, A}
    };
    Assert.assertEquals(8, survivalCalculator.getNeighbourCount(board, 1, 1));
  }

  @Test
  public void testCorners() throws Exception {
    Boolean[][] board = {
      {A, D, A},
      {D, A, D},
      {A, D, A}
    };
    // Top Left has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 0, 0));
    // Top Right has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 0, 2));
    // Bottom Left has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 2, 0));
    // Bottom Right has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 2, 2));
    // Centre has 4
    Assert.assertEquals(4, survivalCalculator.getNeighbourCount(board, 1, 1));
  }
}
