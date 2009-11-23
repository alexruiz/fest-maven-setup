/*
 * Created on Dec 9, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.mocks;

/**
 * Understands an unexpected error.
 *
 * @author Alex Ruiz
 */
public class UnexpectedError extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a new </code>{@link UnexpectedError}</code>.
   * @param cause the original cause of the error.
   */
  UnexpectedError(Throwable cause) {
    super(cause);
  }
}
