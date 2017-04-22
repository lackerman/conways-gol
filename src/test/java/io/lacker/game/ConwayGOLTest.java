package io.lacker.game;

import io.lacker.calculator.SurvivalCalculator;
import io.lacker.classifier.BooleanStateClassifier;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ConwayGOLTest {

  private static final boolean A = true; // ALIVE - shortened to neaten display
  private static final boolean D = false; // DEAD

  private ConwayGOL createGame(Boolean[][] board) {
    return new ConwayGOL(new SurvivalCalculator(new BooleanStateClassifier()), board);
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
  }

  @Test
  public void testEvolutionUnderPopulationDeath1Neighbours() throws Exception {
    Boolean[][] initialBoard = {
      {D, A, D},
      {D, A, D},
      {D, D, D}
    };
    ConwayGOL conwayGOL = createGame(initialBoard);
    assertArrayEquals(initialBoard, conwayGOL.getUniverse());

    conwayGOL.evolve();
    Boolean[][] boardAfterEvolution = {
      {D, D, D},
      {D, D, D},
      {D, D, D}
    };
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
    conwayGOL.evolve();
    conwayGOL.evolve();
    conwayGOL.evolve();
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardAfterEvolution, conwayGOL.getUniverse());
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
    assertArrayEquals(boardStep1, conwayGOL.getUniverse());

    Boolean[][] boardStep2 = {
      {D, D, D, D, D, D},
      {D, D, A, D, D, D},
      {A, D, A, D, D, D},
      {D, A, A, D, D, D},
      {D, D, D, D, D, D},
      {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    assertArrayEquals(boardStep2, conwayGOL.getUniverse());

    Boolean[][] boardStep3 = {
      {D, D, D, D, D, D},
      {D, A, D, D, D, D},
      {D, D, A, A, D, D},
      {D, A, A, D, D, D},
      {D, D, D, D, D, D},
      {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    assertArrayEquals(boardStep3, conwayGOL.getUniverse());

    Boolean[][] boardStep4 = {
      {D, D, D, D, D, D},
      {D, D, A, D, D, D},
      {D, D, D, A, D, D},
      {D, A, A, A, D, D},
      {D, D, D, D, D, D},
      {D, D, D, D, D, D}
    };
    conwayGOL.evolve();
    assertArrayEquals(boardStep4, conwayGOL.getUniverse());
  }
}
