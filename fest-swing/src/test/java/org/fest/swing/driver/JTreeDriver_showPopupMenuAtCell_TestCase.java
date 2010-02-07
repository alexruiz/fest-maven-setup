/*
 * Created on Jul 16, 2009
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Base test case in <code>{@link JTreeDriver}</code> related to showing a pop-up menu at any cell in a
 * <code>{@link JTree}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class JTreeDriver_showPopupMenuAtCell_TestCase extends JTreeDriver_TestCase {

  private JPopupMenu popupMenu;

  @RunsInEDT
  @Override void extraSetUp() {
    execute(new GuiTask() {
      protected void executeInEDT() {
        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Hello"));
        tree.addMouseListener(new Listener(popupMenu));
      }
    });
  }

  final JPopupMenu popupMenu() { return popupMenu; }

  static class Listener extends MouseAdapter {
    private final JPopupMenu popupMenu;

    Listener(JPopupMenu popupMenu) {
      this.popupMenu = popupMenu;
    }

    @Override public void mouseReleased(MouseEvent e) {
      if (!e.isPopupTrigger()) return;
      Component c = e.getComponent();
      if (!(c instanceof JTree)) return;
      JTree tree = (JTree)c;
      int x = e.getX();
      int y = e.getY();
      int row = tree.getRowForLocation(x, y);
      if (row >= 0) popupMenu.show(tree, x, y);
    }
  }
}
