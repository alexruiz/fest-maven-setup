/*
 * Created on Feb 24, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.Regex.regex;

import org.fest.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for <code>{@link JListDriver#indexOf(javax.swing.JList, java.util.regex.Pattern)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_indexOfValueAsPattern_Test extends JListDriver_TestCase {

  @Test
  public void should_return_index_of_item_matching_pattern() {
    int index = driver.indexOf(list, regex("thr.*"));
    assertThat(index).isEqualTo(2);
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_throw_error_if_item_matching_given_value_was_not_found() {
    try {
      driver.indexOf(list, regex("fou.*"));
      failWhenExpectingException();
    } catch (LocationUnavailableException expected) {
      assertThat(expected.getMessage()).isEqualTo(
          "Unable to find item matching the pattern 'fou.*' among the JList contents ['one', 'two', 'three']");
    }
  }
}
