/*
 * Created on Jul 29, 2010
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
package org.fest.swing.text;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import javax.swing.JList;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.swing.test.swing.TestListModel;
import org.junit.*;

/**
 * Tests for <code>{@link JListTextReader#checkContainsText(JList, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JListTextReader_checkContainsText_Test extends EDTSafeTestCase {

  private JList list;
  private TestListModel listModel;
  private JListTextReader reader;

  @Before
  public void setUp() {
    list = createMock(JList.class);
    listModel = new TestListModel(null, "Yoda", "Luke", "Leia");
    reader = new JListTextReader(new TestJListCellReader());
  }

  @Test
  public void should_return_false_if_text_in_JList_does_not_contain_given_String() {
    new EasyMockTemplate(list) {
      @Override protected void expectations() {
        expect(list.getModel()).andReturn(listModel).atLeastOnce();
      }

      @Override protected void codeToTest() {
        assertThat(reader.checkContainsText(list, "Han")).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_true_if_text_in_JList_contains_given_String() {
    new EasyMockTemplate(list) {
      @Override protected void expectations() {
        expect(list.getModel()).andReturn(listModel).atLeastOnce();
      }

      @Override protected void codeToTest() {
        assertThat(reader.checkContainsText(list, "Yo")).isTrue();
      }
    }.run();
  }

  private static class TestJListCellReader implements JListCellReader {
    public String valueAt(JList list, int index) {
      Object element = list.getModel().getElementAt(index);
      return element != null ? element.toString() : null;
    }
  }
}
