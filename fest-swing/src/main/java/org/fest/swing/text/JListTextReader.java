/*
 * Created on Jul 29, 2010
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
package org.fest.swing.text;

import javax.swing.JList;
import javax.swing.ListModel;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.driver.BasicJListCellReader;
import org.fest.util.VisibleForTesting;

/**
 * Understands reading the text displayed in a <code>{@link JList}</code>.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class JListTextReader implements TextReader<JList> {

  private final JListCellReader cellReader;

  /**
   * Creates a new </code>{@link JListTextReader}</code>.
   */
  public JListTextReader() {
    this(new BasicJListCellReader());
  }

  @VisibleForTesting
  JListTextReader(JListCellReader cellReader) {
    this.cellReader = cellReader;
  }

  /**
   * Indicates that this reader supports {@code JList}s.
   * @return {@code JList.class}.
   */
  public Class<JList> supportedComponent() {
    return JList.class;
  }

  /**
   * Indicates whether the text representation of any of the elements in the given {@code JList} contains the given
   * text.
   * @param list the given {@code JList}.
   * @param text the given text.
   * @return {@code true} if the text representation of any of the elements the given {@code JList} contains the given
   * text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  public boolean containsText(JList list, String text) {
    ListModel model = list.getModel();
    int elementCount = model.getSize();
    for (int i = 0; i < elementCount; i++) {
      String elementText = cellReader.valueAt(list, i);
      if (elementText == null) continue;
      if (elementText.contains(text)) return true;
    }
    return false;
  }
}
