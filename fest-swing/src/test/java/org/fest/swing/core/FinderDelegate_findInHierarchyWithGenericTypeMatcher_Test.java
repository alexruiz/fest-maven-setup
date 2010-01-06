/*
 * Created on Jul 27, 2009
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

import static org.fest.assertions.Assertions.assertThat;

import java.util.Collection;

import javax.swing.JTextField;

import org.junit.Test;

/**
 * Tests for <code>{@link FinderDelegate#find(org.fest.swing.hierarchy.ComponentHierarchy, GenericTypeMatcher)}</code>.
 *
 * @author Alex Ruiz
 */
public class FinderDelegate_findInHierarchyWithGenericTypeMatcher_Test extends FinderDelegate_TestCase {

  @Test 
  public void should_return_components_matching_GenericTypeMatcher() {
    GenericTypeMatcher<JTextField> matcher = new GenericTypeMatcher<JTextField>(JTextField.class) {
      protected boolean isMatching(JTextField textField) {
        return "textBox".equals(textField.getName());
      }
    };
    Collection<JTextField> found = finder.find(hierarchy, matcher);
    assertThat(found).containsOnly(window.textField);
  }

  @Test
  public void should_return_empty_collection_if_matching_Components_not_found() {
    GenericTypeMatcher<JTextField> matcher = new GenericTypeMatcher<JTextField>(JTextField.class) {
      protected boolean isMatching(JTextField textField) {
        return false;
      }
    };
    Collection<JTextField> found = finder.find(hierarchy, matcher);
    assertThat(found).isEmpty();
  }
}
