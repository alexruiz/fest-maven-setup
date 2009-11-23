/*
 * Created on Jul 19, 2009
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

import static org.fest.swing.query.ComponentHasFocusQuery.hasFocus;
import static org.fest.swing.test.core.CommonAssertions.*;
import static org.fest.swing.timing.Pause.pause;

import org.fest.swing.timing.Condition;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentDriver#focus(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentDriver_focus_Test extends ComponentDriver_TestCase {

  @Test
  public void should_give_focus_to_Component() {
    showWindow();
    driver.focus(window.button);
    pause(new Condition("Component has focus") {
      public boolean test() {
        return hasFocus(window.button);
      }
    });
  }

  @Test
  public void should_throw_error_if_Component_is_disabled() {
    disableButton();
    try {
      driver.focus(window.button);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
    assertThatButtonDoesNotHaveFocus();
  }

  @Test
  public void should_throw_error_if_Component_is_not_showing_on_the_screen() {
    try {
      driver.focus(window.button);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
    assertThatButtonDoesNotHaveFocus();
  }
}
