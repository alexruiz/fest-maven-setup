/*
 * Created on Feb 24, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.*;
import static org.fest.swing.util.Range.from;
import static org.fest.swing.util.Range.to;
import static org.fest.util.Arrays.array;

import org.junit.Test;

/**
 * Tests for <code>{@link JListDriver#selectItems(javax.swing.JList, org.fest.swing.util.Range.From, org.fest.swing.util.Range.To)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_selectItemsByFluentRange_Test extends JListDriver_TestCase {

  @Test
  public void should_select_items() {
    showWindow();
    driver.selectItems(list, from(0), to(1));
    assertThat(selectedValues()).isEqualTo(array("one", "two"));
  }

  @Test
  public void should_select_items_even_if_already_selected() {
    select(0, 1);
    showWindow();
    driver.selectItems(list, from(0), to(1));
    assertThat(selectedValues()).isEqualTo(array("one", "two"));
  }

  @Test
  public void should_throw_error_if_JList_is_disabled() {
    disableList();
    try {
      driver.selectItems(list, from(0), to(1));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JList_is_not_showing_on_the_screen() {
    try {
      driver.selectItems(list, from(0), to(1));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }
}
