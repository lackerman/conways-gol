package io.lacker.property;

public class ApplicationProperties {

  private static final String NUM_ROWS = "numRows";
  private static final String NUM_COLS = "numCols";
  private static final String NUM_ITERATIONS = "numIterations";
  private static final String KNOWN_UNIVERSE = "universe";
  private static final String GUI = "gui";
  private static final String SEPARATOR = "=";

  private final int numIterations;
  private final int numRows;
  private final int numCols;
  private final boolean gui;
  private final String universe;

  public static ApplicationProperties unmarshal(String[] arguments) {
    if (arguments == null || arguments.length < 2 || arguments.length > 4) {
      throw new IllegalArgumentException("Application arguments are invalid");
    }

    int numIterations = 0;
    int numCols = 0;
    int numRows = 0;
    boolean gui = false;
    String universe = null;

    String[] argumentPair;
    for (String argument : arguments) {
      argumentPair = argument.split(SEPARATOR);
      switch (argumentPair[0]) {
        case NUM_ITERATIONS:
          numIterations = getArgumentValue(argumentPair[1]);
          break;
        case NUM_ROWS:
          numRows = getArgumentValue(argumentPair[1]);
          break;
        case NUM_COLS:
          numCols = getArgumentValue(argumentPair[1]);
          break;
        case KNOWN_UNIVERSE:
          universe = argumentPair[1];
          break;
        case GUI:
          gui = true;
          break;
      }
    }
    return new ApplicationProperties(numIterations, universe, numRows, numCols, gui);
  }

  private static int getArgumentValue(String argumentValue) {
    try {
      return Integer.parseInt(argumentValue.trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private ApplicationProperties(int numIterations, String universe, int numRows, int numCols, boolean gui) {
    this.numIterations = numIterations;
    this.numRows = numRows;
    this.numCols = numCols;
    this.universe = universe;
    this.gui = gui;
  }

  public int getNumIterations() {
    return numIterations;
  }

  public int getNumRows() {
    return numRows;
  }

  public int getNumCols() {
    return numCols;
  }

  public String getUniverse() {
    return universe;
  }

  public boolean isGui() {
    return gui;
  }

  public boolean isValid() {
    return numIterations > 0 &&
      ((numRows > 2 && numCols > 2) || (universe != null && !universe.isEmpty()));
  }
}
