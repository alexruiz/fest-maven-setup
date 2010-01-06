/*
 * Created on Aug 5, 2009
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

import static java.awt.event.InputEvent.*;
import static org.fest.swing.core.MouseButton.*;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Base test case for <code>{@link MouseButton}</code>.
 *
 * @author Alex Ruiz 
 */
@RunWith(Parameterized.class)
public abstract class MouseButton_TestCase {

  final MouseButton button;
  final int mask;

  @Parameters
  public static Collection<Object[]> mouseButtonMasks() {
    return list(new Object[][] { 
        { LEFT_BUTTON, BUTTON1_MASK },
        { MIDDLE_BUTTON, BUTTON2_MASK },
        { RIGHT_BUTTON, BUTTON3_MASK },
    });
  }

  public MouseButton_TestCase(MouseButton button, int mask) {
    this.button = button;
    this.mask = mask;
  }
}
