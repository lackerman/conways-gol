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

package za.co.scrinium.conways;

import za.co.scrinium.conways.calculator.SurvivalCalculator;
import za.co.scrinium.conways.classifier.BooleanStateClassifier;
import za.co.scrinium.conways.classifier.StateClassifier;
import za.co.scrinium.conways.game.ConwayGOL;
import za.co.scrinium.conways.property.ApplicationProperties;
import za.co.scrinium.conways.render.ConsoleRenderer;
import za.co.scrinium.conways.render.GraphicsRenderer;
import za.co.scrinium.conways.render.Renderer;

import javax.swing.*;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/10 9:43 PM
 */
public class Main {

  public static final String HELP_MESSAGE;

  static {
    StringBuilder builder = new StringBuilder();
    builder.append("DSTV Interview Test: Conway's Game Of Life\n\n");
    builder.append("The program arguments are as follows: \n");
    builder.append("\tnumIterations=X (X is an natural number above 0)\n");
    builder.append("\tnumRows= (The Width of the board. A natural number above 3)\n");
    builder.append("\tnumCols= (The Height of the board. A natural number above 3)\n\n");
    builder.append("\tExample: java -jar \"target/ConwaysGOL-1.0.jar\" numIterations=5 numCols=10 numRows=10 \n");
    HELP_MESSAGE = builder.toString();
  }

  public static void main(String[] args) {
    try {
      ApplicationProperties properties = null;
      try {
        properties = ApplicationProperties.unmarshall(args);
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

  private JFrame initialiseUI(GraphicsRenderer<Boolean> aRenderer, int aWidth, int aHeight) {
    JFrame frame = new JFrame("Conway's Game of Life");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setSize(aWidth, aHeight);
    frame.add(aRenderer.getPanel());
    return frame;
  }

  public void startGame(ApplicationProperties aProperties) {
    StateClassifier<Boolean> classifier = new BooleanStateClassifier();
    SurvivalCalculator<Boolean> calculator = new SurvivalCalculator<Boolean>(classifier);

    int numRows = aProperties.getNumRows();
    int numCols = aProperties.getNumCols();

    GraphicsRenderer<Boolean> gRenderer = new GraphicsRenderer<Boolean>(classifier, numRows, numCols);
    JFrame frame = initialiseUI(gRenderer, numCols * 15, numRows * 15);

    Renderer<Boolean> cRenderer = new ConsoleRenderer<Boolean>(classifier, numRows, numCols);
    // Create blank universe
    ConwayGOL conway = new ConwayGOL(calculator, numRows, numCols);
    for (int evolutionCount = 0; evolutionCount < aProperties.getNumIterations(); evolutionCount++) {
      conway.evolve();
      cRenderer.render(conway.getUniverse());
      gRenderer.render(conway.getUniverse());
      try {
        // Sleep timer to allow the person viewing the Game time to see the changes
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Game Over!!!");
    frame.setVisible(false);
    frame.dispose();
  }

}
