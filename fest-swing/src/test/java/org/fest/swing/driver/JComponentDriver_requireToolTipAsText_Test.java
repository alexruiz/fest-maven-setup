/*
 * Created on Jul 17, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JComponent;

import org.junit.Test;

/**
 * Tests for <code>{@link JComponentDriver#requireToolTip(JComponent, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JComponentDriver_requireToolTipAsText_Test extends JComponentDriver_TestCase {

  @Test
  public void should_pass_if_toolTip_is_equal_to_given_text() {
    driver.requireToolTip(button, "A ToolTip");
  }

  @Test
  public void should_pass_if_toolTip_matches_pattern_in_given_text() {
    driver.requireToolTip(button, "A Tool.*");
  }

  @Test
  public void should_fail_if_toolTip_is_not_equal_to_given_text() {
    try {
      driver.requireToolTip(button, "Hello");
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'toolTipText'")
                                .contains("actual value:<'A ToolTip'> is not equal to or does not match pattern:<'Hello'>");
    }
  }
}
