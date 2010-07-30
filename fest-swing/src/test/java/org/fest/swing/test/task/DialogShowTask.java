/*
 * Created on Sep 9, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.test.task;

import static org.fest.swing.timing.Pause.pause;

import java.awt.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.timing.Condition;

/**
 * Understands a task that makes a <code>{@link Dialog}</code> visible. This task is <code>not</code> executed in the
 * event dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class DialogShowTask {

  @RunsInCurrentThread
  public static void packAndShow(Dialog dialog, Dimension preferredSize) {
    dialog.setPreferredSize(preferredSize);
    packAndShow(dialog);
  }

  @RunsInCurrentThread
  public static void packAndShow(final Dialog dialog) {
    dialog.pack();
    dialog.setVisible(true);
    pause(new Condition("Dialog is showing") {
      @Override
      public boolean test() {
        return dialog.isShowing();
      }
    });
  }

  private DialogShowTask() {}
}
