/*
 * Created on May 16, 2009
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
import static org.fest.swing.util.OSFamily.LINUX;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link OSIdentifier#isLinux()}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class OSIdentifier_isLinux_Test extends OSIdentifier_TestCase {

  private final String linux;

  @Parameters
  public static Collection<Object[]> linux() {
    return list(new Object[][] { { "linux" }, { "Linux" }, { "LINUX" } });
  }

  public OSIdentifier_isLinux_Test(String linux) {
    this.linux = linux;
  }

  @Test
  public void shouldReturnLinuxIfOSNameIsEqualToLinux() {
    new EasyMockTemplate(propertyReader) {
      @Override
      protected void expectations() {
        expectOSName(linux);
        expectNoMRJVersion();
      }

      @Override
      protected void codeToTest() {
        OSIdentifier osIdentifier = new OSIdentifier(propertyReader);
        assertThat(osIdentifier.isLinux()).isTrue();
        assertThat(osIdentifier.isX11()).isTrue();
        assertThat(osIdentifier.isHPUX()).isFalse();
        assertThat(osIdentifier.isMacintosh()).isFalse();
        assertThat(osIdentifier.isOSX()).isFalse();
        assertThat(osIdentifier.isSolaris()).isFalse();
        assertThat(osIdentifier.isWindows()).isFalse();
        assertThat(osIdentifier.isWindows9x()).isFalse();
        assertThat(osIdentifier.isWindowsXP()).isFalse();
        assertThat(osIdentifier.osFamily()).isEqualTo(LINUX);
      }
    }.run();
  }
}
