package io.lacker;

import io.lacker.calculator.Calculator;
import io.lacker.calculator.SurvivalCalculator;
import io.lacker.classifier.BooleanStateClassifier;
import io.lacker.classifier.StateClassifier;
import io.lacker.game.ConwayGOL;
import io.lacker.property.ApplicationProperties;
import io.lacker.render.ConsoleRenderer;
import io.lacker.render.GraphicsRenderer;
import io.lacker.render.Renderer;

import javax.swing.*;

public class Main {

  private static final String HELP_MESSAGE = "Conway's Game Of Life\n\n" +
    "The program arguments are as follows: \n" +
    "\tnumIterations=X (The number of evolutions. A natural number above 0)\n" +
    "\tnumRows= (The Width of the board. A natural number above 3)\n" +
    "\tnumCols= (The Height of the board. A natural number above 3)\n" +
    "\tuniverse= (A predefined universe. Currently only glidergun)\n" +
    "\tgui (Whether or not to use the GUI)\n\n" +
    "\tExample: java -jar \"target/ConwaysGOL-1.0.jar\" numIterations=5 numCols=10 numRows=10 \n";

  public static void main(String[] args) {
    try {
      ApplicationProperties properties = null;
      try {
        properties = ApplicationProperties.unmarshal(args);
      } catch (IllegalArgumentException e) {
        exitAndShowHelp();
      }
      if (null == properties || !properties.isValid()) {
        exitAndShowHelp();
      } else {
        new Main().startGame(properties);
      }
    } catch (Exception e) {
      System.err.println("Fatal Unhandled exception: " + e.getMessage());
      e.printStackTrace();
      exitAndShowHelp();
    }
  }

  private static void exitAndShowHelp() {
    System.out.println(HELP_MESSAGE);
    System.exit(1);
  }

  private Renderer<Boolean> setupGuiRenderer(StateClassifier<Boolean> classifier, int numRows, int numCols) {
    final JFrame frame = new JFrame("Conway's Game of Life");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setSize(numCols * 10, numRows * 10);

    GraphicsRenderer<Boolean> renderer = new GraphicsRenderer<>(classifier, numRows, numCols, () -> {
      frame.setVisible(false);
      frame.dispose();
    });

    frame.add(renderer.getPanel());
    return renderer;
  }

  private Renderer<Boolean> setupConsoleRenderer(StateClassifier<Boolean> classifier, int numRows, int numCols) {
    return new ConsoleRenderer<>(classifier, numRows, numCols);
  }

  void startGame(ApplicationProperties properties) {
    StateClassifier<Boolean> classifier = new BooleanStateClassifier();
    Calculator<Boolean> calculator = new SurvivalCalculator(classifier);

    ConwayGOL conway;
    if (properties.getUniverse() == null) {
      conway = new ConwayGOL(calculator, properties.getNumRows(), properties.getNumCols());
    } else {
      conway = new ConwayGOL(calculator, Loader.loadUniverse("/" + properties.getUniverse() + ".txt"));
    }

    Renderer<Boolean> renderer;
    if (properties.isGui()) {
      renderer = setupGuiRenderer(classifier, conway.getNumRows(), conway.getNumCols());
    } else {
      renderer = setupConsoleRenderer(classifier, conway.getNumRows(), conway.getNumCols());
    }

    for (int evolutionCount = 0; evolutionCount < properties.getNumIterations(); evolutionCount++) {
      conway.evolve();
      renderer.render(conway.getUniverse());
      try {
        // Sleep timer to allow the person viewing the Game time to see the changes
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    renderer.shutdown();
    System.out.println("Game Over!!!");
  }
}
