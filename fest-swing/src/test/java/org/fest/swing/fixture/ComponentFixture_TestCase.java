/*
 * Created on Jul 27, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.TestRobots.newRobotMock;
import static org.fest.swing.test.builder.JTextFields.textField;

import javax.swing.JTextField;

import org.junit.Before;

/**
 * Test cases for <code>{@link ComponentFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentFixture_TestCase {

  JTextField target;
  ComponentFixture<JTextField> fixture;

  @Before
  public final void setUp() {
    target = textField().createNew();
    fixture = new ConcreteComponentFixture(newRobotMock(), target);
  }
}
