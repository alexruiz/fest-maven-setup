/*
 * Created on Oct 1, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

/**
 * Test case for implementations of <code>isLessThan</code> for objects and primitives.
 *
 * @author Alex Ruiz
 */
public interface Assert_isLessThan_TestCase {

  void should_pass_if_actual_is_less_than_expected();

  void should_fail_if_actual_is_equal_to_expected();

  void should_fail_and_display_description_of_assertion_if_actual_is_equal_to_expected();

  void should_fail_with_custom_message_if_actual_is_equal_to_expected();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_equal_to_expected();

  void should_fail_if_actual_is_greater_than_expected();

  void should_fail_and_display_description_of_assertion_if_actual_is_greater_than_expected();

  void should_fail_with_custom_message_if_actual_is_greater_than_expected();

  void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_greater_than_expected();
}