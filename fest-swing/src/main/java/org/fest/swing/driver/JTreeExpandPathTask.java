/*
 * Created on Aug 19, 2008
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

import static org.fest.swing.driver.JTreeAddRootIfInvisibleTask.addRootIfInvisible;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that ensures that the node identified by the specified path is expanded and viewable. This action
 * is executed in the event dispatch thread.
 * @see JTree#isExpanded(TreePath)
 * @see JTree#expandPath(TreePath)
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
final class JTreeExpandPathTask {

  @RunsInEDT
  static void expandTreePath(final JTree tree, final TreePath path) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        TreePath realPath = addRootIfInvisible(tree, path);
        if (!tree.isExpanded(path)) tree.expandPath(realPath);
      }
    });
  }

  private JTreeExpandPathTask() {}
}