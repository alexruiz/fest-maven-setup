/*
 * Created on Apr 28, 2009
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
package org.fest.swing.fixture;

import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.BorderFactory.createEmptyBorder;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import org.fest.swing.edt.GuiQuery;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Containers}</code>.
 *
 * @author Alex Ruiz
 */
public class ContainersTest {

  private MyPanel panel;

  @Before public void setUp() {
    panel = execute(new GuiQuery<MyPanel>() {
      protected MyPanel executeInEDT() {
        return new MyPanel();
      }
    });
  }

  @Test
  public void shouldShowContainerInJFrame() {
    FrameFixture frameFixture = null;
    try {
      frameFixture = Containers.showInFrame(panel);
      frameFixture.requireShowing();
    } finally {
      cleanUp(frameFixture);
    }
  }

  @Test
  public void shouldPlaceContainerInJFrameWithoutSowing() {
    FrameFixture frameFixture = null;
    try {
      frameFixture = Containers.frameFixtureFor(panel);
      frameFixture.requireNotVisible();
    } finally {
      cleanUp(frameFixture);
    }
  }

  private void cleanUp(FrameFixture frameFixture) {
    if (frameFixture != null) frameFixture.cleanUp();
  }

  private static class MyPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private final GridBagConstraints c = new GridBagConstraints();

    MyPanel() {
      setLayout(new GridBagLayout());
      setBorder(createEmptyBorder(10, 10, 10, 10));
      c.gridx = c.gridy = 0;
      addUsernameInput();
      nextLine();
      addPasswordInput();
    }

    private void addUsernameInput() {
      addInput("Username:", new JTextField(20));
    }

    private void addPasswordInput() {
      addInput("Password:", new JPasswordField(20));
    }

    private void addInput(String label, JComponent input) {
      add(new JLabel(label), c);
      c.gridx++;
      add(Box.createHorizontalStrut(10), c);
      c.gridx++;
      c.gridwidth = REMAINDER;
      add(input, c);
    }

    private void nextLine() {
      c.gridx = 0;
      c.gridwidth = 1;
      c.gridy++;
    }
  }
}
