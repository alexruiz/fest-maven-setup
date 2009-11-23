/*
 * Created on Aug 29, 2008
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
package org.fest.swing.test.task;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sets the <code>{@link JPopupMenu}</code> for a <code>{@link JComponent}</code>. This task is
 * executed in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
public final class ComponentSetPopupMenuTask {

  @RunsInEDT
  public static void setPopupMenu(final JComponent c, final JPopupMenu popupMenu) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        c.setComponentPopupMenu(popupMenu);
      }
    });
  }

  @RunsInEDT
  public static JPopupMenu createAndSetPopupMenu(final JComponent c, final String...items) {
    return execute(new GuiQuery<JPopupMenu>() {
      protected JPopupMenu executeInEDT() {
        JPopupMenu popupMenu = new JPopupMenu();
        for (String item : items)
          popupMenu.add(new JMenuItem(item));
        c.setComponentPopupMenu(popupMenu);
        return popupMenu;
      }
    });
  }

  private ComponentSetPopupMenuTask() {}
}