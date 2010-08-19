/*
 * Created on Dec 26, 2009
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

import javax.swing.JTree;

import org.fest.swing.core.*;
import org.fest.swing.exception.*;

/**
 * Understands functional testing of single nodes, referenced by their paths, in <code>{@link JTree}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class JTreePathFixture implements JTreeNodeFixture {

  private final String path;
  private final JTreeFixture tree;

  /**
   * Creates a new </code>{@link JTreePathFixture}</code>.
   * @param tree handles the {@code JTree} containing the node with the given path.
   * @param path the given path.
   */
  protected JTreePathFixture(JTreeFixture tree, String path) {
    this.tree = tree;
    this.path = path;
  }

  /**
   * Simulates a user expanding this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws ActionFailedException if this method fails to expand the path.
   * @since 1.2
   */
  public JTreePathFixture expand() {
    tree.expandPath(path);
    return this;
  }

  /**
   * Simulates a user collapsing this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws ActionFailedException if this method fails to collapse the path.
   * @since 1.2
   */
  public JTreePathFixture collapse() {
    tree.collapsePath(path);
    return this;
  }

  /**
   * Selects the this fixture's tree node, expanding parent nodes if necessary. This method will not click the node if
   * it is already selected.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture select() {
    tree.selectPath(path);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture click() {
    tree.clickPath(path);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given button is {@code null}.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture click(MouseButton button) {
    tree.clickPath(path, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's tree node.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture click(MouseClickInfo mouseClickInfo) {
    tree.clickPath(path, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture doubleClick() {
    tree.doubleClickPath(path);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture rightClick() {
    tree.rightClickPath(path);
    return this;
  }

  /**
   * Simulates a user dragging this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   */
  public JTreePathFixture drag() {
    tree.drag(path);
    return this;
  }

  /**
   * Simulates a user dropping relative to this fixture's tree node.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JTreePathFixture drop() {
    tree.drop(path);
    return this;
  }

  /**
   * Shows a pop-up menu using this fixture's tree node as the invoker of the pop-up menu.
   * @return a fixture that handles functional testing of the displayed pop-up menu.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return tree.showPopupMenuAt(path);
  }

  /**
   * Returns the {@code String} representation of this fixture's tree node.
   * @return the {@code String} representation of this fixture's tree node.
   */
  public String value() {
    return tree.valueAt(path);
  }

  /**
   * Returns the path of this fixture's node.
   * @return the path of this fixture's node.
   */
  public String path() {
    return path;
  }
}
