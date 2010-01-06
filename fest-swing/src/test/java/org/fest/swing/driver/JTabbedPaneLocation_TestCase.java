/*
 * Created on Apr 5, 2008
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

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;

/**
 * Base test case for <code>{@link JTabbedPaneLocation}</code>.
 *
 * @author Yvonne Wang
 */
public abstract class JTabbedPaneLocation_TestCase extends RobotBasedTestCase {

  JTabbedPane tabbedPane;
  JTabbedPaneLocation location;

  @Override protected final void onSetUp() {
    MyWindow window = MyWindow.createNew(getClass());
    tabbedPane = window.tabbedPane;
    location = new JTabbedPaneLocation();
  }

  static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JTabbedPane tabbedPane = new JTabbedPane();

    @RunsInEDT
    static MyWindow createNew(final Class<?> testClass) {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow(testClass);
        }
      });
    }

    private MyWindow(Class<?> testClass) {
      super(testClass);
      tabbedPane.addTab("one", new JPanel());
      tabbedPane.addTab("two", new JPanel());
      tabbedPane.setPreferredSize(new Dimension(200, 100));
      addComponents(tabbedPane);
    }
  }
}
