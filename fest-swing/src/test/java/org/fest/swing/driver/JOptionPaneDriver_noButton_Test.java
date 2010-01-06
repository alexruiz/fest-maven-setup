/*
 * Created on Mar 11, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.test.swing.JOptionPaneLauncher.launch;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.junit.Test;

/**
 * Tests for <code>{@link JOptionPaneDriver#noButton(JOptionPane)}</code>.
 *
 * @author Alex Ruiz
 */
public class JOptionPaneDriver_noButton_Test extends JOptionPaneDriver_TestCase {

  @Test
  public void should_find_No_button() {
    JOptionPane optionPane = confirmMessage();
    launch(optionPane, title());
    JButton button = driver.noButton(optionPane);
    assertThatButtonHasText(button, "OptionPane.noButtonText");
  }
}
