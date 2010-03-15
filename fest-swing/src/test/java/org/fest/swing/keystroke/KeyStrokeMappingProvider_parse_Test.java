/*
 * Created on Mar 14, 2010
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
package org.fest.swing.keystroke;

import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.keystroke.KeyStrokeMapping.mapping;
import static org.fest.swing.keystroke.KeyStrokeMappingProvider.NO_MASK;
import static org.fest.swing.util.Platform.isWindows;

import java.util.Collection;

import org.fest.swing.exception.ParsingException;
import org.fest.swing.test.core.CommonAssertions;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link KeyStrokeMappingsParser#parse(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class KeyStrokeMappingProvider_parse_Test {

  private KeyStrokeMappingsParser parser;

  @Before
  public void setUp() {
    parser = new KeyStrokeMappingsParser();
  }

  @Test
  public void should_parse_mapping_file() {
    KeyStrokeMappingProvider mappingProvider = parser.parse("keyboard-mapping.txt");
    Collection<KeyStrokeMapping> mappings = mappingProvider.keyStrokeMappings();
    assertThat(mappings).contains(mapping('\b', VK_BACK_SPACE, NO_MASK),
                                  mapping('', VK_DELETE, NO_MASK),
                                  mapping('\n', VK_ENTER, NO_MASK),
                                  mapping('', VK_ESCAPE, NO_MASK),
                                  mapping('\t', VK_TAB, NO_MASK),
                                  mapping('a', VK_A, NO_MASK),
                                  mapping('A', VK_A, SHIFT_MASK),
                                  mapping(',', VK_COMMA, NO_MASK));
    if (isWindows()) assertThat(mappings).contains(mapping('\r', VK_ENTER, NO_MASK));
  }

  @Test
  public void should_throw_error_if_file_not_found() {
    try {
      parser.parse("abc.txt");
      CommonAssertions.failWhenExpectingException();
    } catch (ParsingException e) {
      assertThat(e.getMessage()).isEqualTo("Unable to open file abc.txt");
    }
  }
}
