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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.Regex.regex;

import org.junit.Test;

/**
 * Tests for <code>{@link JComboBoxDriver#requireSelection(javax.swing.JComboBox, java.util.regex.Pattern)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxDriver_requireSelectionByPattern_Test extends JComboBoxDriver_TestCase {

  @Test
  public void should_pass_if_JComboBox_has_matching_selection() {
    selectFirstItem();
    driver.requireSelection(comboBox, regex("firs."));
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_pass_if_JComboBox_does_not_have_matching_selection() {
    selectFirstItem();
    try {
      driver.requireSelection(comboBox, regex("sec.*"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selectedIndex'")
                                .contains("actual value:<'first'> does not match pattern:<'sec.*'>");
    }
  }

  @Test
  public void should_fail_if_JComboBox_does_not_have_any_selection() {
    clearSelection();
    try {
      driver.requireSelection(comboBox, regex("second"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selectedIndex'")
                                .contains("No selection");
    }
  }
}
