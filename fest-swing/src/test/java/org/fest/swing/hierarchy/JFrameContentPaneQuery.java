/*
 * Created on Sep 09, 2007
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
package org.fest.swing.hierarchy;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Container;

import javax.swing.JFrame;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the content pane object of a given
 * <code>{@link JFrame}</code>.
 * @see JFrame#getContentPane()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunsInEDT
final class JFrameContentPaneQuery  {

  static Container contentPaneOf(final JFrame frame) {
    return execute(new GuiQuery<Container>() {
      @Override protected Container executeInEDT() {
        return frame.getContentPane();
      }
    });
  }

  private JFrameContentPaneQuery() {}
}