/*
 * Created on Aug 6, 2008
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
import static org.fest.swing.data.TableCell.row;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Collections.list;

import java.util.Collection;

import javax.swing.JTable;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link JTableCellEditableQuery#isCellEditable(JTable, org.fest.swing.data.TableCell)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JTableCellEditableQuery_isCellEditable_Test extends RobotBasedTestCase {

  private JTable table;

  private final int column;
  private final boolean editable;

  @Parameters
  public static Collection<Object[]> cells() {
    return list(new Object[][] {
        { 0, false },
        { 1, false },
        { 2, true },
        { 3, true },
        { 4, true },
    });
  }

  public JTableCellEditableQuery_isCellEditable_Test(int column, boolean editable) {
    this.column = column;
    this.editable = editable;
  }

  @Override protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    table = window.table;
  }

  @Test
  public void shouldIndicateWhetherCellIsEditableOrNot() {
    // TODO test validation of cell indices
    assertThat(isCellEditable(table, 0, column)).isEqualTo(editable);
  }

  @RunsInEDT
  private static boolean isCellEditable(final JTable table, final int row, final int column) {
    return execute(new GuiQuery<Boolean>() {
      @Override
      protected Boolean executeInEDT() {
        return JTableCellEditableQuery.isCellEditable(table, row(row).column(column));
      }
    });
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JTable table;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JTableCellEditableQuery_isCellEditable_Test.class);
      TableRenderDemo content = new TableRenderDemo();
      table = content.table;
      setContentPane(content);
    }
  }
}
