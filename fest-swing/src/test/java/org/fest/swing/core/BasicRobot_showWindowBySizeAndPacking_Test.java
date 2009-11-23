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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.query.ComponentLocationOnScreenQuery.locationOnScreen;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JWindow;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link BasicRobot#showWindow(java.awt.Window, java.awt.Dimension, boolean)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicRobot_showWindowBySizeAndPacking_Test extends EDTSafeTestCase {

  private BasicRobot robot;
  
  @Before public void setUp() {
    robot = (BasicRobot) BasicRobot.robotWithNewAwtHierarchy();
  }
  
  @After public void tearDown() {
    robot.cleanUp();
  }

  @Test
  public void should_not_pack_window_as_specified() {
    // TODO split this test case in 2+
    Dimension size = new Dimension(100, 100);
    WindowToShow w = execute(new GuiQuery<WindowToShow>() {
      protected WindowToShow executeInEDT() {
        return new WindowToShow();
      }
    });
    robot.showWindow(w, size, false);
    assertThat(sizeOf(w)).isEqualTo(size);
    assertThat(w.wasPacked()).isFalse();
    assertThat(locationOnScreen(w)).isEqualTo(new Point(0, 0));
  }

  private static class WindowToShow extends JWindow {
    private static final long serialVersionUID = 1L;
    private boolean packed;

    @Override public void pack() {
      packed = true;
      super.pack();
    }

    boolean wasPacked() { return packed; }
  }
}
