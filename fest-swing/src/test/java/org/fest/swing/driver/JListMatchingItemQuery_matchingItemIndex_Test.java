/*
 * Created on Nov 3, 2008
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
package org.fest.swing.driver;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JList;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.TextMatcher;
import org.junit.Test;

/**
 * Tests for <code>{@link JListMatchingItemQuery#matchingItemIndex(JList, TextMatcher, JListCellReader)}</code>.
 *
 * @author Alex Ruiz
 */
public class JListMatchingItemQuery_matchingItemIndex_Test extends JListMatchingItemQuery_TestCase {

  @Test
  public void should_return_index_of_matching_item() {
    final TextMatcher matcher = mockTextMatcher();
    new EasyMockTemplate(matcher) {
      @Override
      protected void expectations() {
        expect(matcher.isMatching("Yoda")).andReturn(false);
        expect(matcher.isMatching("Luke")).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        assertThat(matchingItemIndex(matcher)).isEqualTo(1);
      }
    }.run();
  }

  @Test
  public void should_return_negative_one_if_matching_item_not_found() {
    final TextMatcher matcher = mockTextMatcher();
    new EasyMockTemplate(matcher) {
      @Override
      protected void expectations() {
        expect(matcher.isMatching("Yoda")).andReturn(false);
        expect(matcher.isMatching("Luke")).andReturn(false);
      }

      @Override
      protected void codeToTest() {
        assertThat(matchingItemIndex(matcher)).isEqualTo(-1);
      }
    }.run();
  }

  @RunsInEDT
  private int matchingItemIndex(final TextMatcher matcher) {
    return execute(new GuiQuery<Integer>() {
      @Override
      protected Integer executeInEDT() {
        return JListMatchingItemQuery.matchingItemIndex(list, matcher, cellReader);
      }
    });
  }
}
