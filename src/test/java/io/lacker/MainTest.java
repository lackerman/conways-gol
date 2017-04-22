package io.lacker;

import io.lacker.property.ApplicationProperties;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainTest {

  @Test
  public void testStartGameWithDifferentParams1() throws Exception {
    ApplicationProperties properties = ApplicationProperties.unmarshal(createArguments(10, 20, 1));
    new Main().startGame(properties);
    assertTrue(true);
  }

  @Test
  public void testStartGameWithDifferentParams2() throws Exception {
    ApplicationProperties properties = ApplicationProperties.unmarshal(createArguments(50, 5, 1));
    new Main().startGame(properties);
    assertTrue(true);
  }

  @Test
  public void testStartGameWithDifferentParams3() throws Exception {
    ApplicationProperties properties = ApplicationProperties.unmarshal(createArguments(10, 10, 1));
    new Main().startGame(properties);
    assertTrue(true);
  }

  @Test
  public void testStartGameWithDifferentParams4() throws Exception {
    ApplicationProperties properties = ApplicationProperties.unmarshal(createArguments(1, 1, 2));
    new Main().startGame(properties);
    assertTrue(true);
  }

  private String[] createArguments(int numRows, int numCols, int numIterations) {
    return new String[]{"numRows=" + numRows, "numCols=" + numCols, "numIterations=" + numIterations};
  }
}
