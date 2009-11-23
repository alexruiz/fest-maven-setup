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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.core;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link SimpleFallbackExceptionHandler#handle(Throwable)}</code>.
 *
 * @author Alex Ruiz
 */
public class SimpleFallbackExceptionHandler_handle_Test {

  private SimpleFallbackExceptionHandler handler;
  
  @Before public void setUp() {
    handler = new SimpleFallbackExceptionHandler();
  }
  
  @Test
  public void should_print_stack_trace_of_given_exception() {
    final Throwable t = createMock(Throwable.class);
    new EasyMockTemplate(t) {
      protected void expectations() {
        t.printStackTrace();
        expectLastCall().once();
      }

      protected void codeToTest() {
        handler.handle(t);
      }
    }.run();
  }
}
