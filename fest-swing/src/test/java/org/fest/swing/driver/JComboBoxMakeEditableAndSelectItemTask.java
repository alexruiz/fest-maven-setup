/*
 * Created on Jul 26, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that makes a <code>{@link JComboBox}</code> editable and selects an item. This task is executed in
 * the event dispatch thread.
 *
 * @author Alex Ruiz
 */
final class JComboBoxMakeEditableAndSelectItemTask {

  @RunsInEDT
   static void makeEditableAndSelectItem(final JComboBox comboBox, final Object itemToSelect) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        comboBox.setEditable(true);
        comboBox.setSelectedItem(itemToSelect);
      }
    });
  }

  @RunsInEDT
  static void makeEditableAndSelectIndex(final JComboBox comboBox, final int index) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        comboBox.setEditable(true);
        comboBox.setSelectedIndex(index);
      }
    });
  }

  private JComboBoxMakeEditableAndSelectItemTask() {}
}
