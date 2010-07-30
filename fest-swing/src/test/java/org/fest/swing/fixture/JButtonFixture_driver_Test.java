/*
 * Created on Nov 17, 2009
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

import static org.fest.swing.core.TestRobots.singletonRobotMock;
import static org.fest.swing.test.builder.JButtons.button;

import javax.swing.JButton;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link JButtonFixture#driver(org.fest.swing.driver.AbstractButtonDriver)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JButtonFixture_driver_Test extends EDTSafeTestCase {

  private JButtonFixture fixture;
  private JButton target;

  @Before
  public void setUp() {
    target = button().createNew();
    fixture = new JButtonFixture(singletonRobotMock(), target);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_driver_is_null() {
    fixture.driver(null);
  }
}
