/*
 * Created on Nov 12, 2007
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
package org.fest.swing.hierarchy;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link NewHierarchy#roots()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class NewHierarchy_roots_Test extends NewHierarchy_TestCase {

  @Test
  public void should_not_contain_ignored_Windows() {
    NewHierarchy hierarchy = new NewHierarchy(toolkit, filter, true);
    assertThat(hierarchy.roots()).excludes(window);
  }

  @Test
  public void should_contain_Windows_that_have_not_been_ignored() {
    NewHierarchy hierarchy = new NewHierarchy(toolkit, filter, false);
    assertThat(hierarchy.roots()).contains(window);
  }

  @Test
  public void should_recognize_given_Component() {
    NewHierarchy hierarchy = new NewHierarchy(toolkit, filter, true);
    assertThat(hierarchy.roots()).excludes(window);
    hierarchy.recognize(window);
    assertThat(hierarchy.roots()).contains(window);
  }
}
