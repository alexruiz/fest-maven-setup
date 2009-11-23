/*
 * Created on Mar 11, 2008
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
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.swing.JOptionPaneLauncher.pack;
import static org.fest.util.Arrays.array;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Tests for <code>{@link JOptionPaneDriver#requireOptions(JOptionPane, Object[])}</code>.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneDriver_requireOptions_Test extends JOptionPaneDriver_TestCase {

  @Test
  public void should_pass_if_options_are_equal_to_expected() {
    JOptionPane optionPane = messageWithOptions("First", "Second");
    pack(optionPane, title());
    driver.requireOptions(optionPane, array("First", "Second"));
  }

  public void should_fail_if_options_are_not_equal_to_expected() {
    JOptionPane optionPane = messageWithOptions("First", "Second");
    pack(optionPane, title());
    try {
      driver.requireOptions(optionPane, array("Third"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'options'")
                                .contains("expected:<['Third']> but was:<['First', 'Second']>");
    }
  }

}
