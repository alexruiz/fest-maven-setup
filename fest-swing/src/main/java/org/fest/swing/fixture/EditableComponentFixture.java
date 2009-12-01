/*
 * Created on Mar 11, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

/**
 * Understands state verification of an editable GUI component.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public interface EditableComponentFixture {

  /**
   * Asserts that the GUI component is editable.
   * @throws AssertionError if the GUI component is not editable.
   * @return this fixture.
   */
  public EditableComponentFixture requireEditable();

  /**
   * Asserts that the GUI component is not editable.
   * @throws AssertionError if the GUI component is editable.
   * @return this fixture.
   */
  public EditableComponentFixture requireNotEditable();

}
