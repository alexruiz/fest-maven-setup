/*
 * Created on Mar 26, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.keystroke;

import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;

import java.util.Locale;

import javax.swing.KeyStroke;

/**
 * Understands a collection of <code>{@link KeyStrokeMapping}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class KeyStrokeMap {

  private static KeyStrokeMapCollection maps = new KeyStrokeMapCollection();

  static {
    reloadFromLocale();
  }

  /**
   * Reloads the key stroke mappings for the language from the default locale.
   */
  public static void reloadFromLocale() {
    KeyStrokeMappingProviderPicker picker = new KeyStrokeMappingProviderPicker();
    maps.clear();
    addKeyStrokesFrom(picker.providerFor(Locale.getDefault()));
  }

  // for testing only
  static void updateKeyStrokeMapCollection(KeyStrokeMapCollection c) {
    maps = c;
  }

  /**
   * Adds the collection of <code>{@link KeyStrokeMapping}</code>s from the given
   * <code>{@link KeyStrokeMappingProvider}</code> to this map.
   * @param provider the given <code>KeyStrokeMappingProvider</code>.
   */
  public static void addKeyStrokesFrom(KeyStrokeMappingProvider provider) {
    for (KeyStrokeMapping entry : provider.keyStrokeMappings())
      add(entry.character(), entry.keyStroke());
  }

  private static void add(Character character, KeyStroke keyStroke) {
    maps.add(character, keyStroke);
  }

  /**
   * Removes all the character-<code>{@link KeyStroke}</code> mappings.
   */
  public static void clearKeyStrokes() {
    maps.clear();
  }

  /**
   * Indicates whether <code>{@link KeyStrokeMap}</code> has mappings or not.
   * @return <code>true</code> if it has mappings, <code>false</code> otherwise.
   */
  public static boolean hasKeyStrokes() {
    return !maps.isEmpty();
  }

  /**
   * Returns the <code>{@link KeyStroke}</code> corresponding to the given character, as best we can guess it, or
   * <code>null</code> if we don't know how to generate it.
   * @param character the given character.
   * @return the key code-based <code>KeyStroke</code> corresponding to the given character, or <code>null</code> if
   * we cannot generate it.
   */
  public static KeyStroke keyStrokeFor(char character) {
    return maps.keyStrokeFor(character);
  }

  /**
   * Given a <code>{@link KeyStroke}</code>, returns the equivalent character. Key strokes are defined properly for
   * US keyboards only. To contribute your own, please add them using the method
   * <code>{@link #addKeyStrokesFrom(KeyStrokeMappingProvider)}</code>.
   * @param keyStroke the given <code>KeyStroke</code>.
   * @return KeyEvent.VK_UNDEFINED if the result is unknown.
   */
  public static char charFor(KeyStroke keyStroke) {
    Character character = maps.charFor(keyStroke);
    // Try again, but strip all modifiers but shift
    if (character == null) character = charWithoutModifiersButShift(keyStroke);
    if (character == null) return CHAR_UNDEFINED;
    return character.charValue();
  }

  private static Character charWithoutModifiersButShift(KeyStroke keyStroke) {
    int mask = keyStroke.getModifiers() & ~SHIFT_MASK;
    return maps.charFor(KeyStroke.getKeyStroke(keyStroke.getKeyCode(), mask));
  }

  private KeyStrokeMap() {}
}
