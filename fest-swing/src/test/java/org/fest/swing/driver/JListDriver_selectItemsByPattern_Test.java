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
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.util.Arrays.array;

import org.fest.swing.exception.LocationUnavailableException;
import org.junit.Test;

/**
 * Tests for <code>{@link JListDriver#selectItems(javax.swing.JList, java.util.regex.Pattern[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_selectItemsByPattern_Test extends JListDriver_TestCase {

  @Test
  public void should_throw_error_if_a_matching_item_was_not_found() {
    showWindow();
    try {
      driver.selectItems(list, array(regex("ten")));
      failWhenExpectingException();
    } catch (LocationUnavailableException e) {
      assertThat(e.getMessage()).isEqualTo(
          "Unable to find item matching the pattern 'ten' among the JList contents ['one', 'two', 'three']");
    }
  }

  @Test
  public void should_select_items_even_if_already_selected() {
    select(1, 2);
    showWindow();
    driver.selectItems(list, array(regex("two"), regex("three")));
    assertThat(selectedValues()).isEqualTo(array("two", "three"));
  }

  @Test
  public void should_select_items_matching_pattern() {
    showWindow();
    driver.selectItems(list, array(regex("t.*")));
    assertThat(selectedValues()).isEqualTo(array("two", "three"));
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_select_items_matching_patterns() {
    showWindow();
    driver.selectItems(list, array(regex("tw.*"), regex("thr.*")));
    assertThat(selectedValues()).isEqualTo(array("two", "three"));
    assertThatCellReaderWasCalled();
  }

  @Test
  public void should_throw_error_if_JList_is_disabled() {
    disableList();
    try {
      driver.selectItems(list, array(regex("two"), regex("three")));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsDisabledComponent(e);
    }
  }

  @Test
  public void should_throw_error_if_JList_is_not_showing_on_the_screen() {
    try {
      driver.selectItems(list, array(regex("two"), regex("three")));
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThatErrorCauseIsNotShowingComponent(e);
    }
  }
}
