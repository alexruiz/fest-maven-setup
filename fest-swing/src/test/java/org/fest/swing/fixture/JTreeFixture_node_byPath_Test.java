/*
 * Created on Dec 26, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JTreeFixture#node(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeFixture_node_byPath_Test extends JTreeFixture_TestCase {

  @Test
  public void should_return_row() {
    final String path = "root";
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().validatePath(target(), path);
      }

      protected void codeToTest() {
        JTreeNodeFixture nodeFixture = fixture().node(path);
        assertThat(nodeFixture).isInstanceOf(JTreePathFixture.class);
        JTreePathFixture pathFixture = (JTreePathFixture) nodeFixture;
        assertThat(pathFixture.path()).isEqualTo(path);
      }
    }.run();
  }
}
