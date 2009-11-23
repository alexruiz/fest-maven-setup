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

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Tests for <code>{@link JOptionPaneDriver#requireMessage(JOptionPane, Object)}</code>.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneDriver_requireMessage_Test extends JOptionPaneDriver_TestCase {

  @Test
  public void should_pass_if_message_is_equal_to_expected() {
    JOptionPane optionPane = messageWithValue("Leia");
    pack(optionPane, title());
    driver.requireMessage(optionPane, "Leia");
  }

  @Test
  public void should_pass_if_message_matches_pattern() {
    JOptionPane optionPane = messageWithValue("Leia");
    pack(optionPane, title());
    driver.requireMessage(optionPane, "Le.*");
  }

  @Test
  public void should_pass_if_non_String_message_is_equal_to_expected() {
    JOptionPane optionPane = messageWithValue(new Person("Leia"));
    pack(optionPane, title());
    driver.requireMessage(optionPane, new Person("Leia"));
  }

  @Test
  public void should_pass_if_non_String_message_matches_pattern() {
    JOptionPane optionPane = messageWithValue(new Person("Leia"));
    pack(optionPane, title());
    driver.requireMessage(optionPane, "Le.*");
  }

  @Test
  public void should_fail_is_message_does_match_expected() {
    JOptionPane optionPane = messageWithValue("Palpatine");
    pack(optionPane, title());
    try {
      driver.requireMessage(optionPane, "Anakin");
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'message'")
                                .contains("actual value:<'Palpatine'> is not equal to or does not match pattern:<'Anakin'>");
    }
  }
}
