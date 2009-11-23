/*
 * Created on Feb 16, 2007
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
package org.fest.swing.test.awt;

import java.awt.Dimension;

/**
 * Understands an AWT <code>{@link Dimension}</code> with some extra utility methods.
 *
 * @author Alex Ruiz
 */
public class FluentDimension extends Dimension {
  private static final long serialVersionUID = 1L;

  public FluentDimension() {}
  public FluentDimension(Dimension d) { super(d); }
  public FluentDimension(int width, int height) { super(width, height); }

  public FluentDimension addToWidth(int value) {
    width += value;
    return this;
  }
  
  public FluentDimension addToHeight(int value) {
    height += value;
    return this;
  }
}
