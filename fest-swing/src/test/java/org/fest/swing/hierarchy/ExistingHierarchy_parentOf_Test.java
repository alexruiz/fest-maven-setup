/*
 * Created on Oct 20, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JTextFields.textField;

import javax.swing.JTextField;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.SequentialTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link ExistingHierarchy#parentOf(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ExistingHierarchy_parentOf_Test extends SequentialTestCase {

  private ParentFinder parentFinder;
  private TestWindow window;
  private JTextField textField;
  private ExistingHierarchy hierarchy;

  @Override protected void onSetUp() {
    parentFinder = MockParentFinder.mock();
    window = TestWindow.createNewWindow(getClass());
    textField = textField().createNew();
    hierarchy = new ExistingHierarchy(parentFinder, new ChildrenFinder());
  }

  @Override protected void onTearDown() {
    window.destroy();
  }

  @Test
  public void should_return_parent_of_Component() {
    new EasyMockTemplate(parentFinder) {
      @Override protected void expectations() {
        expect(parentFinder.parentOf(textField)).andReturn(window);
      }

      @Override protected void codeToTest() {
        assertThat(hierarchy.parentOf(textField)).isSameAs(window);
      }
    }.run();
  }
}
