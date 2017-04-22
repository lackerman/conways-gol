package io.lacker.property;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationPropertiesTest {

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalNullArguments() throws Exception {
    ApplicationProperties.unmarshal(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalLessThan2() throws Exception {
    ApplicationProperties.unmarshal(new String[]{"1"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalGreaterThan4() throws Exception {
    ApplicationProperties.unmarshal(new String[]{"1", "2", "3", "4", "5"});
  }

  @Test
  public void testUnmarshal2SuccessfulAndInvalid() throws Exception {
    ApplicationProperties props = ApplicationProperties.unmarshal(new String[]{"1", "2"});
    assertFalse(props.isValid());
  }

  @Test
  public void testUnmarshal3SuccessfulAndInvalid() throws Exception {
    ApplicationProperties props = ApplicationProperties.unmarshal(new String[]{"1", "2", "3"});
    assertFalse(props.isValid());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalSuccessfulAndInvalidColumnValues() throws Exception {
    ApplicationProperties.unmarshal(new String[]{"numRows=6", "numCols=XXX", "numIterations=25"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalSuccessfulAndInvalidRowValues() throws Exception {
    ApplicationProperties.unmarshal(new String[]{"numRows=XXX", "numCols=5", "numIterations=25"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshalSuccessfulAndInvalidIterationValues() throws Exception {
    ApplicationProperties.unmarshal(new String[]{"numRows=6", "numCols=XXX", "numIterations=XXX"});
  }

  @Test
  public void testUnmarshalSuccessfulAndInvalidValuesWithWhiteSpace() throws Exception {
    ApplicationProperties props = ApplicationProperties.unmarshal(
      new String[]{"numRows = 6", "numCols = 5", "numIterations = 6"});
    assertFalse(props.isValid());
  }

  @Test
  public void testUnmarshalSuccessfulAndValid() {
    ApplicationProperties props = ApplicationProperties.unmarshal(
      new String[]{"numRows=6", "numCols=10", "numIterations=25"});

    assertTrue(props.isValid());
    assertEquals(6, props.getNumRows());
    assertEquals(10, props.getNumCols());
    assertEquals(25, props.getNumIterations());
  }

  @Test
  public void testUnmarshalSuccessfulAndValidForUniverse() {
    ApplicationProperties props = ApplicationProperties.unmarshal(
      new String[]{"universe=test", "numIterations=25", "gui"});

    assertTrue(props.isValid());
    assertTrue(props.isGui());
    assertEquals("test", props.getUniverse());
    assertEquals(25, props.getNumIterations());
  }
}
