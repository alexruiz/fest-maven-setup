/*
 * Created on Apr 1, 2008
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
package org.fest.swing.core;

import static org.easymock.classextension.EasyMock.createMock;

import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.hierarchy.SingleComponentHierarchy;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Before;

/**
 * Base test case for <code>{@link SingleComponentHierarchy}</code>.
 * 
 * @author Alex Ruiz
 */
public abstract class SingleComponentHierarchy_TestCase extends EDTSafeTestCase {

  ComponentHierarchy delegate;
  TestWindow root;
  SingleComponentHierarchy hierarchy;

  @Before
  public final void setUp() {
    delegate = createMock(ComponentHierarchy.class);
    root = TestWindow.createNewWindow(getClass());
    hierarchy = new SingleComponentHierarchy(root, delegate);
    onSetUp();
  }

  void onSetUp() {}
}
