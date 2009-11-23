/*
 * Created on Nov 19, 2009
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
import static org.fest.swing.test.builder.JMenuItems.menuItem;

import javax.swing.JMenuItem;

import org.fest.swing.driver.JMenuItemDriver;
import org.junit.BeforeClass;

/**
 * Test cases for <code>{@link JMenuItemFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JMenuItemFixture_TestCase extends ComponentFixture_TestCase<JMenuItem> {

  private static JMenuItem target;

  private JMenuItemDriver driver;
  private JMenuItemFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = menuItem().createNew();
  }

  final void onSetUp() {
    driver = createMock(JMenuItemDriver.class);
    fixture = new JMenuItemFixture(robot(), target);
    fixture.driver(driver);
  }

  final JMenuItemDriver driver() {  return driver; }
  final JMenuItem target() { return target; }
  final JMenuItemFixture fixture() { return fixture; }
}
