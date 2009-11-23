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
import static org.fest.swing.test.builder.JSliders.slider;

import javax.swing.JSlider;

import org.fest.swing.driver.JSliderDriver;
import org.junit.BeforeClass;

/**
 * Test cases for <code>{@link JSliderFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class JSliderFixture_TestCase extends ComponentFixture_TestCase<JSlider> {

  private static JSlider target;

  private JSliderDriver driver;
  private JSliderFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = slider().createNew();
  }

  final void onSetUp() {
    driver = createMock(JSliderDriver.class);
    fixture = new JSliderFixture(robot(), target);
    fixture.driver(driver);
  }

  final JSliderDriver driver() {  return driver; }
  final JSlider target() { return target; }
  final JSliderFixture fixture() { return fixture; }
}
