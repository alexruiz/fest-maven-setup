/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.booleanArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link BooleanArrayAssert#isNotEqualTo(boolean[])}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BooleanArrayAssert_isNotEqualTo_Test implements Assert_isNotEqualTo_TestCase {

  @Test
  public void should_pass_if_actual_and_expected_are_not_equal() {
    new BooleanArrayAssert(true).isNotEqualTo(booleanArray(false));
  }

  @Test
  public void should_fail_if_actual_and_expected_are_equal() {
    expectAssertionError("actual value:<[true]> should not be equal to:<[true]>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(true).isNotEqualTo(booleanArray(true));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("[A Test] actual value:<[true]> should not be equal to:<[true]>").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(true).as("A Test")
                                    .isNotEqualTo(booleanArray(true));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(true).overridingErrorMessage("My custom message")
                                    .isNotEqualTo(booleanArray(true));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new BooleanArrayAssert(true).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .isNotEqualTo(booleanArray(true));
      }
    });
  }
}
