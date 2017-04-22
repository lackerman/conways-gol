package io.lacker.classifier;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BooleanStateClassifierTest {

  @Test
  public void testIsAlive() throws Exception {
    assertTrue(new BooleanStateClassifier().isAlive(true));
  }

  @Test
  public void testIsDead() throws Exception {
    assertFalse(new BooleanStateClassifier().isAlive(false));
  }
}
