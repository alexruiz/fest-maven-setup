/*
 * Created on Apr 3, 2010
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
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.TestURLs.singletonURL;

import java.net.URL;

import org.junit.Test;

/**
 * Tests for <code>{@link JAppletDriver#documentBaseOf(javax.swing.JApplet)}</code>.
 *
 * @author Alex Ruiz
 */
public class JAppletDriver_documentOf_Test extends JAppletDriver_TestCase {

  @Test
  public void should_return_document_base() throws Exception {
    URL url = singletonURL();
    applet().updateDocumentBase(url);
    URL result = driver().documentBaseOf(applet());
    assertThat(result).isSameAs(url);
    assertThat(applet().wasMethodCalledInEDT("getDocumentBase")).isTrue();
  }
}
