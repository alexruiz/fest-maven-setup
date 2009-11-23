/*
 * Created on Nov 17, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.builder.JButtons.button;
import static org.fest.swing.test.builder.JDialogs.dialog;
import static org.fest.swing.test.builder.JFrames.frame;
import static org.fest.swing.test.core.CommonAssertions.*;

import java.awt.Container;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestMdiWindow;
import org.fest.swing.test.task.ComponentSetEnabledTask;
import org.junit.Test;

/**
 * Tests for <code>{@link ContainerStateValidator#validateCanResize(Container)}</code>.
 *
 * @author Alex Ruiz
 */
public class ContainerStateValidator_validateCanResize_Test extends RobotBasedTestCase {

  @Test
  public void should_pass_if_Frame_is_resizable() {
    JFrame f = frame().createNew();
    robot.showWindow(f);
    validateCanResize(f);
  }

  @Test
  public void should_fail_if_Frame_is_not_resizable() {
    JFrame f = frame().resizable(false).createNew();
    try {
      validateCanResize(f);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotResizableComponent(e);
    }
  }

  @Test
  public void should_fail_if_Frame_is_resizable_but_disabled() {
    JFrame f = frame().createNew();
    disable(f);
    try {
      validateCanResize(f);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_fail_if_Frame_is_resizable_but_not_showing_on_the_screen() {
    JFrame f = frame().createNew();
    try {
      validateCanResize(f);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }

  @Test
  public void should_pass_if_Dialog_is_resizable() {
    JDialog d = dialog().createNew();
    robot.showWindow(d);
    validateCanResize(d);
  }

  @Test
  public void should_fail_if_Dialog_is_not_resizable() {
    JDialog d = dialog().resizable(false).createNew();
    try {
      validateCanResize(d);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotResizableComponent(e);
    }
  }

  @Test
  public void should_fail_if_Dialog_is_resizable_but_disabled() {
    JDialog d = dialog().createNew();
    disable(d);
    try {
      validateCanResize(d);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @RunsInEDT
  private void disable(Container c) {
    ComponentSetEnabledTask.disable(c);
    robot.waitForIdle();
  }

  @Test
  public void should_fail_if_Dialog_is_resizable_but_not_showing_on_the_screen() {
    JDialog d = dialog().createNew();
    try {
      validateCanResize(d);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }

  @Test
  public void should_pass_if_JInternalFrame_is_resizable() {
    TestMdiWindow w = TestMdiWindow.createNewWindow(getClass());
    robot.showWindow(w);
    validateCanResize(w.internalFrame());
  }

  @Test
  public void should_pass_if_JInternalFrame_is_resizableAndDisabled() {
    TestMdiWindow w = TestMdiWindow.createNewWindow(getClass());
    robot.showWindow(w);
    disable(w.internalFrame());
    validateCanResize(w.internalFrame());
  }

  @Test
  public void should_fail_if_JInternalFrame_is_not_resizable() {
    TestMdiWindow w = TestMdiWindow.createNewWindow(getClass());
    robot.showWindow(w);
    JInternalFrame i = w.internalFrame();
    makeNotResizable(i);
    robot.waitForIdle();
    try {
      validateCanResize(i);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotResizableComponent(e);
    }
  }

  @RunsInEDT
  private static void makeNotResizable(final JInternalFrame internalFrame) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        internalFrame.setResizable(false);
      }
    });
  }

  @Test
  public void should_fail_if_JInternalFrame_is_resizable_but_not_showing_on_the_screen() {
    TestMdiWindow w = TestMdiWindow.createNewWindow(getClass());
    try {
      validateCanResize(w.internalFrame());
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }

  @Test
  public void should_fail_if_Component_is_not_Window() {
    try {
      validateCanResize(button().createNew());
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotResizableComponent(e);
    }
  }

  @RunsInEDT
  private static void validateCanResize(final Container c) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        ContainerStateValidator.validateCanResize(c);
      }
    });
  }
}
