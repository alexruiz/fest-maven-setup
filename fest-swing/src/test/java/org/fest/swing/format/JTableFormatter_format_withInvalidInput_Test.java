/*
 * Created on Mar 24, 2008
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
package org.fest.swing.format;

import static org.fest.swing.test.builder.JTextFields.textField;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableFormatter#format(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableFormatter_format_withInvalidInput_Test extends EDTSafeTestCase {

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_Component_is_not_JTable() {
    new JTableFormatter().format(textField().createNew());
  }
}
