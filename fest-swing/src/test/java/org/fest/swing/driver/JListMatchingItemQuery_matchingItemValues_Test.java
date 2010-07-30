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

import java.util.List;

import javax.swing.JList;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.util.TextMatcher;
import org.junit.Test;

/**
 * Tests for <code>{@link JListMatchingItemQuery#matchingItemValues(JList, TextMatcher, JListCellReader)}</code>.
 *
 * @author Alex Ruiz
 */
public class JListMatchingItemQuery_matchingItemValues_Test extends JListMatchingItemQuery_TestCase {

  @Test
  public void should_return_values_of_matching_items() {
    final TextMatcher matcher = mockTextMatcher();
    new EasyMockTemplate(matcher) {
      @Override
      protected void expectations() {
        expect(matcher.isMatching("Yoda")).andReturn(false);
        expect(matcher.isMatching("Luke")).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        List<String> values = JListMatchingItemQuery.matchingItemValues(list, matcher, cellReader);
        assertThat(values).hasSize(1).containsOnly("Luke");
      }
    }.run();
  }

  @Test
  public void should_return_empty_list_if_matching_values_not_found() {
    final TextMatcher matcher = mockTextMatcher();
    new EasyMockTemplate(matcher) {
      @Override
      protected void expectations() {
        expect(matcher.isMatching("Yoda")).andReturn(false);
        expect(matcher.isMatching("Luke")).andReturn(false);
      }

      @Override
      protected void codeToTest() {
        List<String> values = JListMatchingItemQuery.matchingItemValues(list, matcher, cellReader);
        assertThat(values).isEmpty();
      }
    }.run();
  }
}
