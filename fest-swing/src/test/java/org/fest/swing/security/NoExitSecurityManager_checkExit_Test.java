/*
 * Created on Dec 21, 2009
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
package org.fest.swing.security;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.util.Arrays.array;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.util.StackTraces;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link NoExitSecurityManager#checkExit(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class NoExitSecurityManager_checkExit_Test {

  private ExitCallHook hook;
  private StackTraces stackTraces;
  private NoExitSecurityManager securityManager;

  @Before
  public void setUp() {
    hook = createMock(ExitCallHook.class);
    stackTraces = createMock(StackTraces.class);
    securityManager = new NoExitSecurityManager(hook, stackTraces);
  }

  @Test
  public void should_call_hook_and_throw_error_if_Runtime_exit_was_called() {
    final StackTraceElement[] stackTrace = array(methodInRuntime("exit"));
    new EasyMockTemplate(hook, stackTraces) {
      protected void expectations() {
        expect(stackTraces.stackTraceInCurrentThread()).andReturn(stackTrace);
        hook.exitCalled(0);
        expectLastCall();
      }

      protected void codeToTest() {
        try {
          securityManager.checkExit(0);
          failWhenExpectingException();
        } catch (ExitException e) {}
      }
    }.run();
  }

  @Test
  public void should_call_hook_and_throw_error_if_Runtime_halt_was_called() {
    final StackTraceElement[] stackTrace = array(methodInRuntime("halt"));
    new EasyMockTemplate(hook, stackTraces) {
      protected void expectations() {
        expect(stackTraces.stackTraceInCurrentThread()).andReturn(stackTrace);
        hook.exitCalled(0);
        expectLastCall();
      }

      protected void codeToTest() {
        try {
          securityManager.checkExit(0);
          failWhenExpectingException();
        } catch (ExitException e) {}
      }
    }.run();
  }

  @Test
  public void should_not_call_hook_and_throw_error_if_method_called_is_in_Runtime_but_is_not_exit_or_halt() {
    final StackTraceElement[] stackTrace = array(methodInRuntime("availableProcessors"));
    new EasyMockTemplate(hook, stackTraces) {
      protected void expectations() {
        expect(stackTraces.stackTraceInCurrentThread()).andReturn(stackTrace);
      }

      protected void codeToTest() {
        securityManager.checkExit(0);
      }
    }.run();
  }

  private StackTraceElement methodInRuntime(String methodName) {
    return new StackTraceElement(Runtime.class.getName(), methodName, "Runtime.java", 0);
  }

  @Test
  public void should_not_call_hook_and_throw_error_if_method_called_is_not_Runtime_exit_or_halt() {
    StackTraceElement e = new StackTraceElement(String.class.getName(), "substring", "String.java", 0);
    final StackTraceElement[] stackTrace = array(e);
    new EasyMockTemplate(hook, stackTraces) {
      protected void expectations() {
        expect(stackTraces.stackTraceInCurrentThread()).andReturn(stackTrace);
      }

      protected void codeToTest() {
        securityManager.checkExit(0);
      }
    }.run();
  }
}
