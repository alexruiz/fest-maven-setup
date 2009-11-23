/*
 * Created on Aug 11, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link JTableHeaderLocation#pointAt(javax.swing.table.JTableHeader, int)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JTableHeaderLocation_pointAtColumnByIndex_Test extends JTableHeaderLocation_TestCase {

  private final int index;

  @Parameters
  public static Collection<Object[]> indices() {
    return list(columnIndices());
  }

  public JTableHeaderLocation_pointAtColumnByIndex_Test(int index) {
    this.index = index;
  }

  @Test
  public void should_return_point_at_column() {
    showWindow();
    assertThat(pointAt(index)).isEqualTo(expectedPoint(index));
  }

}
