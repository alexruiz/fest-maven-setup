/*
 * Created on Jul 16, 2008
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
package org.fest.swing.core.matcher;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JFrames.frame;
import static org.fest.swing.test.core.Regex.regex;

import javax.swing.JFrame;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Test;

/**
 * Tests for <code>{@link FrameMatcher#matches(java.awt.Component)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FrameMatcher_matches_byTitlePattern_Test extends EDTSafeTestCase {

  @Test
  public void should_return_true_if_title_matches_pattern() {
    FrameMatcher matcher = FrameMatcher.withTitle(regex("He.*"));
    JFrame frame = frame().withTitle("Hello").createNew();
    assertThat(matcher.matches(frame)).isTrue();
  }

  @Test
  public void should_return_false_if_title_does_not_match_pattern() {
    FrameMatcher matcher = FrameMatcher.withTitle(regex("Hello"));
    JFrame frame = frame().withTitle("Bye").createNew();
    assertThat(matcher.matches(frame)).isFalse();
  }
}
