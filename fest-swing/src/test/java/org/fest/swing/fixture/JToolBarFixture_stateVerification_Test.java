/*
 * Created on Nov 18, 2009
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
package org.fest.swing.fixture;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JToolBars.toolBar;

import javax.swing.JToolBar;

import org.fest.swing.driver.JToolBarDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in <code>{@link JToolBarFixture}</code> that are inherited from
 * <code>{@link StateVerificationFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JToolBarFixture_stateVerification_Test extends StateVerificationFixture_TestCase<JToolBar> {

  private static JToolBar target;

  private JToolBarDriver driver;
  private JToolBarFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = toolBar().createNew();
  }

  void onSetUp() {
    driver = createMock(JToolBarDriver.class);
    fixture = new JToolBarFixture(robot(), target);
    fixture.driver(driver);
  }

  JToolBarDriver driver() {  return driver; }
  JToolBar target() { return target; }
  JToolBarFixture fixture() { return fixture; }
}
