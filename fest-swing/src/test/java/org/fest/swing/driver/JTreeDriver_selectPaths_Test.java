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

import static org.fest.swing.test.core.CommonAssertions.*;

import org.junit.Test;

/**
 * Tests for <code>{@link JTreeDriver#selectPaths(javax.swing.JTree, String[])}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_selectPaths_Test extends JTreeDriver_selectCell_TestCase {

  @Test
  public void should_select_cells() {
    clearTreeSelection();
    showWindow();
    updateTreeWithDefaultSelectionModel();
    String[] paths = { "root/branch1/branch1.1", "root/branch1/branch1.2" };
    driver.selectPaths(tree, paths);
    requireSelectedPaths(paths);
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    String[] paths = { "root/branch1/branch1.1", "root/branch1/branch1.2" };
    try {
      driver.selectPaths(tree, paths);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    String[] paths = { "root/branch1/branch1.1", "root/branch1/branch1.2" };
    try {
      driver.selectPaths(tree, paths);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }

}
