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

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Frame;

import org.fest.swing.test.builder.JFrames;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link FrameFixture#FrameFixture(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class FrameFixture_constructor_withName_Test extends EDTSafeTestCase {

  private FrameFixture fixture;

  @After
  public void tearDown() {
    if (fixture != null) fixture.cleanUp();
  }

  @Test
  public void should_lookup_showing_frame_by_name_using_new_Robot() {
    Frame target = JFrames.frame().withName("frame")
                          .withTitle(getClass().getSimpleName())
                          .createAndShow();
    fixture = new FrameFixture("frame");
    assertThat(fixture.robot).isNotNull();
    assertThat(fixture.component()).isSameAs(target);
  }
}
