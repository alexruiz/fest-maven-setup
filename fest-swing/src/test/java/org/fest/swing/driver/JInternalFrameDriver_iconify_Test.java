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
import static org.fest.swing.driver.JInternalFrameIconQuery.isIconified;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.*;

import javax.swing.JInternalFrame;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Tests for <code>{@link JInternalFrameDriver#iconify(JInternalFrame)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JInternalFrameDriver_iconify_Test extends JInternalFrameDriver_TestCase {

  @Test
  public void should_not_iconify_already_iconified_JInternalFrame() {
    showWindow();
    iconify();
    driver.iconify(internalFrame);
    assertThat(isIconified(internalFrame)).isTrue();
  }

  @Test
  public void should_throw_error_if_JInternalFrame_is_not_showing_on_the_screen() {
    try {
      driver.iconify(internalFrame);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }

  @Test
  public void should_throw_error_when_iconifying_not_iconfiable_JInternalFrame() {
    makeNotIconfiable();
    showWindow();
    try {
      driver.iconify(internalFrame);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).contains("The JInternalFrame <")
                                .contains("> is not iconifiable");
    }
  }

  @RunsInEDT
  private void makeNotIconfiable() {
    setIconifiable(internalFrame, false);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void setIconifiable(final JInternalFrame internalFrame, final boolean iconfiable) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        internalFrame.setIconifiable(iconfiable);
      }
    });
  }
}
