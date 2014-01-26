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

import org.junit.Assert;
import org.junit.Test;

/**
 * Created By Leo Ackerman
 * Created On 2013/12/12 9:03 AM
 */
public class ApplicationPropertiesTest {

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallNullArguments() throws Exception {
    ApplicationProperties.unmarshall(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallLessThan3() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"A", "B"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallGreaterThan3() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"A", "B", "C", "D"});
  }

  @Test
  public void testUnmarshallSuccessfulAndInvalid() throws Exception {
    ApplicationProperties props = ApplicationProperties.unmarshall(new String[]{"A", "B", "B"});
    Assert.assertTrue(true);
    Assert.assertFalse(props.isValid());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallSuccessfulAndInvalidColumnValues() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"numRows=6", "numCols=XXX", "numIterations=25"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallSuccessfulAndInvalidRowValues() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"numRows=XXX", "numCols=5", "numIterations=25"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallSuccessfulAndInvalidIterationValues() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"numRows=6", "numCols=XXX", "numIterations=XXX"});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnmarshallSuccessfulAndInvalidValuesWithWhiteSpace() throws Exception {
    ApplicationProperties.unmarshall(new String[]{"numRows = 6", "numCols = 5", "numIterations = 6"});
  }

  @Test
  public void testUnmarshallSuccessfulAndValid() throws Exception {
    ApplicationProperties props = ApplicationProperties
            .unmarshall(new String[]{"numRows=6", "numCols=10", "numIterations=25"});
    Assert.assertTrue(true);
    Assert.assertTrue(props.isValid());
    Assert.assertEquals(6, props.getNumRows());
    Assert.assertEquals(10, props.getNumCols());
    Assert.assertEquals(25, props.getNumIterations());
  }
}
