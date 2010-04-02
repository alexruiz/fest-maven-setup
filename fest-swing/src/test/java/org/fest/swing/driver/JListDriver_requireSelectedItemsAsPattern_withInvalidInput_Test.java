/*
 * Created on Feb 24, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Tests for <code>{@link JListDriver#requireSelectedItems(javax.swing.JList, java.util.regex.Pattern...)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListDriver_requireSelectedItemsAsPattern_withInvalidInput_Test extends JListDriver_withMocks_TestCase {

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_array_of_patterns_is_null() {
    Pattern[] patterns = null;
    driver.requireSelectedItems(list, patterns);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_array_of_patterns_is_empty() {
    Pattern[] patterns = new Pattern[0];
    driver.requireSelectedItems(list, patterns);
  }
}
