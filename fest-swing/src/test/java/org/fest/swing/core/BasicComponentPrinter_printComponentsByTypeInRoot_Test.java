/*
 * Created on Jul 25, 2009
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
import static org.fest.swing.format.Formatting.format;

import java.awt.Component;

import javax.swing.JButton;

import org.junit.Test;

/**
 * Tests for <code>{@link BasicComponentPrinter#printComponents(java.io.PrintStream, Class, java.awt.Container)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicComponentPrinter_printComponentsByTypeInRoot_Test extends BasicComponentPrinter_TestCase {

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_OutputStream_is_null() {
    printer.printComponents(null, JButton.class, windowOne);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_type_to_match_is_null() {
    Class<? extends Component> type = null;
    printer.printComponents(out, type, windowOne);
  }

  @Test
  public void should_print_all_Components_of_given_type_in_given_root() {
    printer.printComponents(out, JButton.class, windowOne);
    assertThat(out.printed()).containsOnly(format(windowOne.button));
  }
}
