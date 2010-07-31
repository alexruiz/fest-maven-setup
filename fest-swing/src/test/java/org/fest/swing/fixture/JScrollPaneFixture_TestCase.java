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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JScrollPanes.scrollPane;

import javax.swing.JScrollPane;

import org.fest.swing.driver.JScrollPaneDriver;
import org.junit.BeforeClass;

/**
 * Test cases for <code>{@link JScrollPaneFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JScrollPaneFixture_TestCase extends ComponentFixture_Implementations_TestCase<JScrollPane> {

  private static JScrollPane target;

  private JScrollPaneDriver driver;
  private JScrollPaneFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = scrollPane().createNew();
  }

  @Override final void onSetUp() {
    driver = createMock(JScrollPaneDriver.class);
    fixture = new JScrollPaneFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override final JScrollPaneDriver driver() {  return driver; }
  @Override final JScrollPane target() { return target; }
  @Override final JScrollPaneFixture fixture() { return fixture; }
}
