/*
 * Created on Jul 27, 2009
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
package org.fest.swing.core;

import static org.easymock.EasyMock.expectLastCall;
import static org.fest.swing.test.awt.Toolkits.singletonToolkitMock;

import java.awt.Toolkit;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link EmergencyAbortListener#unregister()}</code>.
 *
 * @author Alex Ruiz
 */
public class EmergencyAbortListener_unregister_Test {

  private Toolkit toolkit;
  private EmergencyAbortListener listener;

  @Before
  public void setUp() {
    toolkit = singletonToolkitMock();
    listener = new EmergencyAbortListener(toolkit);
  }

  @Test
  public void should_unregister_from_toolkit() {
    new EasyMockTemplate(toolkit) {
      protected void expectations() {
        toolkit.removeAWTEventListener(listener);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.unregister();
      }
    }.run();
  }
}
