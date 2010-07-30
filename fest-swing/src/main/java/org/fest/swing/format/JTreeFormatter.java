/*
 * Created on Dec 23, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.format;

import static java.lang.String.valueOf;
import static javax.swing.tree.TreeSelectionModel.*;
import static org.fest.util.Strings.*;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.*;

import org.fest.util.Arrays;

/**
 * Understands a formatter for <code>{@link JTree}</code>s.
 *
 * @author Alex Ruiz
 */
public class JTreeFormatter extends ComponentFormatterTemplate {

  private static final String[] EMPTY = new String[0];

  private static final IntEnum SELECTION_MODES = new IntEnum();
  static {
    SELECTION_MODES.put(SINGLE_TREE_SELECTION,        "SINGLE_TREE_SELECTION")
                   .put(CONTIGUOUS_TREE_SELECTION,    "CONTIGUOUS_TREE_SELECTION")
                   .put(DISCONTIGUOUS_TREE_SELECTION, "DISCONTIGUOUS_TREE_SELECTION");
  }

  /**
   * Returns the <code>String</code> representation of the given <code>{@link Component}</code>, which should be a
   * <code>{@link JTree}</code> (or subclass.)
   * @param c the given {@code Component}.
   * @return the <code>String</code> representation of the given <code>JTree</code>.
   */
  @Override
  protected String doFormat(Component c) {
    JTree tree = (JTree)c;
    return concat(
        tree.getClass().getName(), "[",
        "name=",           quote(tree.getName()),               ", ",
        "selectionCount=", valueOf(tree.getSelectionCount()),   ", ",
        "selectionPaths=", Arrays.format(selectionPaths(tree)), ", ",
        "selectionMode=",  selectionMode(tree),                 ", ",
        "enabled=",        valueOf(tree.isEnabled()),           ", ",
        "visible=",        valueOf(tree.isVisible()),           ", ",
        "showing=",        valueOf(tree.isShowing()),
        "]"
    );
  }

  private String[] selectionPaths(JTree tree) {
    TreePath[] paths = tree.getSelectionPaths();
    if (paths == null) return EMPTY;
    int count = paths.length;
    if (count == 0) return EMPTY;
    String[] pathArray = new String[count];
    for (int i = 0; i < count; i++) {
      TreePath path = paths[i];
      pathArray[i] = path != null ? path.toString() : null;
    }
    return pathArray;
  }

  private String selectionMode(JTree tree) {
    TreeSelectionModel model = tree.getSelectionModel();
    return SELECTION_MODES.get(model.getSelectionMode());
  }

  /**
   * Indicates that this formatter supports <code>{@link JTree}</code> only.
   * @return <code>JTree.class</code>.
   */
  public Class<? extends Component> targetType() {
    return JTree.class;
  }
}
