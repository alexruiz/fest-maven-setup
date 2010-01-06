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

import javax.swing.JButton;
import javax.swing.JList;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicComponentFinder#findByType(java.awt.Container, Class)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicComponentFinder_findByTypeInRoot_Test extends BasicComponentFinder_TestCase {
  
  private MyWindow windowTwo;

  @Test
  public void should_find_Component() {
    windowTwo = MyWindow.createNew(getClass());
    JButton button = finder.findByType(windowTwo, JButton.class);
    assertThat(button).isSameAs(windowTwo.button);
  }

  @Test
  public void should_throw_error_if_Component_not_found() {
    try {
      finder.findByType(window, JList.class);
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("type=javax.swing.JList");
    }
  }

  @Override void beforeReleasingScreenLock() {
    if (windowTwo != null) windowTwo.destroy();
  }
}
