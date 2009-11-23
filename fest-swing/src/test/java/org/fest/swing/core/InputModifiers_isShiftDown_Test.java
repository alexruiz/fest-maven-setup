/*
 * Created on Aug 5, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.core;

import static java.awt.event.InputEvent.ALT_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link InputModifiers#isShiftDown(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class InputModifiers_isShiftDown_Test {

  @Test
  public void should_return_true_if_shift_mask_is_present() {
    assertThat(InputModifiers.isShiftDown(SHIFT_MASK | ALT_MASK)).isTrue();
  }

  @Test
  public void should_return_false_if_shift_mask_is_not_present() {
    assertThat(InputModifiers.isShiftDown(ALT_MASK)).isFalse();
  }
}
