/*
 * Created on Aug 9, 2009
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

import org.junit.Test;

/**
 * Tests for <code>{@link AbstractButtonDriver#requireNotSelected(javax.swing.AbstractButton)}</code>.
 *
 * @author Alex Ruiz
 */
public class AbstractButtonDriver_requireNotSelected_Test extends AbstractButtonDriver_TestCase {

  @Test
  public void should_pass_if_button_is_not_selected() {
    unselectCheckBox();
    driver.requireNotSelected(checkBox);
  }

  @Test
  public void should_fail_if_button_is_selected() {
    selectCheckBox();
    try {
      driver.requireNotSelected(checkBox);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selected'")
                                .contains("expected:<false> but was:<true>");
    }
  }
}
