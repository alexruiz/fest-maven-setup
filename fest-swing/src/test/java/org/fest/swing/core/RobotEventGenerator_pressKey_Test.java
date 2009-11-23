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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.core;

/**
 * Tests for <code>{@link RobotEventGenerator#pressKey(int, char)}</code> and
 * <code>{@link RobotEventGenerator#releaseKey(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class RobotEventGenerator_pressKey_Test extends InputEventGenerator_pressKey_TestCase {

  public RobotEventGenerator_pressKey_Test(int keyToPress, String expectedText) {
    super(keyToPress, expectedText);
  }

  InputEventGenerator eventGenerator() {
    return new RobotEventGenerator();
  }
}
