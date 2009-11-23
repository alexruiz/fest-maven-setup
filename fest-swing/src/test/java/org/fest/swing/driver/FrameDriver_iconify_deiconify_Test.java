/*
 * Created on Feb 24, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.NORMAL;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link FrameDriver#iconify(java.awt.Frame)}</code> and 
 * <code>{@link FrameDriver#deiconify(java.awt.Frame)}</code>.
 *
 * @author Alex Ruiz
 */
public class FrameDriver_iconify_deiconify_Test extends FrameDriver_TestCase {

  @Test
  public void shouldIconifyAndDeiconifyFrame() {
    showWindow();
    driver.iconify(window);
    assertThat(frameState()).isEqualTo(ICONIFIED);
    driver.deiconify(window);
    assertThat(frameState()).isEqualTo(NORMAL);
  }
}
