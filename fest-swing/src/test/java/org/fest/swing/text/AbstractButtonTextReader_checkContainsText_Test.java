/*
 * Created on Jul 29, 2010
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
package org.fest.swing.text;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import javax.swing.AbstractButton;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;

/**
 * Tests for <code>{@link AbstractButtonTextReader#checkContainsText(AbstractButton, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class AbstractButtonTextReader_checkContainsText_Test extends EDTSafeTestCase {

  private AbstractButton button;
  private AbstractButtonTextReader reader;

  @Before
  public void setUp() {
    button = createMock(AbstractButton.class);
    reader = new AbstractButtonTextReader();
  }

  @Test
  public void should_return_false_if_text_in_AbstractButton_is_null() {
    new EasyMockTemplate(button) {
      @Override protected void expectations() {
        expect(button.getText()).andReturn(null);
      }

      @Override protected void codeToTest() {
        assertThat(reader.checkContainsText(button, "Yoda")).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_false_if_text_in_AbstractButton_does_not_contain_given_String() {
    new EasyMockTemplate(button) {
      @Override protected void expectations() {
        expect(button.getText()).andReturn("Leia");
      }

      @Override protected void codeToTest() {
        assertThat(reader.checkContainsText(button, "Yoda")).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_true_if_text_in_AbstractButton_contains_given_String() {
    new EasyMockTemplate(button) {
      @Override protected void expectations() {
        expect(button.getText()).andReturn("Yoda");
      }

      @Override protected void codeToTest() {
        assertThat(reader.checkContainsText(button, "Yo")).isTrue();
      }
    }.run();
  }
}
