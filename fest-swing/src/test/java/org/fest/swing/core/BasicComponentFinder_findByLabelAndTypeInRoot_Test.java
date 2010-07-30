/*
 * Created on Jul 24, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.*;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicComponentFinder#findByLabel(java.awt.Container, String, Class)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicComponentFinder_findByLabelAndTypeInRoot_Test extends BasicComponentFinder_TestCase {

  private MyWindow windowTwo;

  @Test
  public void should_find_Component() {
    windowTwo = MyWindow.createNew(getClass());
    JButton button = finder.findByLabel(window, "A Label", JButton.class);
    assertThat(button).isSameAs(window.button);
  }

  @Test
  public void should_throw_error_if_Component_not_found() {
    try {
      finder.findByLabel(window, "list", JLabel.class);
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("label='list'")
                                .contains("type=javax.swing.JLabel");
    }
  }

  @Test
  public void should_throw_error_if_Component_found_by_label_Container_but_not_by_type() {
    try {
      finder.findByLabel(window, "button", JLabel.class);
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("label='button'")
                                .contains("type=javax.swing.JLabel");
    }
  }

  @Override void beforeReleasingScreenLock() {
    if (windowTwo != null) windowTwo.destroy();
  }
}
