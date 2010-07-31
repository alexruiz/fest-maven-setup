/*
 * Created on Nov 20, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JList;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the number of items in a given
 * <code>{@link JList}</code>.
 *
 * @author Alex Ruiz
 */
@RunsInEDT
final class JListItemCountQuery {

  static int itemCountIn(final JList list) {
    return execute(new GuiQuery<Integer>() {
      @Override protected Integer executeInEDT() {
        return list.getModel().getSize();
      }
    });
  }

  private JListItemCountQuery() {}
}
