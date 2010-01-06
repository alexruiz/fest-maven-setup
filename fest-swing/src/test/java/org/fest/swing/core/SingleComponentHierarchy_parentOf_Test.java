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

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JFrames.frame;
import static org.fest.swing.test.builder.JLabels.label;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.hierarchy.SingleComponentHierarchy;
import org.junit.Test;

/**
 * Tests for <code>{@link SingleComponentHierarchy#parentOf(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 */
public class SingleComponentHierarchy_parentOf_Test extends SingleComponentHierarchy_TestCase {

  @Test
  public void should_return_parent_of_Component() {
    final JFrame p = frame().createNew();
    final JLabel c = label().createNew();
    new EasyMockTemplate(delegate) {
      protected void expectations() {
        expect(delegate.parentOf(c)).andReturn(p);
      }

      protected void codeToTest() {
        Container foundParent = hierarchy.parentOf(c);
        assertThat(foundParent).isSameAs(p);
      }
    }.run();
  }
}
