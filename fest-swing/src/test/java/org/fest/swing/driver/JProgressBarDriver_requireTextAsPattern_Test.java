/*
 * Created on Dec 4, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.Regex.regex;

import javax.swing.JProgressBar;

import org.junit.Test;

/**
 * Tests for <code>{@link JProgressBarDriver#requireText(JProgressBar, java.util.regex.Pattern)}</code>.
 *
 * @author Alex Ruiz
 */
public class JProgressBarDriver_requireTextAsPattern_Test extends JProgressBarDriver_TestCase {

  @Test
  public void should_pass_if_text_matches_pattern() {
    driver.requireText(progressBar, regex("60.*"));
  }

  @Test
  public void should_fail_if_text_does_not_match_pattern() {
    try {
      driver.requireText(progressBar, regex("50%"));
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'text'")
                                .contains("actual value:<'60%'> does not match pattern:<'50%'>");
    }
  }
}
