/*
 * Created on Aug 3, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.Mocks.mockRobot;

import org.fest.swing.core.Robot;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentFixtureValidator#notNullRobot(org.fest.swing.core.Robot)}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentFixtureValidator_notNullRobot_Test {

  @Test
  public void should_return_Robot_if_Robot_is_not_null() {
    Robot robot = mockRobot();
    assertThat(ComponentFixtureValidator.notNullRobot(robot)).isSameAs(robot);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Robot_is_null() {
    ComponentFixtureValidator.notNullRobot(null);
  }

}
