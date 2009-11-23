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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

/**
 * Tests for <code>{@link JTreeDriver#drag(javax.swing.JTree, String)}</code> and
 * <code>{@link JTreeDriver#drop(javax.swing.JTree, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_dragPath_dropPath_Test extends JTreeDriver_dragAndDrop_TestCase {

  @Test
  public void should_drag_and_drop() {
    showWindow();
    driver.drag(tree, "root/branch1/branch1.1");
    driver.drop(dropTree, "root");
    // the first child in drag-source tree is no longer branch1.1 but branch1.2
    DefaultMutableTreeNode branch1 = firstChildInRoot();
    assertThat(childCountOf(branch1)).isEqualTo(1);
    assertThat(textOf(firstChildOf(branch1))).isEqualTo("branch1.2");
    // the only child in the root of the drop-target tree is now branch1.1
    DefaultMutableTreeNode root = rootOf(dropTree);
    assertThat(childCountOf(root)).isEqualTo(1);
    assertThat(textOf(firstChildOf(root))).isEqualTo("branch1.1");
  }
}
