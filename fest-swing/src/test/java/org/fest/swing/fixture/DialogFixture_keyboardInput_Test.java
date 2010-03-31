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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.builder.JDialogs.dialog;
import static org.fest.swing.test.task.WindowDestroyTask.hideAndDisposeInEDT;

import java.awt.Dialog;

import org.fest.swing.driver.DialogDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Tests for methods in <code>{@link DialogFixture}</code> that are inherited from
 * {@link KeyboardInputSimulationFixture}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class DialogFixture_keyboardInput_Test extends KeyboardInputSimulationFixture_TestCase<Dialog> {

  private static Dialog target;

  private DialogDriver driver;
  private DialogFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = dialog().createNew();
  }

  @AfterClass
  public static void disposeTarget() {
    hideAndDisposeInEDT(target);
  }

  void onSetUp() {
    driver = createMock(DialogDriver.class);
    fixture = new DialogFixture(robot(), target);
    fixture.driver(driver);
  }

  DialogDriver driver() { return driver; }
  Dialog target() { return target; }
  DialogFixture fixture() { return fixture; }
}