/*
 * Created on Jan 5, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.util;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.fest.mocks.EasyMockTemplate;
import org.junit.*;

/**
 * Tests for <code>{@link AWTExceptionHandlerInstaller#installAWTExceptionHandler(Class, SystemPropertyWriter)}</code>.
 *
 * @author Alex Ruiz
 */
public class AWTExceptionHandlerInstaller_installAWTExceptionHandler_Test {

  private SystemPropertyWriter writer;

  @Before
  public void setUp() {
    writer = createMock(SystemPropertyWriter.class);
  }

  @Test
  public void should_install_AWT_event_handler() {
    final Class<CorrectEventHandler> exceptionHandlerType = CorrectEventHandler.class;
    new EasyMockTemplate(writer) {
      @Override
      protected void expectations() {
        expect(writer.updateSystemProperty("sun.awt.exception.handler", exceptionHandlerType.getName())).andReturn("");
      }

      @Override
      protected void codeToTest() {
        AWTExceptionHandlerInstaller.installAWTExceptionHandler(exceptionHandlerType, writer);
      }
    }.run();
  }

  @Test
  public void should_throw_error_if_AWT_event_handler_type_does_not_have_default_constructor() {
    try {
      AWTExceptionHandlerInstaller.installAWTExceptionHandler(WrongEventHandler.class, writer);
      failWhenExpectingException();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("The exception handler type should have a default constructor");
    }
  }

  static class CorrectEventHandler {}

  static class WrongEventHandler {
    public WrongEventHandler(String something) {
      if (something == null) return;
    }
  }
}
