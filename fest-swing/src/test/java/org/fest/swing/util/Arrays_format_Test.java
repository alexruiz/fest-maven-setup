/*
 * Created on Jul 29, 2009
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
import static org.fest.util.Strings.concat;
import static org.fest.util.Systems.LINE_SEPARATOR;

import org.junit.Test;

/**
 * Tests for <code>{@link Arrays#format(String[][])}</code>.
 *
 * @author Alex Ruiz
 */
public class Arrays_format_Test {

  @Test
  public void should_return_null_if_array_is_null() {
    assertThat(Arrays.format(null)).isEqualTo("null");
  }

  @Test
  public void should_return_empty_brackets_if_first_dimension_is_zero() {
    String[][] array = new String[0][];
    assertThat(Arrays.format(array)).isEqualTo("[]");
  }

  @Test
  public void should_return_empty_brackets_if_second_dimension_is_zero() {
    String[][] array = new String[1][0];
    assertThat(Arrays.format(array)).isEqualTo("[[]]");
  }

  @Test
  public void should_format_array() {
    String[][] array = {
        { "0-0", "0-1", "0-2"},
        { "1-0", "1-1", "1-2"},
        { "2-0", "2-1", "2-2"},
        { "3-0", "3-1", "3-2"},
    };
    String formatted = concat(
        "[['0-0', '0-1', '0-2'],", LINE_SEPARATOR,
        " ['1-0', '1-1', '1-2'],", LINE_SEPARATOR,
        " ['2-0', '2-1', '2-2'],", LINE_SEPARATOR,
        " ['3-0', '3-1', '3-2']]"
    );
    assertThat(Arrays.format(array)).isEqualTo(formatted);
  }
}
