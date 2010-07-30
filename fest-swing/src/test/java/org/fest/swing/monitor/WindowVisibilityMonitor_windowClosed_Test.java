/*
 * Created on Oct 10, 2007
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.MethodInvocations.Args.args;

import java.awt.event.WindowEvent;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowVisibilityMonitor#windowClosed(WindowEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowVisibilityMonitor_windowClosed_Test extends WindowVisibilityMonitor_TestCase {

  @Test
  public void should_remove_itself_when_Window_is_closed() {
    window.startRecording();
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {}

      @Override
      protected void codeToTest() {
        monitor.windowClosed(new WindowEvent(window, 8));
        assertThat(window.requireInvoked("removeWindowListener", args(monitor)));
        assertThat(window.requireInvoked("removeComponentListener", args(monitor)));
      }
    }.run();
  }
}
