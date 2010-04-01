/*
 * Created on Jun 24, 2008
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
package org.fest.swing.input;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.awt.Toolkits.newToolkitStub;

import java.awt.Toolkit;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.awt.ToolkitStub;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link DragAwareEventNormalizer#stopListening()}</code>.
 *
 * @author Alex Ruiz
 */
public class DragAwareEventNormalizer_stopListening_Test extends DragAwareEventNormalizer_TestCase {

  private ToolkitStub toolkit;
  private EventQueueStub eventQueue;
  private int mask;

  @Before
  public void setUp() {
    eventQueue = new EventQueueStub();
    toolkit = newToolkitStub();
    toolkit.eventQueue(eventQueue);
    mask = 8;
  }

  @Test
  public void should_dispose_EventQueue_when_stops_listening() {
    final DragAwareEventQueue dragAwareEventQueue = createMock(DragAwareEventQueue.class);
    eventNormalizer = new DragAwareEventNormalizer() {
      @Override DragAwareEventQueue newDragAwareEventQueue(Toolkit t, long m) {
        return dragAwareEventQueue;
      }
    };
    eventNormalizer.startListening(toolkit, delegateEventListenerMock(), mask);
    new EasyMockTemplate(dragAwareEventQueue) {
      protected void expectations() {
        dragAwareEventQueue.pop();
        expectLastCall().once();
      }

      protected void codeToTest() {
        eventNormalizer.stopListening();
      }
    }.run();
    assertEventNormalizerIsNotInToolkit(toolkit, mask);
  }

  @Test
  public void should_gracefully_stop_listening_if_DragAwareQueue_is_null() {
    eventNormalizer = new DragAwareEventNormalizer() {
      @Override DragAwareEventQueue newDragAwareEventQueue(Toolkit t, long m) {
        throw new RuntimeException("Thrown on purpose");
      }
    };
    eventNormalizer.startListening(toolkit, delegateEventListenerMock(), mask);
    eventNormalizer.stopListening();
    assertEventNormalizerIsNotInToolkit(toolkit, mask);
  }
}
