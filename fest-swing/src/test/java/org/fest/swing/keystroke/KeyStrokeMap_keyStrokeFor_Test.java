/*
 * Created on May 16, 2009
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
package org.fest.swing.keystroke;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link KeyStrokeMap#keyStrokeFor(char)}</code>.
 *
 * @author Alex Ruiz
 */
public class KeyStrokeMap_keyStrokeFor_Test extends KeyStrokeMap_TestCase {

  @Test
  public void should_return_KeyStroke_from_char() {
    new EasyMockTemplate(provider) {
      @Override protected void expectations() {
        expect(provider.keyStrokeMappings()).andReturn(mappings);
      }

      @Override protected void codeToTest() {
        KeyStrokeMap.addKeyStrokesFrom(provider);
        assertThat(KeyStrokeMap.keyStrokeFor('A')).isSameAs(keyStroke);
      }
    }.run();
  }
}
