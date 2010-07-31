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
package org.fest.swing.monitor;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Window;
import java.util.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link Context#rootWindows()}</code>.
 *
 * @author Alex Ruiz
 */
public class Context_rootWindows_Test extends Context_TestCase {

  @Test
  public void should_return_root_windows() {
    final TestWindow anotherFrame = TestWindow.createNewWindow(getClass());
    new EasyMockTemplate(windowEventQueueMapping) {
      @Override protected void expectations() {
        expect(windowEventQueueMapping.windows()).andReturn(frameInList());
      }

      @Override protected void codeToTest() {
        Collection<Window> rootWindows = context.rootWindows();
        assertThat(rootWindows).contains(window);
        assertThat(rootWindows).contains(anotherFrame);
      }
    }.run();
  }

  private List<Window> frameInList() {
    List<Window> windows = new ArrayList<Window>();
    windows.add(window);
    return windows;
  }
}
