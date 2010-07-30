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
import static org.fest.swing.test.builder.JLabels.label;
import static org.junit.rules.ExpectedException.none;

import javax.swing.JButton;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Tests for <code>{@link TextReader#containsText(java.awt.Component, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class TextReader_containsText_Test extends EDTSafeTestCase {

  @Rule
  public final ExpectedException thrown = none();

  private TextReader<JButton> reader;

  @Before
  public void setUp() {
    reader = new TestTextReader();
  }

  @Test
  public void should_throw_error_if_Component_is_not_supported() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Expecting component of type javax.swing.JButton but got javax.swing.JLabel");
    reader.containsText(label().createNew(), "Yoda");
  }

  @Test
  public void should_return_true_if_Component_contains_text() {
    final JButton button = createMock(JButton.class);
    new EasyMockTemplate(button) {
      @Override protected void expectations() {
        expect(button.getText()).andReturn("Yoda");
      }

      @Override protected void codeToTest() {
        assertThat(reader.containsText(button, "Yoda")).isTrue();
      }
    };
  }

  @Test
  public void should_return_false_if_Component_doesn_not_contain_text() {
    final JButton button = createMock(JButton.class);
    new EasyMockTemplate(button) {
      @Override protected void expectations() {
        expect(button.getText()).andReturn("Yoda");
      }

      @Override protected void codeToTest() {
        assertThat(reader.containsText(button, "Leia")).isFalse();
      }
    };
  }

  private static class TestTextReader extends TextReader<JButton> {
    @Override public Class<JButton> supportedComponent() {
      return JButton.class;
    }

    @Override protected boolean checkContainsText(JButton button, String text) {
      return button.getText().contains(text);
    }
  }
}
