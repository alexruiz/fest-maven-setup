/*
 * Created on Apr 5, 2008
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

import javax.swing.JLabel;

import org.junit.Test;

/**
 * Tests for <code>{@link JLabelDriver#requireText(JLabel, java.util.regex.Pattern)}</code>.
 *
 * @author Yvonne Wang
 */
public class JLabelDriver_requireTextAsPattern_Test extends JLabelDriver_TestCase {

  @Test
  public void should_pass_if_text_matches_pattern() {
    driver.requireText(label, regex("H.*"));
  }

  @Test
  public void should_fail_if_text_does_not_match_pattern() {
    try {
      driver.requireText(label, regex("Bye"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'text'")
                                .contains("actual value:<'Hi'> does not match pattern:<'Bye'>");
    }
  }
}
