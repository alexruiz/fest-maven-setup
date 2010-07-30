/*
 * Created on Jul 30, 2009
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
package org.fest.swing.util;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.OSFamily.MAC;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link OSIdentifier#isMacintosh()}</code>.
 *
 * @author Alex Ruiz
 */
public class OSIdentifier_isMacintosh_Test extends OSIdentifier_TestCase {

  @Test
  public void should_return_Macintosh_if_MRJVersion_is_not_null() {
    new EasyMockTemplate(propertyReader) {
      @Override
      protected void expectations() {
        expectOSName("");
        expectSomeMRJVersion();
      }

      @Override
      protected void codeToTest() {
        OSIdentifier osIdentifier = new OSIdentifier(propertyReader);
        assertThat(osIdentifier.isMacintosh()).isTrue();
        assertThat(osIdentifier.isX11()).isTrue();
        assertThat(osIdentifier.isHPUX()).isFalse();
        assertThat(osIdentifier.isLinux()).isFalse();
        assertThat(osIdentifier.isOSX()).isFalse();
        assertThat(osIdentifier.isSolaris()).isFalse();
        assertThat(osIdentifier.isWindows()).isFalse();
        assertThat(osIdentifier.isWindows9x()).isFalse();
        assertThat(osIdentifier.isWindowsXP()).isFalse();
        assertThat(osIdentifier.osFamily()).isEqualTo(MAC);
      }
    }.run();
  }

}
