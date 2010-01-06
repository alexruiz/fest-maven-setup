/*
 * Created on Jul 25, 2009
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
package org.fest.swing.core;

import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.*;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for <code>{@link BasicRobot#pressAndReleaseKey(int, int...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicRobot_pressAndReleaseKeyWithModifiers_Test extends BasicRobot_TestCase {

  @Test
  public void should_press_key_and_modifiers() {
    robot.focusAndWaitForFocusGain(window.textField);
    KeyPressRecorder recorder = KeyPressRecorder.attachTo(window.textField);
    robot.pressAndReleaseKey(VK_C, new int[] { CTRL_MASK, SHIFT_MASK });
    robot.waitForIdle();
    List<KeyAction> actions = recorder.actions;
    assertThat(actions).containsOnly(
        KeyAction.action(KEY_PRESSED,  VK_SHIFT),
        KeyAction.action(KEY_PRESSED,  VK_CONTROL),
        KeyAction.action(KEY_PRESSED,  VK_C),
        KeyAction.action(KEY_RELEASED, VK_C),
        KeyAction.action(KEY_RELEASED, VK_CONTROL),
        KeyAction.action(KEY_RELEASED, VK_SHIFT)
    );
  }

  private static class KeyPressRecorder extends KeyAdapter {
    final List<KeyAction> actions = new ArrayList<KeyAction>();

    static KeyPressRecorder attachTo(Component c) {
      KeyPressRecorder recorder = new KeyPressRecorder();
      c.addKeyListener(recorder);
      return recorder;
    }

    @Override public void keyPressed(KeyEvent e) {
      actions.add(KeyAction.action(KEY_PRESSED, e.getKeyCode()));
    }

    @Override public void keyReleased(KeyEvent e) {
      actions.add(KeyAction.action(KEY_RELEASED, e.getKeyCode()));
    }
  }

  private static class KeyAction {
    final int type;
    final int keyCode;

    static KeyAction action(int type, int keyCode) {
      return new KeyAction(type, keyCode);
    }

    private KeyAction(int type, int keyCode) {
      this.type = type;
      this.keyCode = keyCode;
    }

    @Override public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      final KeyAction other = (KeyAction) obj;
      if (type != other.type) return false;
      return keyCode == other.keyCode;
    }

    @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + type;
      result = prime * result + keyCode;
      return result;
    }

    @Override public String toString() {
      StringBuilder b = new StringBuilder();
      b.append("[type=").append(type).append(", ");
      b.append("keyCode=").append(keyCode).append("]");
      return b.toString();
    }
  }

}
