/*
 * Created on May 22, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.input;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;

/**
 * Understands detection of native drag 'n drop events.
 *
 * @author Alex Ruiz
 */
class NativeDnDIdentifier {

  boolean isNativeDragAndDrop(AWTEvent e) {
    return (e instanceof MouseEvent) && e.getClass().getName().indexOf("SunDropTargetEvent") != -1;
  }

}
