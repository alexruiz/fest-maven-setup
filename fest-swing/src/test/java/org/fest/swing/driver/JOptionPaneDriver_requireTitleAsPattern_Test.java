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
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.swing.test.swing.JOptionPaneLauncher.pack;
import static org.fest.util.Strings.concat;

import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Tests for <code>{@link JOptionPaneDriver#requireTitle(JOptionPane, java.util.regex.Pattern)}</code>.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneDriver_requireTitleAsPattern_Test extends JOptionPaneDriver_TestCase {

  @Test
  public void should_pass_if_title_matches_pattern() {
    JOptionPane optionPane = informationMessage();
    pack(optionPane, title());
    driver.requireTitle(optionPane, regex("JOptionP.*"));
  }

  @Test
  public void should_fail_if_title_does_not_match_pattern() {
    JOptionPane optionPane = informationMessage();
    pack(optionPane, title());
    try {
      driver.requireTitle(optionPane, regex("Yoda"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'title'")
                                .contains(concat("actual value:<'", title(), "'> does not match pattern:<'Yoda'>"));
    }
  }
}
