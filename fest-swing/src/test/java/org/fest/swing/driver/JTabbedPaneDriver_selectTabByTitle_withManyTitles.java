/*
 * Created on Feb 25, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.util.Collections.list;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link JTabbedPaneDriver#selectTab(javax.swing.JTabbedPane, String)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JTabbedPaneDriver_selectTabByTitle_withManyTitles extends JTabbedPaneDriver_TestCase {

  private final String title;
  private final int index;

  @Parameters
  public static Collection<Object[]> indices() {
    return list(new Object[][] {
        { "One", 0 },
        { "Two", 1 }
    });
  }

  public JTabbedPaneDriver_selectTabByTitle_withManyTitles(String title, int index) {
    this.title = title;
    this.index = index;
  }

  @Test
  public void should_select_tab_with_equal_title() {
    showWindow();
    driver.selectTab(tabbedPane, title);
    assertThatSelectedTabIndexIs(index);
  }
}
