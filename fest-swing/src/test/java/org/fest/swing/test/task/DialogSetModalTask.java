/*
 * Created on Aug 9, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.test.task;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Dialog;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that makes a <code>{@link Dialog}</code> modal. This task is <code>not</code> executed in the
 * event dispatch thread.
 * 
 * @author Alex Ruiz
 */
public final class DialogSetModalTask {

  @RunsInEDT
  public static void makeModal(final Dialog d, final boolean modal) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        d.setModal(modal);
      }
    });
  }
  
  private DialogSetModalTask() {}
}
