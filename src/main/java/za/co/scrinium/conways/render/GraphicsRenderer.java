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

package za.co.scrinium.conways.render;

import za.co.scrinium.conways.classifier.StateClassifier;

import javax.swing.*;
import java.awt.*;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 10:33 AM
 */
public class GraphicsRenderer<T> extends Renderer<T> {

  public static final int SIZE = 10;
  private T[][] board;
  private JPanel panel;

  public GraphicsRenderer(StateClassifier<T> aStateClassifier, int aWidth, int aHeight) {
    super(aStateClassifier, aWidth, aHeight);
    panel = new ConwayPanel();
  }

  public void render(T[][] aBoard) {
    board = aBoard.clone();
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        panel.updateUI();
      }
    });
  }

  public JPanel getPanel() {
    return panel;
  }

  private final class ConwayPanel extends JPanel {

    public void paintComponent(Graphics g) {
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, SIZE * getNumColumns(), SIZE * getNumRows());

      g.setColor(Color.BLACK);
      for (int row = 0; row < getNumRows(); row++) {
        for (int col = 0; col < getNumColumns(); col++) {
          if (getStateClassifier().isAlive(board[row][col])) {
            g.fillRect(col * SIZE, row * SIZE, SIZE, SIZE);
          }
        }
      }
    }
  }
}
