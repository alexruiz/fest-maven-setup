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

import static org.fest.swing.test.core.CommonAssertions.*;

import org.junit.Test;

/**
 * Tests for <code>{@link JScrollBarDriver#scrollUnitUp(javax.swing.JScrollBar, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JScrollBarDriver_scrollUnitUpWithTimes_Test extends JScrollBarDriver_TestCase {

  @Test
  public void should_scroll_unit_up() {
    showWindow();
    driver.scrollUnitUp(scrollBar, 6);
    assertThatScrollBarValueIs(24);
  }

  @Test
  public void should_throw_error_if_JScrollBar_is_disabled() {
    disableScrollBar();
    try {
      driver.scrollUnitUp(scrollBar, 6);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JScrollBar_is_not_showing_on_the_screen() {
    try {
      driver.scrollUnitUp(scrollBar, 6);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }
}
