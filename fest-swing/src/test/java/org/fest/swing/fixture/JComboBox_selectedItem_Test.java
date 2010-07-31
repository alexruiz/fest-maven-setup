/*
 * Created on Jul 26, 2010
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

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JComboBoxFixture#selectedItem()}</code>.
 *
 * @author Alex Ruiz
 */
public class JComboBox_selectedItem_Test extends JComboBoxFixture_TestCase {

  @Test
  public void should_require_item_count() {
    final String selectedItem = "Yoda";
    new EasyMockTemplate(driver()) {
      @Override protected void expectations() {
        expect(driver().selectedItemOf(target())).andReturn(selectedItem);
      }

      @Override protected void codeToTest() {
        assertThat(fixture().selectedItem()).isSameAs(selectedItem);
      }
    }.run();
  }

}
