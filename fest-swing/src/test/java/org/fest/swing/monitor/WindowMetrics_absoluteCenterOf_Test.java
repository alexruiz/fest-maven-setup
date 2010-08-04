/*
 * Created on Jul 31, 2009
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
package org.fest.swing.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowMetrics#absoluteCenterOf(Window)}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowMetrics_absoluteCenterOf_Test extends EDTSafeTestCase {

  private int x;
  private int y;
  private int left;
  private int right;
  private int top;
  private int bottom;
  private int width;
  private int height;
  private Window window;

  @Before
  public void setUp() {
    x = 600;
    y = 300;
    left = 20;
    right = 30;
    top = 40;
    bottom = 10;
    width = 200;
    height = 100;
    window = createMock(Window.class);
  }

  @Test
  public void should_calculate_center() {
    new EasyMockTemplate(window) {
      @Override protected void expectations() {
        expect(window.getInsets()).atLeastOnce().andReturn(new Insets(top, left, bottom, right));
        expect(window.getX()).atLeastOnce().andReturn(x);
        expect(window.getY()).atLeastOnce().andReturn(y);
        expect(window.getWidth()).atLeastOnce().andReturn(width);
        expect(window.getHeight()).atLeastOnce().andReturn(height);
      }

      @Override protected void codeToTest() {
        int expectedX = x + left + (width - (left + right));
        int expectedY = y + top + (height - (top + bottom));
        assertThat(WindowMetrics.absoluteCenterOf(window)).isEqualTo(new Point(expectedX, expectedY));
      }
    };
  }
}
