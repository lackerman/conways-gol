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

import org.junit.Assert;
import org.junit.Test;
import za.co.scrinium.conways.calculator.SurvivalCalculator;
import za.co.scrinium.conways.classifier.BooleanStateClassifier;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 11:10 AM
 */
public class ConwayGOLTest {

  public static final boolean A = true;
  public static final boolean D = false;

  private ConwayGOL createGame(Boolean[][] aBoard) {
    return new ConwayGOL(new SurvivalCalculator<Boolean>(new BooleanStateClassifier()), aBoard);
  }

  @Test
  public void testEvolutionUnderPopulationDeath0Neighbours() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D},
            {D, A, D},
            {D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, D, D},
            {D, D, D},
            {D, D, D}
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testEvolutionUnderPopulationDeath1Neighbours() throws Exception {
    Boolean[][] initialBoard = {
            {D, A, D},
            {D, A, D},
            {D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);
    Assert.assertArrayEquals(initialBoard, conwayGOL.getUniverse());

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, D, D},
            {D, D, D},
            {D, D, D}
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testEvolutionCenterSurviveSidesDie() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D},
            {A, A, A},
            {D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, A, D},
            {D, A, D},
            {D, A, D}
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testEvolutionOverPopulationDeath() throws Exception {
    Boolean[][] initialBoard = {
            {A, D, A},
            {D, A, D},
            {A, D, A}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, A, D},
            {A, D, A},
            {D, A, D}
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testStillLifeBlock() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D},
            {D, A, A, D},
            {D, A, A, D},
            {D, D, D, D},
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, D, D, D},
            {D, A, A, D},
            {D, A, A, D},
            {D, D, D, D},
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testStillLifeBeehive() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D, D, D},
            {D, D, A, A, D, D},
            {D, A, D, D, A, D},
            {D, D, A, A, D, D},
            {D, D, D, D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, D, D, D, D, D},
            {D, D, A, A, D, D},
            {D, A, D, D, A, D},
            {D, D, A, A, D, D},
            {D, D, D, D, D, D}
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testStillLifeBoat() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D, D},
            {D, A, A, D, D},
            {D, A, D, A, D},
            {D, D, A, D, D},
            {D, D, D, D, D},
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
            {D, D, D, D, D},
            {D, A, A, D, D},
            {D, A, D, A, D},
            {D, D, A, D, D},
            {D, D, D, D, D},
    };
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testOscillatorsBlinker() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D, D},
            {D, D, A, D, D},
            {D, D, A, D, D},
            {D, D, A, D, D},
            {D, D, D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    Boolean[][] boardAfterEvolution = {
            {D, D, D, D, D},
            {D, D, D, D, D},
            {D, A, A, A, D},
            {D, D, D, D, D},
            {D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testOscillatorsToad() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D, D, D},
            {D, D, D, A, D, D},
            {D, A, D, D, A, D},
            {D, A, D, D, A, D},
            {D, D, A, D, D, D},
            {D, D, D, D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    Boolean[][] boardAfterEvolution = {
            {D, D, D, D, D, D},
            {D, D, D, D, D, D},
            {D, D, A, A, A, D},
            {D, A, A, A, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D},
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testOscillatorsBeacon() throws Exception {
    Boolean[][] initialBoard = {
            {D, D, D, D, D, D},
            {D, A, A, D, D, D},
            {D, A, D, D, D, D},
            {D, D, D, D, A, D},
            {D, D, D, A, A, D},
            {D, D, D, D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    Boolean[][] boardAfterEvolution = {
            {D, D, D, D, D, D},
            {D, A, A, D, D, D},
            {D, A, A, D, D, D},
            {D, D, D, A, A, D},
            {D, D, D, A, A, D},
            {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testGlider() throws Exception {
    Boolean[][] initialBoard = {
            {D, A, D, D, D, D},
            {D, D, A, D, D, D},
            {A, A, A, D, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);

    Boolean[][] boardStep1 = {
            {D, D, D, D, D, D},
            {A, D, A, D, D, D},
            {D, A, A, D, D, D},
            {D, A, D, D, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardStep1, conwayGOL.getUniverse());

    Boolean[][] boardStep2 = {
            {D, D, D, D, D, D},
            {D, D, A, D, D, D},
            {A, D, A, D, D, D},
            {D, A, A, D, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardStep2, conwayGOL.getUniverse());

    Boolean[][] boardStep3 = {
            {D, D, D, D, D, D},
            {D, A, D, D, D, D},
            {D, D, A, A, D, D},
            {D, A, A, D, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardStep3, conwayGOL.getUniverse());

    Boolean[][] boardStep4 = {
            {D, D, D, D, D, D},
            {D, D, A, D, D, D},
            {D, D, D, A, D, D},
            {D, A, A, A, D, D},
            {D, D, D, D, D, D},
            {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    Assert.assertArrayEquals(boardStep4, conwayGOL.getUniverse());
  }

  private void printBoard(Boolean[][] aUniverse) {
    for (Boolean[] anAUniverse : aUniverse) {
      for (Boolean anAnAUniverse : anAUniverse) {
        System.out.print(anAnAUniverse ? "#" : "*");
      }
      System.out.println();
    }
    System.out.println();
  }
}
