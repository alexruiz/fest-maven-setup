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
 * Tests for <code>{@link Platform#canMoveWindows()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_canMoveWindows_Test extends Platform_TestCase {
  
  @Test
  public void should_return_cannot_move_windows_if_OS_is_Windows() {
    Platform.initialize(windows9x(), toolkitProvider);
    assertThat(Platform.canMoveWindows()).isEqualTo(false);
  }
  
  @Test
  public void should_return_cannot_move_windows_if_OS_is_OSX() {
    Platform.initialize(osX(), toolkitProvider);
    assertThat(Platform.canMoveWindows()).isEqualTo(false);
  }

  @Test
  public void should_return_can_move_windows_if_OS_is_not_Windows_or_OSX() {
    Platform.initialize(linux(), toolkitProvider);
    assertThat(Platform.canMoveWindows()).isEqualTo(true);
  }
}
