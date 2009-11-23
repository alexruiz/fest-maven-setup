/*
 * Created on Aug 4, 2009
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
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.awt.AWT.centerOf;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Collections.list;

import java.awt.Point;
import java.util.Collection;

import org.fest.swing.test.recorder.ClickRecorder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Base test case for implementations of 
 * <code>{@link InputEventGenerator#pressMouse(Point, int)}</code> and 
 * <code>{@link InputEventGenerator#releaseMouse(int)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public abstract class InputEventGenerator_pressMouse_TestCase extends InputEventGenerator_TestCase {

  private final MouseButton button;
  private final int buttonMask;
  
  @Parameters
  public static Collection<Object[]> mouseButtons() {
    return list(MouseButtonProvider.mouseButtons());
  }
  
  public InputEventGenerator_pressMouse_TestCase(MouseButton button) {
    this.button = button;
    buttonMask = button.mask;
  }
  
  @Test
  public void should_press_mouse_button_at_given_point_and_release_mouse_button() {
    Point center = centerOf(window);
    eventGenerator.moveMouse(window, center.x, center.y); // indirectly testing mouseMove :)
    ClickRecorder recorder = ClickRecorder.attachTo(window);
    eventGenerator.pressMouse(buttonMask);
    eventGenerator.releaseMouse(buttonMask);
    pause(DELAY);
    assertThat(recorder.clicked(button));
  }
}
