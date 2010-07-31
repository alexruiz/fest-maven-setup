/*
 * Created on May 20, 2009
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
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Strings.*;

import java.awt.Dialog;

import javax.swing.JDialog;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.*;
import org.fest.swing.test.swing.WindowLauncher.DialogToLaunch;

/**
 * Base test case for lookup of <code>{@link Dialog}</code>s in <code>{@link ContainerFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class ContainerFixture_dialog_TestCase extends RobotBasedTestCase {

  ConcreteContainerFixture fixture;
  WindowLauncher window;

  @Override protected void onSetUp() {
    window = WindowLauncher.createNew(getClass());
    fixture = new ConcreteContainerFixture(robot, window);
  }

  final void assertThatDialogWasFound(DialogFixture f) {
    assertThat(f.component()).isInstanceOf(DialogToLaunch.class);
  }

  final void assertThatErrorMessageIsCorrect(WaitTimedOutError e) {
    assertThat(e.getMessage()).contains("Timed out waiting for dialog to be found");
  }

  final void launchDialogNow() {
    launchDialogAfterWaitingFor(0);
  }

  final void launchDialogAfterWaitingFor(int delay) {
    robot.showWindow(window);
    window.dialogLaunchDelay(delay);
    fixture.button("launchDialog").click();
  }

  static class DialogByTitleMatcher extends GenericTypeMatcher<JDialog> {
    private static final String TITLE = "Launched Dialog";

    DialogByTitleMatcher() {
      super(JDialog.class);
    }

    @Override   protected boolean isMatching(JDialog dialog) {
      return TITLE.equals(dialog.getTitle());
    }

    @Override public String toString() {
      return concat("dialog with title ", quote(TITLE));
    }
  }
}
