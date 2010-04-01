/*
 * Created on Oct 18, 2007
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
package org.fest.swing.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.reset;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.awt.Toolkits.newToolkitStub;

import java.awt.Frame;
import java.awt.Window;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.awt.ToolkitStub;
import org.fest.swing.test.core.SequentialTestCase;
import org.fest.swing.test.swing.TestWindow;

/**
 * Base test case for <code>{@link WindowMonitor}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class WindowMonitor_TestCase extends SequentialTestCase {

  WindowMonitor monitor;

  ToolkitStub toolkit;
  Windows windows;
  Context context;
  WindowStatus windowStatus;
  TestWindow frame;

  @Override protected final void onSetUp() {
    toolkit = newToolkitStub();
    windows = createMock(Windows.class);
    context = createMock(Context.class);
    windowStatus = createMock(WindowStatus.class);
    frame = TestWindow.createNewWindow(getClass());
    createWindowMonitor();
  }

  private void createWindowMonitor() {
    new EasyMockTemplate(context, windows, windowStatus) {
      protected final void expectations() {
        expect(windowStatus.windows()).andReturn(windows);
        for (Frame f : Frame.getFrames()) expectToExamine(f);
      }

      private void expectToExamine(Window w) {
        expectWindowsToMarkAsReadyToUse(w);
        expectContextToAddContextFor(w);
        windows.attachNewWindowVisibilityMonitor(w);
        for (Window owned : ownedWindowsOf(w)) expectToExamine(owned);
      }

      private void expectWindowsToMarkAsReadyToUse(Window w) {
        windows.markExisting(w);
        expectLastCall().once();
      }

      private void expectContextToAddContextFor(Window w) {
        context.addContextFor(w);
        expectLastCall().once();
      }

      protected final void codeToTest() {
        monitor = execute(new GuiQuery<WindowMonitor>() {
          protected WindowMonitor executeInEDT() {
            return new WindowMonitor(toolkit, context, windowStatus);
          }
        });
      }
    }.run();
    reset(context, windows, windowStatus);
  }

  @RunsInEDT
  private static Window[] ownedWindowsOf(final Window w) {
    return execute(new GuiQuery<Window[]>() {
      protected Window[] executeInEDT() {
        return w.getOwnedWindows();
      }
    });
  }

  @Override protected final void onTearDown() {
    frame.destroy();
  }
}
