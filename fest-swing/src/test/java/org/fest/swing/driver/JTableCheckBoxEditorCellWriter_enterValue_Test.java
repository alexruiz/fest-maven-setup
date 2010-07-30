/*
 * Created on Jun 10, 2008
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

import org.fest.swing.cell.JTableCellWriter;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableCheckBoxEditorCellWriter#enterValue(javax.swing.JTable, int, int, String)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableCheckBoxEditorCellWriter_enterValue_Test extends JTableCellWriter_enterValue_TestCase {

  @Override
  protected JTableCellWriter createWriter() {
    return new JTableCheckBoxEditorCellWriter(robot);
  }

  @Test
  public void should_check_in_JCheckBox_editor() {
    int row = 0;
    int col = 4;
    writer.enterValue(table, row, col, "false");
    assertThat(valueAt(row,col)).isEqualTo(false);
    writer.enterValue(table, row, col, "true");
    assertThat(valueAt(row,col)).isEqualTo(true);
    writer.enterValue(table, row, col, "false");
    assertThat(valueAt(row,col)).isEqualTo(false);
    writer.enterValue(table, row, col, "false");
    assertThat(valueAt(row,col)).isEqualTo(false);
  }
}
