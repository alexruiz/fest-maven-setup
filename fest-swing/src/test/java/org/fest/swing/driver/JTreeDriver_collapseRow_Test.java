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
 * Tests for <code>{@link JTreeDriver#collapseRow(javax.swing.JTree, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_collapseRow_Test extends JTreeDriver_toggleCell_TestCase {

  @Test
  public void should_collapse_cell() {
    showWindow();
    requireRowExpanded(0);
    driver.collapseRow(tree, 0);
    requireRowCollapsed(0);
  }

  @Test
  public void should_not_do_anything_if_cell_already_collapsed() {
    showWindow();
    requireRowCollapsed(5);
    driver.collapseRow(tree, 5);
    requireRowCollapsed(5);
  }

  @Test
  public void should_throw_error_if_JTree_is_disabled() {
    disableTree();
    try {
      driver.collapseRow(tree, 0);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JTree_is_not_showing_on_the_screen() {
    try {
      driver.collapseRow(tree, 0);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }
}
