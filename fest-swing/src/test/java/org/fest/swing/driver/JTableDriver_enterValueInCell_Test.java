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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.easymock.EasyMock.expectLastCall;
import static org.fest.swing.data.TableCell.row;

import javax.swing.JTable;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.data.TableCell;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableDriver#enterValueInCell(JTable, TableCell, String)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableDriver_enterValueInCell_Test extends JTableDriver_withMockCellWriter_TestCase {

  private String value;

  @Override void onSetUp() {
    value = "Hello";
  }

  @Test
  public void should_enter_value_in_cell() {
    new EasyMockTemplate(cellWriter) {
      protected void expectations() {
        cellWriter.enterValue(table, 0, 0, value);
        expectLastCall().once();
      }

      protected void codeToTest() {
        driver.enterValueInCell(table, row(0).column(0), value);
      }
    }.run();
  }
}
