/*
 * Created on Aug 9, 2008
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
package org.fest.swing.query;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.*;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentShowingQuery#isShowing(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentShowingQuery_isShowing_Test extends SequentialEDTSafeTestCase {

  private MyWindow window;

  @Override protected void onSetUp() {
    window = MyWindow.createNew();
  }

  @Override protected void onTearDown() {
    window.destroy();
  }

  @Test
  public void should_return_false_Component_is_not_showing() {
    window.startRecording();
    assertThat(ComponentShowingQuery.isShowing(window)).isFalse();
    window.requireInvoked("isShowing");
  }

  @Test
  public void should_return_true_if_Component_is_showing() {
    window.display();
    window.startRecording();
    assertThat(ComponentShowingQuery.isShowing(window)).isTrue();
    window.requireInvoked("isShowing");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    private boolean recording;
    private final MethodInvocations methodInvocations = new MethodInvocations();

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ComponentShowingQuery_isShowing_Test.class);
    }

    @Override public boolean isShowing() {
      if (recording) methodInvocations.invoked("isShowing");
      return super.isShowing();
    }

    void startRecording() { recording = true; }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
