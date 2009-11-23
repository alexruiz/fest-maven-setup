/*
 * Created on Apr 2, 2008
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
package org.fest.swing.core;

import static org.fest.swing.timing.Pause.pause;

import java.awt.*;

import org.fest.swing.input.InputState;
import org.fest.swing.monitor.WindowMonitor;

/**
 * Understands posting <code>{@link AWTEvent}</code>s in a <code>{@link EventQueue}</code>.
 *
 * @author Yvonne Wang
 */
class AWTEventPoster {

  private final Toolkit toolkit;
  private final InputState inputState;
  private final WindowMonitor windowMonitor;
  private final Settings settings;

  AWTEventPoster(Toolkit toolkit, InputState inputState, WindowMonitor windowMonitor, Settings settings) {
    this.toolkit = toolkit;
    this.inputState = inputState;
    this.windowMonitor = windowMonitor;
    this.settings = settings;
  }

  // Post the given event to the corresponding event queue for the given component.
  void postEvent(Component c, AWTEvent event) {
    // Force an update of the input state, so that we're in synch internally. Otherwise we might post more events before
    // this one gets processed and end up using stale values for those events.
    inputState.update(event);
    eventQueueFor(c).postEvent(event);
    pause(settings.delayBetweenEvents());
  }

  /* Usually only needed when dealing with Applets. */
  private EventQueue eventQueueFor(Component c) {
    return c != null ? windowMonitor.eventQueueFor(c) : toolkit.getSystemEventQueue();
  }
}
