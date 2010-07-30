/*
 * Created on Mar 16, 2008
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
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableHeaderFixture#showPopupMenuAt(int)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableHeaderFixture_showPopupMenuAt_byIndex_Test extends JTableHeaderFixture_showPopupMenuAt_TestCase {

  @Test
  public void should_show_JPopupMenu() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), 1)).andReturn(popupMenu());
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture().showPopupMenuAt(1);
        assertThatJPopupMenuWasShown(result);
      }
    }.run();
  }
}
