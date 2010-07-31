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

import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.*;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import javax.swing.KeyStroke;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link KeyStrokeMap#charFor(javax.swing.KeyStroke)}</code>.
 *
 * @author Alex Ruiz
 */
public class KeyStrokeMap_charFor_Test extends KeyStrokeMap_TestCase {

  @Test
  public void should_return_char_for_KeyStroke() {
    new EasyMockTemplate(provider) {
      @Override protected void expectations() {
        expect(provider.keyStrokeMappings()).andReturn(mappings);
      }

      @Override protected void codeToTest() {
        KeyStrokeMap.addKeyStrokesFrom(provider);
        assertThat(KeyStrokeMap.charFor(keyStroke)).isEqualTo('A');
      }
    }.run();
  }

  @Test
  public void should_strip_modifiers_except_Shift_if_char_for_KeyStroke_not_found() {
    final Character character = 'a';
    final KeyStrokeMapCollection maps = mockKeyStrokeMapCollection();
    KeyStrokeMap.updateKeyStrokeMapCollection(maps);
    new EasyMockTemplate(maps) {
      @Override protected void expectations() {
        expect(maps.charFor(keyStroke)).andReturn(null);
        expect(maps.charFor(removeModifiersExceptShift(keyStroke))).andReturn(character);
      }

      @Override protected void codeToTest() {
        assertThat(KeyStrokeMap.charFor(keyStroke)).isEqualTo(character);
      }
    }.run();
  }

  @Test
  public void should_return_undefined_character_if_char_for_KeyStroke_not_found() {
    final KeyStrokeMapCollection maps = mockKeyStrokeMapCollection();
    KeyStrokeMap.updateKeyStrokeMapCollection(maps);
    new EasyMockTemplate(maps) {
      @Override protected void expectations() {
        expect(maps.charFor(keyStroke)).andReturn(null);
        expect(maps.charFor(removeModifiersExceptShift(keyStroke))).andReturn(null);
      }

      @Override protected void codeToTest() {
        assertThat(KeyStrokeMap.charFor(keyStroke)).isEqualTo(CHAR_UNDEFINED);
      }
    }.run();
  }

  private KeyStrokeMapCollection mockKeyStrokeMapCollection() {
    return createMock(KeyStrokeMapCollection.class);
  }

  private static KeyStroke removeModifiersExceptShift(KeyStroke base) {
    final int mask = base.getModifiers() & ~SHIFT_MASK;
    return KeyStroke.getKeyStroke(VK_A, mask);
  }
}
