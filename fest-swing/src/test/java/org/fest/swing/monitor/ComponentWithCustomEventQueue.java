/*
 * Created on Mar 22, 2008
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
package org.fest.swing.monitor;

import java.awt.Toolkit;

import javax.swing.JComponent;

/**
 * Understands a GUI component stub that can be configured to use a custom <code>{@link Toolkit}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class ComponentWithCustomEventQueue extends JComponent {

  private static final long serialVersionUID = 1L;
  
  private final Toolkit toolkit;

  ComponentWithCustomEventQueue(Toolkit toolkit) {
    this.toolkit = toolkit;
  }

  @Override public Toolkit getToolkit() {
    return toolkit;
  }
}
