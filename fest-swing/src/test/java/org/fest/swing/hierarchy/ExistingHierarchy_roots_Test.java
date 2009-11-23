/*
 * Created on Oct 20, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Window;
import java.util.Collection;

import org.fest.swing.monitor.WindowMonitor;
import org.junit.Test;

/**
 * Tests for <code>{@link ExistingHierarchy#roots()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ExistingHierarchy_roots_Test extends ExistingHierarchy_TestCase {

  @Test
  public void should_return_all_root_Windows() {
    Collection<Window> rootWindows = WindowMonitor.instance().rootWindows();
    assertThat(hierarchy.roots()).isEqualTo(rootWindows);
  }
}
