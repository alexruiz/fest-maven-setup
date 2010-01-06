/*
 * Created on Dec 13, 2008
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

import static java.lang.Thread.currentThread;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Strings.concat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.*;

/**
 * Tests for <code>{@link Throwables#appendCurrentThreadStackTraceToThrowable(Throwable, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class Throwables_appendCurrentThreadStackTraceToThrowable_Test {

  private AtomicReference<RuntimeException> exceptionReference;

  @Before public void setUp() {
    exceptionReference = new AtomicReference<RuntimeException>();
  }

  @After public void tearDown() {
    exceptionReference.set(null);
  }

  @Test
  public void should_add_stack_trace_of_current_thread() {
    final CountDownLatch latch = new CountDownLatch(1);
    new Thread() {
      @Override public void run() {
        RuntimeException e = new RuntimeException("Thrown on purpose");
        exceptionReference.set(e);
        latch.countDown();
      }
    }.start();
    try {
      latch.await();
    } catch (InterruptedException e) {
      currentThread().interrupt();
    }
    RuntimeException thrown = exceptionReference.get();
    Throwables.appendCurrentThreadStackTraceToThrowable(thrown, "should_add_stack_trace_of_current_thread");
    StackTraceElement[] stackTrace = thrown.getStackTrace();
    assertThat(stackTrace.length).isGreaterThan(1);
    assertThat(asString(stackTrace[0])).isEqualTo(
        "org.fest.swing.edt.Throwables_appendCurrentThreadStackTraceToThrowable_Test$1.run");
    assertThat(asString(stackTrace[1])).isEqualTo(
        "org.fest.swing.edt.Throwables_appendCurrentThreadStackTraceToThrowable_Test.should_add_stack_trace_of_current_thread");
  }

  private String asString(StackTraceElement e) {
    return concat(e.getClassName(), ".", e.getMethodName());
  }
}
