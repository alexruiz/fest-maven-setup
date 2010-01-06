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

/**
 * Tests for <code>{@link RobotEventGenerator#pressMouse(java.awt.Component, java.awt.Point, int)}</code> and
 * <code>{@link RobotEventGenerator#releaseMouse(int)}</code>.
 * 
 * @author Alex Ruiz
 */
public class RobotEventGenerator_pressMouseOnComponent_Test extends InputEventGenerator_pressMouseOnComponent_TestCase {

  public RobotEventGenerator_pressMouseOnComponent_Test(MouseButton button) {
    super(button);
  }

  InputEventGenerator eventGenerator() {
    return new RobotEventGenerator();
  }
}
