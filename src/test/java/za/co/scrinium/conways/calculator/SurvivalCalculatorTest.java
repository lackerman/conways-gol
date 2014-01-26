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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.scrinium.conways.classifier.BooleanStateClassifier;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 11:49 AM
 */
public class SurvivalCalculatorTest {

  private SurvivalCalculator<Boolean> survivalCalculator;

  @Before
  public void setup() {
    survivalCalculator = new SurvivalCalculator<Boolean>(new BooleanStateClassifier());
  }

  @Test
  public void testNoNeighbours() throws Exception {
    Boolean[][] board = {
            {false, false, false},
            {false, true, false},
            {false, false, false}
    };
    Assert.assertEquals(0, survivalCalculator.getNeighbourCount(board, 1, 1, 3, 3));
  }

  @Test
  public void testCorners() throws Exception {
    Boolean[][] board = {
            {true, false, true},
            {false, true, false},
            {true, false, true}
    };
    // Top Left has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 0, 0, 3, 3));
    // Top Right has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 0, 2, 3, 3));
    // Bottom Left has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 2, 0, 3, 3));
    // Bottom Right has 1 (Centre)
    Assert.assertEquals(1, survivalCalculator.getNeighbourCount(board, 2, 2, 3, 3));
    // Centre has 4
    Assert.assertEquals(4, survivalCalculator.getNeighbourCount(board, 1, 1, 3, 3));
  }
}
