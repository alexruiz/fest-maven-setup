/*
 * Created on Jul 16, 2009
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
package org.fest.swing.driver;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.Robots.singletonRobotMock;

import org.fest.swing.cell.JTreeCellReader;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link JTreeDriver#cellReader(JTreeCellReader)}</code>.
 *
 * @author Alex Ruiz
 */
public class JTreeDriver_cellReader_Test {

  private static JTreeDriver driver;

  @BeforeClass
  public static void setUpOnce() {
    driver = new JTreeDriver(singletonRobotMock());
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_cellReader_is_null() {
    driver.cellReader(null);
  }

  @Test
  public void should_set_cellReader() {
    JTreeCellReader cellReader = createMock(JTreeCellReader.class);
    driver.cellReader(cellReader);
    assertThat(driver.cellReader()).isSameAs(cellReader);
  }

}
