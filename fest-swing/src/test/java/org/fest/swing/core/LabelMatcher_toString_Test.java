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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link LabelMatcher#toString()}</code>.
 *
 * @author Alex Ruiz
 */
public class LabelMatcher_toString_Test {

  @Test
  public void should_implement_toString() {
    LabelMatcher matcher = new LabelMatcher("Hello");
    assertThat(matcher.toString()).contains("label='Hello'")
                                  .contains("type=java.awt.Component")
                                  .contains("requireShowing=false");
  }
}
