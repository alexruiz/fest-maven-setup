/*
 * Created on Sep 4, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JSplitPanes.splitPane;

import javax.swing.JSplitPane;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.driver.JSplitPaneDriver;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link JSplitPaneFixture#moveDividerTo(int)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSplitPaneFixture_moveDividerTo_Test extends ComponentFixture_TestCase<JSplitPane> {

  private static JSplitPane target;

  private JSplitPaneDriver driver;
  private JSplitPaneFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = splitPane().createNew();
  }

  void onSetUp() {
    driver = createMock(JSplitPaneDriver.class);
    fixture = new JSplitPaneFixture(robot(), target);
    fixture.driver(driver);
  }

  @Test
  public void should_move_divider() {
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.moveDividerTo(target, 8);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.moveDividerTo(8));
      }
    }.run();
  }

  JSplitPaneDriver driver() {  return driver; }
  JSplitPane target() { return target; }
  JSplitPaneFixture fixture() { return fixture; }
}
