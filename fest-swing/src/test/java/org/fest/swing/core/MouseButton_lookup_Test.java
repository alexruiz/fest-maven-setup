/*
 * Created on Sep 21, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link MouseButton#lookup(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class MouseButton_lookup_Test extends MouseButton_TestCase {

  public MouseButton_lookup_Test(MouseButton button, int mask) {
    super(button, mask);
  }

  @Test 
  public void shouldLookupButtonGivenMask() {
    assertThat(MouseButton.lookup(mask)).isEqualTo(button);
  }
}
