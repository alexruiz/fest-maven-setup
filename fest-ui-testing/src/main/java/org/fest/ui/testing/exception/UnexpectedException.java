/*
 * Created on May 4, 2010
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
package org.fest.ui.testing.exception;

/**
 * Understands an exception thrown when an unexpected error occurs.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class UnexpectedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Returns a </code>{@link UnexpectedException}</code>.
   * @param cause the unexpected exception.
   * @return the created exception.
   */
  public static UnexpectedException unexpected(Throwable cause) {
    return new UnexpectedException(cause);
  }

  /**
   * Creates a new </code>{@link UnexpectedException}</code>.
   * @param cause the unexpected exception.
   */
  public UnexpectedException(Throwable cause) {
    super(cause);
  }

  /**
   * Creates a new </code>{@link UnexpectedException}</code>.
   * @param message the detail message.
   * @param cause the unexpected exception.
   */
  public UnexpectedException(String message, Throwable cause) {
    super(message, cause);
  }
}
