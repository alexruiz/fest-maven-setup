/*
 * Created on May 17, 2007
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
package org.fest.reflect;

/**
 * Understands a person.
 *
 * @author Yvonne Wang
 */
public class Person {

  private static int count;

  private String name;

  public Person() {}

  public Person(String name) {
    setName(name);
  }

  public Person(int name) {
    throw new IllegalArgumentException("The name of a person cannot be a number");
  }

  public Person(Person person) throws Exception {
    throw new Exception("A person cannot be created from another person");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static int getCount() {
    return count;
  }

  public static void setCount(int count) {
    Person.count = count;
  }
}
