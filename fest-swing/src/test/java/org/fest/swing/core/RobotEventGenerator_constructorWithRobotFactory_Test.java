/*
 * Created on Aug 6, 2009
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
package org.fest.swing.core;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.Mocks.mockRobotFactory;

import java.awt.AWTException;
import java.awt.Robot;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.util.RobotFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link RobotEventGenerator#RobotEventGenerator(org.fest.swing.util.RobotFactory, Settings)}</code>.
 *
 * @author Alex Ruiz
 */
public class RobotEventGenerator_constructorWithRobotFactory_Test {

  private RobotFactory robotFactory;

  @Before
  public void setUp() {
    robotFactory = mockRobotFactory();
  }

  @Test
  public void should_use_RobotFactory_to_create_AWTRobot() {
    final Robot robot = createMock(Robot.class);
    new EasyMockTemplate(robotFactory) {
      protected void expectations() throws Throwable {
        expect(robotFactory.newRobotInPrimaryScreen()).andReturn(robot);
      }

      protected void codeToTest() {
        RobotEventGenerator eventGenerator = new RobotEventGenerator(robotFactory, new Settings());
        assertThat(eventGenerator.robot()).isSameAs(robot);
      }
    }.run();
  }

  @Test
  public void should_rethrow_any_error_from_RobotFactory()  {
    final AWTException toThrow = new AWTException("Thrown on purpose");
    new EasyMockTemplate(robotFactory) {
      protected void expectations() throws Throwable {
        expect(robotFactory.newRobotInPrimaryScreen()).andThrow(toThrow);
      }

      protected void codeToTest() {
        try {
          new RobotEventGenerator(robotFactory, new Settings());
          failWhenExpectingException();
        } catch (UnexpectedException e) {
          assertThat(e.getCause()).isSameAs(toThrow);
        }
      }
    }.run();

  }

}
