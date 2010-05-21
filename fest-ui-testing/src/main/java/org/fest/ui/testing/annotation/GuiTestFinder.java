/*
 * Created on May 27, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.ui.testing.annotation;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * Understands utility methods related to GUI tests. A GUI test is a class or method annotated with
 * <code>{@link GuiTest}</code>.
 *
 * @author Alex Ruiz
 */
public final class GuiTestFinder {

  /**
   * Returns <code>true</code> if the given class and/or method are annotated with <code>{@link GuiTest}</code>. This
   * method also searches in super-classes and overridden methods.
   * @param type the class to check.
   * @param method the method to check.
   * @return <code>true</code> if the given class and/or method are annotated with <code>{@link GuiTest}</code>.
   */
  public static boolean isGuiTest(Class<?> type, Method method) {
    return isGuiTest(type) || isGuiTest(method) || isSuperClassGuiTest(type, method);
  }

  private static boolean isSuperClassGuiTest(Class<?> type, Method method) {
    Class<?> superclass = type.getSuperclass();
    while (superclass != null) {
      if (isGuiTest(superclass)) return true;
      Method overriden = method(superclass, method.getName(), method.getParameterTypes());
      if (overriden != null && isGuiTest(overriden)) return true;
      superclass = superclass.getSuperclass();
    }
    return false;
  }

  private static Method method(Class<?> type, String methodName, Class<?>[] parameterTypes) {
      try {
        return type.getDeclaredMethod(methodName, parameterTypes);
      } catch (SecurityException e) {
        return null;
      } catch (NoSuchMethodException e) {
        return null;
      } catch (RuntimeException e) {
        return null;
      }
  }

  private static boolean isGuiTest(AnnotatedElement annotatedElement) {
    return annotatedElement.isAnnotationPresent(GuiTest.class);
  }

  private GuiTestFinder() {}
}
