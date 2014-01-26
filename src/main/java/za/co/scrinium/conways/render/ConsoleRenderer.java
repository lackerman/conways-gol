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

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 11:00 AM
 */
public class ConsoleRenderer<T> extends Renderer<T> {

  public static final char ALIVE = '#';
  public static final char DEAD = ' ';

  public ConsoleRenderer(StateClassifier<T> aClassifier, int aWidth, int aHeight) {
    super(aClassifier, aWidth, aHeight);
  }

  public void render(T[][] aBoard) {
    StringBuilder boardBuilder = new StringBuilder();
    buildRow(boardBuilder);
    for (int row = 0; row < getNumRows(); row++) {
      boardBuilder.append("|");
      for (int col = 0; col < getNumColumns(); col++) {
        boardBuilder.append(getStateClassifier().isAlive(aBoard[row][col]) ? ALIVE : DEAD);
      }
      boardBuilder.append("|\n");
    }
    buildRow(boardBuilder);
    System.out.println(boardBuilder.toString());
  }

  private void buildRow(StringBuilder sb) {
    sb.append('+');
    for (int col = 0; col < getNumColumns(); col++) {
      sb.append('-');
    }
    sb.append('+').append("\n");
  }
}
