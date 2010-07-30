/*
 * Created on May 1, 2009
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
package org.fest.swing.testing;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.core.*;
import org.fest.swing.hierarchy.ExistingHierarchy;
import org.junit.*;

/**
 * Tests for <code>{@link FestSwingTestCaseTemplate#setUpRobot()}</code> and
 * <code>{@link FestSwingTestCaseTemplate#cleanUp()}</code>.
 *
 * @author Alex Ruiz
 */
public class FestSwingTestCaseTemplate_setUpRobot_cleanUp_Test {

  private TestCase testCase;

  @Before public void setUp() {
    testCase = new TestCase();
  }

  @Test
  public void should_create_Robot_on_setUp_and_inactivate_it_on_cleanUp() {
    testCase.setUpRobot();
    Robot robot = testCase.robot();
    assertThat(robot).isInstanceOf(BasicRobot.class);
    assertThat(robot.hierarchy()).isInstanceOf(ExistingHierarchy.class);
    assertThat(robot.isActive()).isTrue();
    testCase.cleanUp();
    assertThat(robot.isActive()).isFalse();
  }

  private static class TestCase extends FestSwingTestCaseTemplate {}
}
