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
import static org.fest.swing.driver.JTreeSetEditableTask.setEditable;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.junit.Test;

/**
 * Tests for <code>{@link JTreeDriver#requireNotEditable(javax.swing.JTree)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_requireNotEditable_Test extends JTreeDriver_TestCase {

  @Test
  public void should_pass_if_JTree_is_not_editable() {
    setEditable(tree, false);
    robot.waitForIdle();
    driver.requireNotEditable(tree);
  }

  @Test
  public void should_fail_if_JTree_is_editable() {
    setEditable(tree, true);
    robot.waitForIdle();
    try {
      driver.requireNotEditable(tree);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'editable'")
                                .contains("expected:<false> but was:<true>");
    }
  }
}
