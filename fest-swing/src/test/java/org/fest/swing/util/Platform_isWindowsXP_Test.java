/*
 * Created on Jul 30, 2009
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
import static org.fest.swing.util.OSIdentifierStub.macintosh;
import static org.fest.swing.util.OSIdentifierStub.windowsXP;

import org.junit.Test;

/**
 * Tests for <code>{@link Platform#isWindowsXP()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_isWindowsXP_Test extends Platform_TestCase {

  @Test
  public void should_return_true_if_OS_is_WindowsXP() {
    Platform.initialize(windowsXP(), toolkitProvider);
    assertThat(Platform.isWindowsXP()).isTrue();
  }

  @Test
  public void should_return_false_if_OS_is_not_WindowsXP() {
    Platform.initialize(macintosh(), toolkitProvider);
    assertThat(Platform.isWindowsXP()).isFalse();
  }
}
