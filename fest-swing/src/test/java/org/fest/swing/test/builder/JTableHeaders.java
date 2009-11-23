/*
 * Created on Nov 26, 2008
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
package org.fest.swing.test.builder;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.table.JTableHeader;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands creation of <code>{@link JTableHeader}</code>.
 *
 * @author Alex Ruiz
 */
public final class JTableHeaders {

  private JTableHeaders() {}

  public static JTableHeaderFactory tableHeader() {
    return new JTableHeaderFactory();
  }
  
  public static class JTableHeaderFactory {
    @RunsInEDT
    public JTableHeader createNew() {
      return execute(new GuiQuery<JTableHeader>() {
        protected JTableHeader executeInEDT() {
         return new JTableHeader();
        }
      });
    }
  }

}
