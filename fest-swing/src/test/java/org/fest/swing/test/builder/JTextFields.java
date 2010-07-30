/*
 * Created on Aug 28, 2008
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
package org.fest.swing.test.builder;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTextField;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands creation of <code>{@link JTextField}</code>s.
 *
 * @author Alex Ruiz
 */
public final class JTextFields {

  private JTextFields() {}

  public static JTextFieldFactory textField() {
    return new JTextFieldFactory();
  }

  public static class JTextFieldFactory {
    int columns;
    String name;
    String text;

    public JTextFieldFactory withColumns(int newColumns) {
      columns = newColumns;
      return this;
    }

    public JTextFieldFactory withName(String newName) {
      name = newName;
      return this;
    }

    public JTextFieldFactory withText(String newText) {
      text = newText;
      return this;
    }

    @RunsInEDT
    public JTextField createNew() {
      return execute(new GuiQuery<JTextField>() {
        @Override
        protected JTextField executeInEDT() {
          JTextField textField = new JTextField();
          textField.setColumns(columns);
          textField.setName(name);
          textField.setText(text);
          return textField;
        }
      });
    }
  }
}