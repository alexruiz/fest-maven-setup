/*
 * Created on Jul 31, 2009
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
package org.fest.swing.util;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.OSIdentifierStub.*;

import org.junit.Test;

/**
 * Tests for <code>{@link Platform#isLinux()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_isLinux_Test extends Platform_TestCase {

  @Test
  public void should_return_true_if_OS_is_Linux() {
    Platform.initialize(linux(), toolkitProvider);
    assertThat(Platform.isLinux()).isTrue();
  }

  @Test
  public void should_return_false_if_OS_is_not_Linux() {
    Platform.initialize(windowsXP(), toolkitProvider);
    assertThat(Platform.isLinux()).isFalse();
  }
}
