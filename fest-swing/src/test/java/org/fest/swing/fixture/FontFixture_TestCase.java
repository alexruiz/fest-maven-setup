/*
 * Created on Mar 29, 2010
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
package org.fest.swing.fixture;

import static java.awt.Font.*;

import java.awt.Font;

import org.junit.*;

/**
 * Base test class for <code>{@link FontFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FontFixture_TestCase {

  private static Font font;
  private static FontFixture fixture;

  @BeforeClass
  public static void setUpOnce() {
    font = new Font("SansSerif", PLAIN, 8);
    fixture = new FontFixture(font);
  }

  final Font boldFont() {
    return font.deriveFont(BOLD);
  }

  final Font italicFont() {
    return font().deriveFont(ITALIC);
  }

  final Font font() { return font; }
  final FontFixture fixture() { return fixture; }
}
