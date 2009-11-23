/*
 * Created on Aug 23, 2008
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

import static org.fest.swing.driver.JTreeChildOfPathCountQuery.childCount;
import static org.fest.util.Strings.concat;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.timing.Condition;

/**
 * Understands a condition that verifies that the children of a node in a <code>{@link JTree}</code> are displayed.
 *
 * @author Alex Ruiz
 */
class JTreeChildrenShowUpCondition extends Condition {

  private JTree tree;
  private TreePath path;

  static JTreeChildrenShowUpCondition untilChildrenShowUp(JTree tree, TreePath path) {
    return new JTreeChildrenShowUpCondition(tree, path);
  }

  private JTreeChildrenShowUpCondition(JTree tree, TreePath path) {
    super(concat(path.toString(), " to show"));
    this.tree = tree;
    this.path = path;
  }

  @RunsInEDT
  public boolean test() {
    return childCount(tree, path) != 0;
  }

  @Override protected void done() {
    tree = null;
    path = null;
  }
}