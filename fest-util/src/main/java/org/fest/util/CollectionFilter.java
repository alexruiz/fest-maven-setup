/*
 * Created on Nov 1, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import java.util.Collection;
import java.util.List;

/**
 * Understands a filter for elements in a collection.
 * @param <T> the generic type of the elements to return by the filter.
 *
 * @author Yvonne Wang
 */
public interface CollectionFilter<T> {

  /**
   * Filters a given collection.
   * @param target the collection to filter.
   * @return a list containing the filtered elements.
   */
  List<T> filter(Collection<?> target);
}
