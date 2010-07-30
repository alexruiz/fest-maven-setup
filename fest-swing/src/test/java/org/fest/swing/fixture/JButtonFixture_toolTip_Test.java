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

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JButtons.button;

import javax.swing.JButton;

import org.fest.swing.driver.AbstractButtonDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in <code>{@link JButtonFixture}</code> that are inherited from
 * <code>{@link ToolTipDisplayFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JButtonFixture_toolTip_Test extends ToolTipDisplayFixture_TestCase<JButton> {

  private static JButton target;

  private AbstractButtonDriver driver;
  private JButtonFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = button().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(AbstractButtonDriver.class);
    fixture = new JButtonFixture(robot(), target);
    fixture.driver(driver);
  }


  @Override
  AbstractButtonDriver driver() {  return driver; }
  @Override
  JButton target() { return target; }
  @Override
  JButtonFixture fixture() { return fixture; }
}
