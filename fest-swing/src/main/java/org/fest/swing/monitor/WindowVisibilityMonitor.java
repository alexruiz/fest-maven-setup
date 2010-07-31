/*
 * Created on Oct 8, 2007
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

import java.awt.Window;
import java.awt.event.*;

import org.fest.swing.annotation.RunsInEDT;

/**
 * Understands tracking of window visibility state.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class WindowVisibilityMonitor extends WindowAdapter implements ComponentListener {

  private final Windows windows;

  WindowVisibilityMonitor(Windows windows) {
    this.windows = windows;
  }

  public void componentShown(ComponentEvent e) {
    Object source = e.getSource();
    if (!(source instanceof Window)) return;
    windows.markAsShowing((Window)source);
  }

  public void componentHidden(ComponentEvent e) {
    Object source = e.getSource();
    if (!(source instanceof Window)) return;
    windows.markAsHidden((Window)source);
  }

  @RunsInEDT
  @Override public void windowClosed(WindowEvent e) {
    Window w = e.getWindow();
    w.removeComponentListener(this);
    w.removeWindowListener(this);
  }

  public void componentResized(ComponentEvent e) {}
  public void componentMoved(ComponentEvent e) {}

}
