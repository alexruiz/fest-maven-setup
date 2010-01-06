/*
 * Created on Feb 25, 2008
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
import static org.fest.swing.driver.JTableDriver_TestCase.MyWindow.COLUMN_COUNT;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.junit.Test;

/**
 * Tests for <code>{@link JTableDriver#requireColumnCount(javax.swing.JTable, int)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableDriver_requireColumnCount_Test extends JTableDriver_TestCase {

  @Test
  public void should_fail_if_column_count_is_not_equal_to_expected() {
    try {
      driver.requireColumnCount(table, 12);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'columnCount'")
                                .contains("expected:<12> but was:<6>");
    }
  }

  @Test
  public void should_pass_if_column_count_is_equal_to_expected() {
    driver.requireColumnCount(table, COLUMN_COUNT);
  }
}
