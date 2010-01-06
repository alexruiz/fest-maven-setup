/*
 * Created on Aug 24, 2009
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
package org.fest.swing.monitor;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link Windows#isReady(java.awt.Window)}</code>.
 *
 * @author Alex Ruiz
 */
public class Windows_isReady_Test extends Windows_TestCase {

  @Test
  public void should_return_true_if_Window_is_open_and_not_hidden() {
    windows.open.put(window, true);
    windows.hidden.remove(window);
    assertThat(windows.isReady(window)).isTrue();
  }

  @Test
  public void should_return_false_if_Window_is_open_and_hidden() {
    windows.hidden.put(window, true);
    windows.open.put(window, true);
    assertThat(windows.isReady(window)).isFalse();
  }

  @Test
  public void should_return_false_if_Window_is_not_open_and_hidden() {
    windows.hidden.put(window, true);
    windows.open.remove(window);
    assertThat(windows.isReady(window)).isFalse();
  }

  @Test
  public void should_return_false_if_Window_is_not_open_and_not_hidden() {
    windows.hidden.remove(window);
    windows.open.remove(window);
    assertThat(windows.isReady(window)).isFalse();
  }
}
