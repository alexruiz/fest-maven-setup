/*
 * Created on Nov 14, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Arrays.array;

import javax.swing.JComboBox;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.fest.swing.util.TextMatcher;
import org.junit.Test;

/**
 * Tests for <code>{@link JComboBoxMatchingItemQuery#matchingItemIndex(JComboBox, TextMatcher, JComboBoxCellReader)}</code>.
 *
 * @author Alex Ruiz
 */
public class JComboBoxMatchingItemQuery_matchingItemIndex_Test extends RobotBasedTestCase {

  private JComboBox comboBox;
  private JComboBoxCellReader cellReader;
  private TextMatcher matcher;

  @Override protected void onSetUp() {
    cellReader = new BasicJComboBoxCellReader();
    matcher = createMock(TextMatcher.class);
    MyWindow window = MyWindow.createNew();
    comboBox = window.comboBox;
  }

  @Test
  public void should_return_matching_indices() {
    new EasyMockTemplate(matcher) {
      protected void expectations() {
        expect(matcher.isMatching("aaa")).andReturn(false);
        expect(matcher.isMatching("bbb")).andReturn(true);
      }

      protected void codeToTest() {
        int matchingIndex = JComboBoxMatchingItemQuery.matchingItemIndex(comboBox, matcher, cellReader);
        assertThat(matchingIndex).isEqualTo(1);
      }
    }.run();
  }

  @Test
  public void should_return_negative_one_if_no_matching_indices_found() {
    new EasyMockTemplate(matcher) {
      protected void expectations() {
        expect(matcher.isMatching("aaa")).andReturn(false);
        expect(matcher.isMatching("bbb")).andReturn(false);
        expect(matcher.isMatching("ccc")).andReturn(false);
      }

      protected void codeToTest() {
        int matchingIndex = JComboBoxMatchingItemQuery.matchingItemIndex(comboBox, matcher, cellReader);
        assertThat(matchingIndex).isEqualTo(-1);
      }
    }.run();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JComboBox comboBox = new JComboBox(array("aaa", "bbb", "ccc"));

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JComboBoxMatchingItemQuery.class);
      addComponents(comboBox);
    }
  }

}
