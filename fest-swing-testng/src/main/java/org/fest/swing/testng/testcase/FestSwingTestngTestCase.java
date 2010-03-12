/*
 * Created on Jan 17, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.testng.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.testing.FestSwingTestCaseTemplate;

/**
 * Understands a template for test cases that use FEST-Swing and TestNG. This template installs a
 * <code>{@link FailOnThreadViolationRepaintManager}</code> to catch violations of Swing thread rules and manages both
 * creation and clean up of a <code>{@link Robot}</code>.
 * @since 1.1
 *
 * @author Alex Ruiz
 */
public abstract class FestSwingTestngTestCase extends FestSwingTestCaseTemplate {

  /**
   * Installs a <code>{@link FailOnThreadViolationRepaintManager}</code> to catch violations of Swing threading rules.
   */
  @BeforeClass
  public final void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  /**
   * Creates a new <code>{@link Robot}</code>.
   */
  @BeforeMethod
  public final void setUp() {
    setUpRobot();
    onSetUp();
  }

  /**
   * Subclasses need set up their own test fixture in this method. This method is called <strong>after</strong>
   * executing <code>{@link #setUp()}</code>.
   */
  protected abstract void onSetUp();

  /**
   * Cleans up any resources used by this test case's <code>{@link Robot}</code>.
   */
  @AfterMethod
  public final void tearDown() {
    try {
      onTearDown();
    } finally {
      cleanUp();
    }
  }

  /**
   * Subclasses need to clean up resources in this method. This method is called <strong>before</strong> executing
   * <code>{@link #tearDown()}</code>.
   */
  protected void onTearDown() {}
}
