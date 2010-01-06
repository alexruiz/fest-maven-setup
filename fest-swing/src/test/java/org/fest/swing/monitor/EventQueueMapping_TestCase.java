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

import java.awt.Component;
import java.awt.EventQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

import org.fest.swing.test.awt.ToolkitStub;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;

/**
 * Base test case for <code>{@link EventQueueMapping}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class EventQueueMapping_TestCase extends EDTSafeTestCase {

  private ToolkitStub toolkit;

  EventQueue eventQueue;
  ComponentWithCustomEventQueue component;
  EventQueueMapping mapping;
  Map<Component, WeakReference<EventQueue>> queueMap;

  @Before public final void setUp() {
    eventQueue = new EventQueue();
    toolkit = ToolkitStub.createNew(eventQueue);
    component = new ComponentWithCustomEventQueue(toolkit);
    mapping = new EventQueueMapping();
    queueMap = mapping.queueMap;
  }
}
