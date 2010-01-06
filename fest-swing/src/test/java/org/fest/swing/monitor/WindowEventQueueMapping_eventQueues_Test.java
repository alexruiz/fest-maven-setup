/*
 * Created on Mar 22, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.EventQueue;
import java.util.Collection;

import org.fest.swing.test.awt.ToolkitStub;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowEventQueueMapping#eventQueues()}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowEventQueueMapping_eventQueues_Test extends WindowEventQueueMapping_withWindow_TestCase {

  @Test
  public void should_return_EventQueues() {
    EventQueue anotherEventQueue = new EventQueue();
    ToolkitStub anotherToolkit = ToolkitStub.createNew(anotherEventQueue);
    MyWindow anotherWindow = MyWindow.createNew(anotherToolkit, getClass());
    mapping.addQueueFor(window);
    mapping.addQueueFor(anotherWindow);
    Collection<EventQueue> eventQueues = mapping.eventQueues();
    assertThat(eventQueues).containsOnly(eventQueue, anotherEventQueue);
  }
}
