/*
 * Created on Jun 7, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JLabel;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.*;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of <code>{@link JLabel}</code>s in <code>{@link ContainerFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class ContainerFixtureJLabelLookupTest extends RobotBasedTestCase {

  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJLabelByType() {
    JLabelFixture label = fixture.label();
    assertThatFixtureHasCorrectJLabel(label);
  }

  @Test
  public void shouldFailIfJLabelCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.label);
      }
    });
    robot.waitForIdle();
    try {
      fixture.label();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher")
                                .contains("type=javax.swing.JLabel, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJLabelByName() {
    JLabelFixture label = fixture.label("readMeLabel");
    assertThatFixtureHasCorrectJLabel(label);
  }

  @Test
  public void shouldFailIfJLabelCannotBeFoundByName() {
    try {
      fixture.label("myLabel");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher")
                                .contains("name='myLabel', type=javax.swing.JLabel, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJLabelWithCustomMatcher() {
    JLabelFixture label = fixture.label(new GenericTypeMatcher<JLabel>(JLabel.class) {
      @Override
      protected boolean isMatching(JLabel l) {
        return "Read Me".equals(l.getText());
      }
    });
    assertThatFixtureHasCorrectJLabel(label);
  }

  private void assertThatFixtureHasCorrectJLabel(JLabelFixture labelFixture) {
    assertThat(labelFixture.component()).isSameAs(window.label);
  }

  @Test
  public void shouldFailIfJLabelCannotBeFoundWithCustomMatcher() {
    try {
      fixture.label(new GenericTypeMatcher<JLabel>(JLabel.class) {
        @Override
        protected boolean isMatching(JLabel l) {
          return false;
        }
      });
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher");
    }
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JLabel label = new JLabel("Read Me");

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJLabelLookupTest.class);
      label.setName("readMeLabel");
      addComponents(label);
    }
  }
}
