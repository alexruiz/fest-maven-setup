/*
 * Created on Jun 18, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FailureMessages.unexpectedEqualOrLess;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link ByteAssert#isGreaterThan(byte)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ByteAssert_isGreaterThan_Test implements Assert_isGreaterThan_TestCase {

  private static Byte actual;
  private static byte greaterThanActual;

  private ByteAssert assertObject;

  @BeforeClass
  public static void setUpOnce() {
    actual = 6;
    greaterThanActual = 8;
  }

  @Before
  public void setUp() {
    assertObject = new ByteAssert(actual);
  }

  @Test
  public void should_pass_if_actual_is_greater_than_expected() {
    assertObject.isGreaterThan((byte)2);
  }

  @Test
  public void should_fail_if_actual_is_equal_to_expected() {
    expectAssertionError(unexpectedEqualOrLess(actual, actual)).on(new CodeToTest() {
      public void run() {
        assertObject.isGreaterThan(actual);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError(unexpectedEqualOrLess("A Test", actual, actual)).on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .isGreaterThan(actual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .isGreaterThan(actual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .overridingErrorMessage("My custom message")
                    .isGreaterThan(actual);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_less_than_expected() {
    expectAssertionError(unexpectedEqualOrLess(actual, greaterThanActual)).on(new CodeToTest() {
      public void run() {
        assertObject.isGreaterThan(greaterThanActual);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError(unexpectedEqualOrLess("A Test", actual, greaterThanActual)).on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .isGreaterThan(greaterThanActual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .isGreaterThan(greaterThanActual);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_less_than_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .overridingErrorMessage("My custom message")
                    .isGreaterThan(greaterThanActual);
      }
    });
  }
}
