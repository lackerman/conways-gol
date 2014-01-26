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

package za.co.scrinium.conways.property;

/**
 * Created By (Your Name)
 * Created On 2013/12/12 8:56 AM
 */
public class ApplicationProperties {

  private static final String ARGUMENT_NUM_ROWS = "numRows";
  private static final String ARGUMENT_NUM_COLS = "numCols";
  private static final String ARGUMENT_NUM_ITERATIONS = "numIterations";
  private static final String SEPARATOR = "=";

  private final int numRows;
  private final int numCols;
  private final int numIterations;

  public ApplicationProperties(int aNumRows, int aNumCols, int aNumIterations) {
    numRows = aNumRows;
    numCols = aNumCols;
    numIterations = aNumIterations;
  }

  public int getNumRows() {
    return numRows;
  }

  public int getNumCols() {
    return numCols;
  }

  public int getNumIterations() {
    return numIterations;
  }

  public static ApplicationProperties unmarshall(String[] arguments) {
    if (arguments == null || arguments.length != 3) {
      throw new IllegalArgumentException("Application arguments are invalid");
    }
    int numCols = -1;
    int numRows = -1;
    int numIterations = -1;
    for (String argument : arguments) {
      if (argument.contains(ARGUMENT_NUM_ROWS)) {
        numRows = getArgumentValue(argument);
      } else {
        if (argument.contains(ARGUMENT_NUM_COLS)) {
          numCols = getArgumentValue(argument);
        } else {
          if (argument.contains(ARGUMENT_NUM_ITERATIONS)) {
            numIterations = getArgumentValue(argument);
          }
        }
      }
    }
    return new ApplicationProperties(numRows, numCols, numIterations);
  }

  public boolean isValid() {
    return !(numCols < 3 || numRows < 3 || numIterations < 1);
  }

  private static int getArgumentValue(String aArgument) {
    try {
      return Integer.parseInt(aArgument.split(SEPARATOR)[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
