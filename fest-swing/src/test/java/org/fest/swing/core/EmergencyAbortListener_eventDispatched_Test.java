/*
 * Created on Jul 20, 2008
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
package org.fest.swing.core;

import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.*;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.awt.TestAWTEvents.newAWTEventMock;
import static org.fest.swing.test.awt.Toolkits.singletonToolkitMock;
import static org.fest.swing.test.builder.JButtons.button;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link EmergencyAbortListener#eventDispatched(AWTEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class EmergencyAbortListener_eventDispatched_Test extends EDTSafeTestCase {

  private TestTerminator terminator;
  private EmergencyAbortListener listener;

  @Before
  public void setUp() {
    terminator = createMock(TestTerminator.class);
    listener = new EmergencyAbortListener(singletonToolkitMock(), terminator);
  }

  @Test
  public void should_not_terminate_tests_if_event_id_is_not_KeyPressed() {
    final AWTEvent event = newAWTEventMock();
    final int eventId = KEY_PRESSED + 1;
    new EasyMockTemplate(event, terminator) {
      protected void expectations() {
        expect(event.getID()).andReturn(eventId);
      }

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  @Test
  public void should_not_terminate_tests_if_event_is_not_KeyEvent() {
    final AWTEvent event = newAWTEventMock();
    new EasyMockTemplate(event, terminator) {
      protected void expectations() {
        expect(event.getID()).andReturn(KEY_PRESSED);
      }

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  @Test
  public void should_not_terminate_tests_if_key_code_does_not_match() {
    final KeyEvent event = new KeyEvent(button().createNew(), KEY_PRESSED, 0, 0, VK_Z, 'Z');
    new EasyMockTemplate(terminator) {
      protected void expectations() {}

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  @Test
  public void should_not_terminate_tests_if_modifiers_do_not_match() {
    final KeyEvent event = new KeyEvent(button().createNew(), KEY_PRESSED, 0, 0, VK_A, 'A');
    new EasyMockTemplate(terminator) {
      protected void expectations() {}

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }

  @Test
  public void should_terminate_tests_if_key_combination_matches() {
    final KeyEvent event = new KeyEvent(button().createNew(), KEY_PRESSED, 0, CTRL_MASK | SHIFT_MASK, VK_A, 'A');
    new EasyMockTemplate(terminator) {
      protected void expectations() {
        terminator.terminateTests();
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.eventDispatched(event);
      }
    }.run();
  }
}
