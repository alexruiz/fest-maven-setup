/*
 * Created on May 20, 2009
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
package org.fest.swing.test.swing;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.event.*;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands a <code>{@link JFrame}</code> that launches a <code>{@link JFileChooser}</code> using a configurable
 * delay.
 *
 * @author Alex Ruiz
 */
public class JFileChooserLauncherWindow extends TestWindow {
  private static final long serialVersionUID = 1L;

  @RunsInEDT
  public static JFileChooserLauncherWindow createNew(final Class<?> testClass) {
    return execute(new GuiQuery<JFileChooserLauncherWindow>() {
      protected JFileChooserLauncherWindow executeInEDT() {
        return new JFileChooserLauncherWindow(testClass);
      }
    });
  }

  private int launchDelay;

  final JFileChooser fileChooser = new JFileChooser();

  private JFileChooserLauncherWindow(Class<?> testClass) {
    super(testClass);
    setUp();
  }

  private void setUp() {
    add(button());
    fileChooser.setName("fileChooser");
    fileChooser.setDialogTitle("Launched JFileChooser");
  }

  @RunsInCurrentThread
  private JButton button() {
    JButton button = new JButton("Browse");
    button.setName("browse");
    button.addMouseListener(new MouseAdapter() {
      @Override public void mousePressed(MouseEvent e) {
        launchJFileChooser();
      }
    });
    return button;
  }

  @RunsInCurrentThread
  private void launchJFileChooser() {
    if (launchDelay == 0) {
      showFileChooser();
      return;
    }
    start(new Timer(launchDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showFileChooser();
      }
    }));
  }

  @RunsInCurrentThread
  private void start(Timer timer) {
    timer.setRepeats(false);
    timer.start();
  }

  @RunsInCurrentThread
  private void showFileChooser() {
    fileChooser.showOpenDialog(JFileChooserLauncherWindow.this);
  }

  public void launchDelay(int newLaunchDelay) {
    this.launchDelay = newLaunchDelay;
  }

  public JFileChooser fileChooser() {
    return fileChooser;
  }
}