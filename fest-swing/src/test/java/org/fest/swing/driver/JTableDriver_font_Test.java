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

import static java.awt.Font.PLAIN;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.data.TableCell.row;

import java.awt.Font;

import javax.swing.JTable;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.data.TableCell;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableDriver#font(JTable, TableCell)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableDriver_font_Test extends JTableDriver_withMockCellReader_TestCase {


  private Font font;

  @Override void onSetUp() {
    font = new Font("SansSerif", PLAIN, 8);
  }

  @Test
  public void should_return_cell_font() {
    new EasyMockTemplate(cellReader) {
      protected void expectations() {
        expect(cellReader.fontAt(table, 0, 0)).andReturn(font);
      }

      protected void codeToTest() {
        Font result = driver.font(table, row(0).column(0));
        assertThat(result).isSameAs(font);
      }
    }.run();
  }
}
