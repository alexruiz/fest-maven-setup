/*
 * Created on Dec 27, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JTreeFixture#valueAt(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeFixture_valueAtRow_Test extends JTreeFixture_TestCase {

  @Test
  public void should_return_node_text() {
    final String nodeText = "hello";
    final int row = 6;
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().nodeValue(target(), row)).andReturn(nodeText);
      }

      protected void codeToTest() {
        String value = fixture().valueAt(row);
        assertThat(value).isEqualTo(nodeText);
      }
    }.run();
  }
}
