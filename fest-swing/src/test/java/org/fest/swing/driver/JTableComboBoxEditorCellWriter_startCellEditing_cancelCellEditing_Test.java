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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.query.ComponentShowingQuery.isShowing;

import javax.swing.JComboBox;

import org.fest.swing.cell.JTableCellWriter;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableComboBoxEditorCellWriter#startCellEditing(javax.swing.JTable, int, int)}</code>
 * and <code>{@link JTableComboBoxEditorCellWriter#cancelCellEditing(javax.swing.JTable, int, int)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableComboBoxEditorCellWriter_startCellEditing_cancelCellEditing_Test 
    extends JTableCellWriter_startCellEditing_TestCase {

  protected JTableCellWriter createWriter() {
    return new JTableComboBoxEditorCellWriter(robot);
  }

  @Test
  public void should_start_and_cancel_cell_editing() {
    int row = 0;
    int col = 2;
    JComboBox editor = (JComboBox)writer.editorForCell(table, row, col);
    writer.startCellEditing(table, row, col);
    assertThat(isShowing(editor)).isTrue();
    writer.cancelCellEditing(table, row, col);
    assertThat(valueAt(row, col)).isEqualTo("Snowboarding");
    assertThat(isShowing(editor)).isFalse();
  }
}
