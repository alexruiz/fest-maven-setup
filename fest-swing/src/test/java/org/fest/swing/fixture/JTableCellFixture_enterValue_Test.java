/*
 * Created on Mar 2, 2008
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
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableCellFixture#enterValue(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTableCellFixture_enterValue_Test extends JTableCellFixture_withMockDriver_TestCase {

  @Test
  public void should_enter_value_in_cell() {
    final String value = "Hello";
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.enterValueInCell(table.target, cell, value);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture.enterValue(value));
      }
    }.run();
  }
}
