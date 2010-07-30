/*
 * Created on Mar 30, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.util;

import static org.easymock.classextension.EasyMock.createMock;

/**
 * Understands instances of <code>{@link RobotFactory}</code> to be used for testing purposes.
 *
 * @author Alex Ruiz
 */
public final class TestRobotFactories {

  public static RobotFactory newRobotFactoryMock() {
    return createMock(RobotFactory.class);
  }

  private TestRobotFactories() {}
}
