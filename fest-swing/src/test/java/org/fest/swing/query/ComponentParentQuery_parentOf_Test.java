/*
 * Created on Aug 6, 2008
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

import java.awt.Container;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.*;
import org.fest.swing.test.core.*;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentParentQuery#parentOf(java.awt.Component)}</code>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentParentQuery_parentOf_Test extends SequentialEDTSafeTestCase {

  private MyButton button;

  @Override protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    button = window.button;
  }

  @Test
  public void should_return_parent_of_Component() {
    button.startRecording();
    Container parent = ComponentParentQuery.parentOf(button);
    assertThat(parent).isInstanceOf(JPanel.class);
    assertThat(parent.getName()).isEqualTo("null.contentPane");
    button.requireInvoked("getParent");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return GuiActionRunner.execute(new GuiQuery<MyWindow>() {
        @Override protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final MyButton button = new MyButton("A button");

    private MyWindow() {
      super(ComponentParentQuery_parentOf_Test.class);
      addComponents(button);
    }
  }

  private static class MyButton extends JButton {
    private static final long serialVersionUID = 1L;

    private boolean recording;
    private final MethodInvocations methodInvocations = new MethodInvocations();

    public MyButton(String text) {
      super(text);
    }

    @Override public Container getParent() {
      if (recording) methodInvocations.invoked("getParent");
      return super.getParent();
    }

    void startRecording() { recording = true; }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
