/*
 * Created on Oct 18, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowMonitor#rootWindows()}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowMonitor_rootWindows_Test extends WindowMonitor_TestCase {

  @Test
  public void should_return_root_Windows() {
    final List<Window> rootWindows = new ArrayList<Window>();
    new EasyMockTemplate(context) {
      protected void expectations() {
        expect(context.rootWindows()).andReturn(rootWindows);
      }

      protected void codeToTest() {
        assertThat(monitor.rootWindows()).isSameAs(rootWindows);
      }
    }.run();
  }

}
