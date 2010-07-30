/*
 * Created on Jul 29, 2008
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

import javax.swing.JTable;

import org.fest.swing.core.Robot;

class AbstractJTableCellWriterStub extends AbstractJTableCellWriter {
  AbstractJTableCellWriterStub(Robot robot) {
    super(robot);
  }

  public void enterValue(JTable table, int row, int column, String value) {}
  public void startCellEditing(JTable table, int row, int column) {}
  @Override
  public void stopCellEditing(JTable table, int row, int column) {}
  @Override
  public void cancelCellEditing(JTable table, int row, int column) {}
}