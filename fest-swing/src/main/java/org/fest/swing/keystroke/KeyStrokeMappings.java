/*
 * Created on Mar 15, 2010
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

import static java.awt.event.KeyEvent.*;
import static java.util.Collections.unmodifiableList;
import static org.fest.swing.keystroke.KeyStrokeMapping.mapping;
import static org.fest.swing.keystroke.KeyStrokeMappingProvider.NO_MASK;
import static org.fest.swing.util.Platform.isWindows;

import java.util.*;

/**
 * Understands utility methods related to <code>{@link KeyStrokeMapping}</code>s.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
final class KeyStrokeMappings {

  static Collection<KeyStrokeMapping> defaultMappings() {
    List<KeyStrokeMapping> mappings = new ArrayList<KeyStrokeMapping>();
    mappings.add(mapping('\b', VK_BACK_SPACE, NO_MASK));
    mappings.add(mapping('', VK_DELETE, NO_MASK));
    mappings.add(mapping('\n', VK_ENTER, NO_MASK));
    if (isWindows()) mappings.add(mapping('\r', VK_ENTER, NO_MASK));
    mappings.add(mapping('', VK_ESCAPE, NO_MASK));
    mappings.add(mapping('\t', VK_TAB, NO_MASK));
    return unmodifiableList(mappings);
  }

  private KeyStrokeMappings() {}
}
