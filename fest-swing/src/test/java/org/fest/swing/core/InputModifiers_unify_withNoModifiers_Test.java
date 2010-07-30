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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link InputModifiers#unify(int...)}</code>.
 *
 * @author Alex Ruiz
 */
public class InputModifiers_unify_withNoModifiers_Test {

  @Test
  public void should_return_zero_if_array_of_modifiers_is_empty() {
    assertThat(InputModifiers.unify(new int[0])).isZero();
  }

  @Test
  public void should_return_zero_if_array_of_modifiers_is_null() {
    int[] modifiers = null;
    assertThat(InputModifiers.unify(modifiers)).isZero();
  }
}
