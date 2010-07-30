/*
 * Created on Mar 25, 2010
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
package org.fest.swing.util;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.OSFamily.WINDOWS;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link Platform#osFamily()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_osFamily_Test extends Platform_TestCase {

  @Test
  public void should_return_osFamily() {
    final OSIdentifier osIdentifier = createMock(OSIdentifier.class);
    Platform.initialize(osIdentifier, toolkitProvider);
    new EasyMockTemplate(osIdentifier) {
      @Override
      protected void expectations() {
        expect(osIdentifier.osFamily()).andReturn(WINDOWS);
      }

      @Override
      protected void codeToTest() {
        assertThat(Platform.osFamily()).isEqualTo(WINDOWS);
      }
    }.run();
  }
}
