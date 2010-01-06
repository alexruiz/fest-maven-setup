/*
 * Created on Oct 17, 2008
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
package org.fest.swing.edt;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.test.core.MethodInvocations;
import org.fest.swing.test.core.SequentialTestCase;
import org.junit.Test;

/**
 * Tests for <code>{@link GuiActionRunner#execute(GuiQuery)}</code>.
 *
 * @author Alex Ruiz
 */
public class GuiActionRunner_execute_queryNotInEDT_Test extends SequentialTestCase {

  @Override protected final void onSetUp() {
    GuiActionRunner.executeInEDT(true);
  }

  @Test
  public void should_execute_query() {
    String valueToReturnWhenExecuted = "Hello";
    TestGuiQuery query = new TestGuiQuery(valueToReturnWhenExecuted);
    GuiActionRunner.executeInEDT(false);
    String result = GuiActionRunner.execute(query);
    assertThat(result).isSameAs(valueToReturnWhenExecuted);
    assertThat(query.wasExecutedInEDT()).isFalse();
    query.requireInvoked("executeInEDT");
  }

  @Test
  public void should_wrap_any_thrown_Exception() {
    final TestGuiQuery query = createMock(TestGuiQuery.class);
    final RuntimeException error = expectedError();
    new EasyMockTemplate(query) {
      protected void expectations() {
        expect(query.executeInEDT()).andThrow(error);
      }

      protected void codeToTest() {
        try {
          GuiActionRunner.executeInEDT(false);
          GuiActionRunner.execute(query);
          failWhenExpectingException();
        } catch (UnexpectedException e) {
          assertThat(e.getCause()).isSameAs(error);
        }
      }
    }.run();
  }

  private RuntimeException expectedError() {
    return new RuntimeException("Thrown on purpose");
  }

  private static class TestGuiQuery extends GuiQuery<String> {
    private final MethodInvocations methodInvocations = new MethodInvocations();
    private final String valueToReturnWhenExecuted;

    TestGuiQuery(String valueToReturnWhenExecuted) {
      this.valueToReturnWhenExecuted = valueToReturnWhenExecuted;
    }

    protected String executeInEDT() {
      methodInvocations.invoked("executeInEDT");
      return valueToReturnWhenExecuted;
    }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
