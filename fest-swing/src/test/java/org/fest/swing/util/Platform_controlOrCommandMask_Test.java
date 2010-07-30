/*
 * Created on Jul 31, 2009
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
package org.fest.swing.util;

import static java.awt.Event.CTRL_MASK;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Toolkit;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link Platform#controlOrCommandMask()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_controlOrCommandMask_Test extends Platform_TestCase {

  @Test
  public void should_return_control_or_command_mask() {
    final Toolkit toolkit = wireMockToolkit();
    new EasyMockTemplate(toolkit) {
      @Override
      protected void expectations() {
        expect(toolkit.getMenuShortcutKeyMask()).andReturn(CTRL_MASK);
      }

      @Override
      protected void codeToTest() {
        assertThat(Platform.controlOrCommandMask()).isEqualTo(CTRL_MASK);
      }
    }.run();
  }
}
