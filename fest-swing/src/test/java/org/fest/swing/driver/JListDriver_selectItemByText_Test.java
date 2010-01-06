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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.*;

import org.fest.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for <code>{@link JListDriver#selectItem(javax.swing.JList, String)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_selectItemByText_Test extends JListDriver_TestCase {

  @Test
  public void should_throw_error_if_a_matching_item_was_not_found() {
    showWindow();
    try {
      driver.selectItem(list, "ten");
      failWhenExpectingException();
    } catch (LocationUnavailableException e) {
      assertThat(e.getMessage()).isEqualTo(
          "Unable to find item matching the value 'ten' among the JList contents ['one', 'two', 'three']");
    }
  }

  @Test
  public void should_select_item() {
    showWindow();
    driver.selectItem(list, "two");
    assertThat(selectedValue()).isEqualTo("two");
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_not_select_item_if_already_selected() {
    select(1);
    showWindow();
    driver.selectItem(list, "two");
    assertThat(selectedValue()).isEqualTo("two");
  }

  @Test
  public void should_select_item_matching_pattern() {
    showWindow();
    driver.selectItem(list, "tw.*");
    assertThat(selectedValue()).isEqualTo("two");
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_throw_error_if_JList_is_disabled() {
    disableList();
    try {
      driver.selectItem(list, "two");
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JList_is_not_showing_on_the_screen() {
    try {
      driver.selectItem(list, "two");
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }
}
