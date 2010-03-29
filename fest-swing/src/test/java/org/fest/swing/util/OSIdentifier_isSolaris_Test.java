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
import static org.fest.swing.util.OSFamily.UNIX;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link OSIdentifier#isSolaris()}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class OSIdentifier_isSolaris_Test extends OSIdentifier_TestCase {

  private final String solaris;

  @Parameters
  public static Collection<Object[]> solaris() {
    return list(new Object[][] { { "sunos" }, { "SunOS" }, { "SUNOS" }, { "solaris" }, { "Solaris" }, { "SOLARIS" } });
  }

  public OSIdentifier_isSolaris_Test(String solaris) {
    this.solaris = solaris;
  }

  @Test
  public void should_return_Solaris_if_OS_name_starts_with_SunOS_or_Solaris() {
    new EasyMockTemplate(propertyReader) {
      protected void expectations() {
        expectOSName(solaris);
        expectNoMRJVersion();
      }

      protected void codeToTest() {
        OSIdentifier osIdentifier = new OSIdentifier(propertyReader);
        assertThat(osIdentifier.isSolaris()).isTrue();
        assertThat(osIdentifier.isX11()).isTrue();
        assertThat(osIdentifier.isHPUX()).isFalse();
        assertThat(osIdentifier.isLinux()).isFalse();
        assertThat(osIdentifier.isMacintosh()).isFalse();
        assertThat(osIdentifier.isOSX()).isFalse();
        assertThat(osIdentifier.isWindows()).isFalse();
        assertThat(osIdentifier.isWindows9x()).isFalse();
        assertThat(osIdentifier.isWindowsXP()).isFalse();
        assertThat(osIdentifier.osFamily()).isEqualTo(UNIX);
      }
    }.run();
  }
}
