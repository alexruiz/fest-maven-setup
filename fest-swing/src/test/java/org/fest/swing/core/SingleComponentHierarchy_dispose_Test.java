/*
 * Created on Aug 5, 2009
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
package org.fest.swing.core;

import static org.easymock.EasyMock.expectLastCall;
import static org.fest.swing.test.builder.JFrames.frame;

import javax.swing.JFrame;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.hierarchy.SingleComponentHierarchy;
import org.junit.Test;

/**
 * Tests for <code>{@link SingleComponentHierarchy#dispose(java.awt.Window)}</code>.
 *
 * @author Alex Ruiz
 */
public class SingleComponentHierarchy_dispose_Test extends SingleComponentHierarchy_TestCase {

  @Test
  public void should_dispose_Window() {
    final JFrame window = frame().createNew();
    new EasyMockTemplate(hierarchyDelegate) {
      protected void expectations() {
        hierarchyDelegate.dispose(window);
        expectLastCall().once();
      }

      protected void codeToTest() {
        hierarchy.dispose(window);
      }
    }.run();
  }
}
