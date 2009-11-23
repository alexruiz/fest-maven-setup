/*
 * Created on Jun 9, 2008
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
package org.fest.swing.driver;

/**
 * Common validation methods.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class CommonValidations {

  static void validateCellReader(Object cellReader) {
    if (cellReader == null) throw new NullPointerException("Cell reader should not be null");
  }

  static void validateCellWriter(Object cellWriter) {
    if (cellWriter == null) throw new NullPointerException("Cell writer should not be null");
  }

  private CommonValidations() {}
}
