/*
 * Created on Jul 24, 2009
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

import java.awt.Component;

/**
 * Understands an implementation of <code>{@link AbstractComponentMatcher}</code> for testing purposes.
 *
 * @author Alex Ruiz
 */
class ConcreteComponentMatcher extends AbstractComponentMatcher {

  public ConcreteComponentMatcher() {
    super();
  }

  public ConcreteComponentMatcher(boolean requireShowing) {
    super(requireShowing);
  }

  public boolean matches(Component c) {
    return false;
  }
}