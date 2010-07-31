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

import javax.swing.JRadioButton;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.*;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of <code>{@link JRadioButton}</code>s in <code>{@link ContainerFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class ContainerFixtureJRadioButtonLookupTest extends RobotBasedTestCase {

  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJRadioButtonByType() {
    JRadioButtonFixture radioButton = fixture.radioButton();
    assertThatFixtureHasCorrectJRadioButton(radioButton);
  }

  @Test
  public void shouldFailIfJRadioButtonCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        window.remove(window.radioButton);
      }
    });
    robot.waitForIdle();
    try {
      fixture.radioButton();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher")
                                .contains("type=javax.swing.JRadioButton, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJRadioButtonByName() {
    JRadioButtonFixture radioButton = fixture.radioButton("selectMeRadioButton");
    assertThatFixtureHasCorrectJRadioButton(radioButton);
  }

  @Test
  public void shouldFailIfJRadioButtonCannotBeFoundByName() {
    try {
      fixture.radioButton("myRadioButton");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher")
                                .contains("name='myRadioButton', type=javax.swing.JRadioButton, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJRadioButtonWithCustomMatcher() {
    JRadioButtonFixture radioButton = fixture.radioButton(new GenericTypeMatcher<JRadioButton>(JRadioButton.class) {
      @Override protected boolean isMatching(JRadioButton r) {
        return "Select Me".equals(r.getText());
      }
    });
    assertThatFixtureHasCorrectJRadioButton(radioButton);
  }

  private void assertThatFixtureHasCorrectJRadioButton(JRadioButtonFixture radioButtonFixture) {
    assertThat(radioButtonFixture.component()).isSameAs(window.radioButton);
  }

  @Test
  public void shouldFailIfJRadioButtonCannotBeFoundWithCustomMatcher() {
    try {
      fixture.radioButton(new GenericTypeMatcher<JRadioButton>(JRadioButton.class) {
        @Override protected boolean isMatching(JRadioButton r) {
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

    final JRadioButton radioButton = new JRadioButton("Select Me");

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJRadioButtonLookupTest.class);
      radioButton.setName("selectMeRadioButton");
      addComponents(radioButton);
    }
  }
}
