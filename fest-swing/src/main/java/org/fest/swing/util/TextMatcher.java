/*
 * Created on Jun 25, 2009
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

/**
 * Understands matching a given text to one or more values.
 *
 * @author Alex Ruiz
 */
public interface TextMatcher {

  /**
   * Indicates whether the given text matches some value(s).
   * @param text the text to verify.
   * @return {@code true} if the given text matches some value(s), {@code false} otherwise.
   */
  boolean isMatching(String text);

  /**
   * Returns the description of the type of value this matcher supports.
   * @return the description of the type of value this matcher supports.
   */
  String description();

  /**
   * Returns the values in this matcher, formatted as a <code>String</code>.
   * @return the values in this matcher, formatted as a <code>String</code>.
   */
  String formattedValues();
}
