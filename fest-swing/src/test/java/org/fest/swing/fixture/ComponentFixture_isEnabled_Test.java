/*
 * Created on Jul 27, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.task.ComponentSetEnabledTask.setEnabled;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.query.ComponentEnabledQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.data.BooleanProvider;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link ComponentFixture#isEnabled()}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class ComponentFixture_isEnabled_Test extends RobotBasedTestCase {

  private final boolean enabled;

  private MyWindow window;

  @Parameters
  public static Collection<Object[]> booleans() {
    return list(BooleanProvider.booleans());
  }

  public ComponentFixture_isEnabled_Test(boolean enabled) {
    this.enabled = enabled;
  }

  @Override protected void onSetUp() {
    window = MyWindow.createNew();
    setEnabled(window, enabled);
    robot.waitForIdle();
  }

  @Test
  public void should_indicate_if_Component_is_enabled_or_not() {
    assertThat(ComponentEnabledQuery.isEnabled(window)).isEqualTo(enabled);
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

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
      super(ComponentFixture_isEnabled_Test.class);
    }
  }
}
