/*
 * Created on Jul 27, 2009
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
package org.fest.swing.test.core;

import org.fest.swing.core.*;
import org.junit.*;

/**
 * Base test case that uses a <code>{@link Robot}</code>. It also ensures that Swing components are created and accessed
 * in the EDT.
 *
 * @author Alex Ruiz
 */
public class RobotBasedTestCase extends EDTSafeTestCase {

  public Robot robot;

  @Before public final void setUp() {
    robot = BasicRobot.robotWithNewAwtHierarchy();
    onSetUp();
  }

  protected void onSetUp() {}

  @After public final void tearDown() {
    try {
      onTearDown();
    } finally {
      robot.cleanUp();
    }
  }

  protected void onTearDown() {}
}
