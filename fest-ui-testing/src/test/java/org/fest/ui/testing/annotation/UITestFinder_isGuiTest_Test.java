/*
 * Created on May 27, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.ui.testing.annotation;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.reflect.core.Reflection.method;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link GuiTestFinder#isGuiTest(Class, Method)}</code>.
 *
 * @author Alex Ruiz
 */
public class UITestFinder_isGuiTest_Test {

  @GuiTest
  public static class GUITestClass {
    @GuiTest
    public void guiTestMethodWithAnnotation() {}
    public void guiTestMethodWithoutAnnotation() {}
  }

  public static class NonGUITestClass {
    @GuiTest public void guiTestMethod() {}
  }

  public static class GUITestSubclass extends GUITestClass {
    @Override public void guiTestMethodWithoutAnnotation() {
      super.guiTestMethodWithoutAnnotation();
    }
  }

  public static class NonGUITestSubclass extends NonGUITestClass {
    @Override public void guiTestMethod() {
      super.guiTestMethod();
    }
  }

  private GUITestClass guiTest;
  private NonGUITestClass nonGUITest;
  private GUITestSubclass guiTestSubclass;
  private NonGUITestSubclass nonGUITestSubclass;

  @Before public void setUp() throws Exception {
    guiTest = new GUITestClass();
    nonGUITest = new NonGUITestClass();
    guiTestSubclass = new GUITestSubclass();
    nonGUITestSubclass = new NonGUITestSubclass();
  }

  @Test public void should_return_true_if_class_has_GUITest_annotation() {
    Class<? extends GUITestClass> guiTestType = guiTest.getClass();
    Method guiTestMethod = method("guiTestMethodWithoutAnnotation").in(guiTest).info();
    boolean isGUITest = GuiTestFinder.isGuiTest(guiTestType, guiTestMethod);
    assertThat(isGUITest).isTrue();
  }

  @Test public void should_return_true_if_only_one_method_has_GUITest_annotation() {
    Class<? extends NonGUITestClass> nonGUITestType = nonGUITest.getClass();
    Method guiTestMethod = method("guiTestMethod").in(nonGUITest).info();
    boolean isGUITest = GuiTestFinder.isGuiTest(nonGUITestType, guiTestMethod);
    assertThat(isGUITest).isTrue();
  }

  @Test public void should_return_true_if_superclass_is_GUI_test() {
    Class<? extends GUITestSubclass> guiTestSubtype = guiTestSubclass.getClass();
    Method guiTestMethod = method("guiTestMethodWithoutAnnotation").in(guiTestSubclass).info();
    boolean isGUITest = GuiTestFinder.isGuiTest(guiTestSubtype, guiTestMethod);
    assertThat(isGUITest).isTrue();
  }

  @Test public void should_return_true_if_overriden_method_is_GUI_test() {
    Class<? extends NonGUITestSubclass> nonGUITestSubtype = nonGUITestSubclass.getClass();
    Method guiTestMethod = method("guiTestMethod").in(nonGUITestSubclass).info();
    boolean isGUITest = GuiTestFinder.isGuiTest(nonGUITestSubtype, guiTestMethod);
    assertThat(isGUITest).isTrue();
  }

  @Test public void should_return_false_if_not_containing_GUITest_annotation() {
    String s = "Yoda";
    Method concat = method("concat").withReturnType(String.class).withParameterTypes(String.class).in(s).info();
    assertThat(GuiTestFinder.isGuiTest(s.getClass(), concat)).isFalse();
  }
}
