/*
 * Created on Jun 2, 2009
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

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.TestComponentFinders.newComponentFinderMock;
import static org.fest.swing.core.UnexpectedJOptionPaneFinder.OPTION_PANE_MATCHER;
import static org.fest.swing.test.builder.JOptionPanes.optionPane;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link UnexpectedJOptionPaneFinder#requireNoJOptionPaneIsShowing}</code>.
 *
 * @author Alex Ruiz
 */
public class UnexpectedJOptionPaneFinder_requireNoJOptionPaneIsShowing_Test extends EDTSafeTestCase {

  private ComponentFinder delegate;
  private UnexpectedJOptionPaneFinder finder;

  @Before public void setUp() {
    delegate = newComponentFinderMock();
    finder = new UnexpectedJOptionPaneFinder(delegate);
  }

  @Test
  public void should_pass_if_there_are_not_any_JOptionPanes_showing() {
    new EasyMockTemplate(delegate) {
      protected void expectations() {
        expect(delegate.findAll(OPTION_PANE_MATCHER)).andReturn(new ArrayList<Component>());
      }

      protected void codeToTest() {
        finder.requireNoJOptionPaneIsShowing();
      }
    }.run();
  }

  @Test
  public void should_fail_if_there_is_a_JOptionPane_showing() {
    new EasyMockTemplate(delegate) {
      protected void expectations() {
        List<Component> found = new ArrayList<Component>();
        found.add(optionPane().createNew());
        expect(delegate.findAll(OPTION_PANE_MATCHER)).andReturn(found);
      }

      protected void codeToTest() {
        try {
          finder.requireNoJOptionPaneIsShowing();
          failWhenExpectingException();
        } catch (AssertionError e) {
          assertThat(e.getMessage()).contains("Expecting no JOptionPane to be showing");
        }
      }
    }.run();
  }
}
