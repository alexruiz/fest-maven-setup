/*
 * Created on May 14, 2007
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
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.hierarchy.NewHierarchy.ignoreExistingComponents;
import static org.fest.util.Strings.concat;
import static org.fest.util.Systems.LINE_SEPARATOR;

import java.awt.*;
import java.io.*;
import java.util.Collection;

import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.hierarchy.*;

/**
 * Understands GUI <code>{@link java.awt.Component}</code> lookup.
 *
 * @author Alex Ruiz
 */
public final class BasicComponentFinder implements ComponentFinder {

  private final ComponentHierarchy hierarchy;
  private final ComponentPrinter printer;
  private final Settings settings;

  private final FinderDelegate finderDelegate = new FinderDelegate();

  private boolean includeHierarchyInComponentLookupException;

  /**
   * Creates a new <code>{@link BasicComponentFinder}</code> with a new AWT hierarchy. <code>{@link Component}</code>s
   * created before the created <code>{@link BasicComponentFinder}</code> cannot be accessed by the created
   * <code>{@link BasicComponentFinder}</code>.
   * @return the created finder.
   */
  public static ComponentFinder finderWithNewAwtHierarchy() {
    return new BasicComponentFinder(ignoreExistingComponents());
  }

  /**
   * Creates a new <code>{@link BasicComponentFinder}</code> that has access to all the GUI components in the AWT
   * hierarchy.
   * @return the created finder.
   */
  public static ComponentFinder finderWithCurrentAwtHierarchy() {
    return new BasicComponentFinder(new ExistingHierarchy());
  }

  /**
   * Creates a new <code>{@link BasicComponentFinder}</code>. The created finder does not use any
   * <code>{@link Settings}</code>.
   * @param hierarchy the component hierarchy to use.
   */
  protected BasicComponentFinder(ComponentHierarchy hierarchy) {
    this(hierarchy, null);
  }

  /**
   * Creates a new <code>{@link BasicComponentFinder}</code>.
   * @param hierarchy the component hierarchy to use.
   * @param settings the configuration settings to use. It can be {@code null}.
   */
  protected BasicComponentFinder(ComponentHierarchy hierarchy, Settings settings) {
    this.hierarchy = hierarchy;
    this.settings = settings;
    printer = new BasicComponentPrinter(hierarchy);
    includeHierarchyIfComponentNotFound(true);
  }

  /** {@inheritDoc} */
  public ComponentPrinter printer() { return printer; }

  /** {@inheritDoc} */
  public <T extends Component> T findByType(Class<T> type) {
    return findByType(type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByType(Class<T> type, boolean showing) {
    return type.cast(find(new TypeMatcher(type, showing)));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByType(Container root, Class<T> type) {
    return findByType(root, type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByType(Container root, Class<T> type, boolean showing) {
    return type.cast(find(root, new TypeMatcher(type, showing)));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByName(String name, Class<T> type) {
    return findByName(name, type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByName(String name, Class<T> type, boolean showing) {
    Component found = find(new NameMatcher(name, type, showing));
    return type.cast(found);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByName(String name) {
    return findByName(name, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByName(String name, boolean showing) {
    return find(new NameMatcher(name, showing));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByLabel(String label, Class<T> type) {
    return findByLabel(label, type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByLabel(String label, Class<T> type, boolean showing) {
    Component found = find(new LabelMatcher(label, type, showing));
    return labelFor(found, type);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByLabel(String label) {
    return findByLabel(label, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByLabel(String label, boolean showing) {
    Component found = find(new LabelMatcher(label, showing));
    return labelFor(found, Component.class);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T find(GenericTypeMatcher<T> m) {
    Component found = find((ComponentMatcher)m);
    return m.supportedType().cast(found);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component find(ComponentMatcher m) {
    return find(hierarchy, m);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByName(Container root, String name, Class<T> type) {
    return findByName(root, name, type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByName(Container root, String name, Class<T> type, boolean showing) {
    Component found = find(root, new NameMatcher(name, type, showing));
    return type.cast(found);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByName(Container root, String name) {
    return findByName(root, name, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByName(Container root, String name, boolean showing) {
    return find(root, new NameMatcher(name, showing));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByLabel(Container root, String label, Class<T> type) {
    return findByLabel(root, label, type, requireShowing());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T findByLabel(Container root, String label, Class<T> type, boolean showing) {
    Component found = find(root, new LabelMatcher(label, type, showing));
    return labelFor(found, type);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByLabel(Container root, String label) {
    return findByLabel(root, label, requireShowing());
  }

  private boolean requireShowing() {
    return requireShowingFromSettingsOr(false);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component findByLabel(Container root, String label, boolean showing) {
    Component found = find(root, new LabelMatcher(label, showing));
    return labelFor(found, Component.class);
  }

  private <T> T labelFor(Component label, Class<T> type) {
    assertThat(label).isInstanceOf(JLabel.class);
    Component target = ((JLabel)label).getLabelFor();
    assertThat(target).isInstanceOf(type);
    return type.cast(target);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public <T extends Component> T find(Container root, GenericTypeMatcher<T> m) {
    Component found = find(root, (ComponentMatcher)m);
    return m.supportedType().cast(found);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component find(Container root, ComponentMatcher m) {
    return find(hierarchy(root), m);
  }

  @RunsInEDT
  private Component find(ComponentHierarchy h, ComponentMatcher m)  {
    Collection<Component> found = finderDelegate.find(h, m);
    if (found.isEmpty()) throw componentNotFound(h, m);
    if (found.size() > 1) throw multipleComponentsFound(found, m);
    return found.iterator().next();
  }

  @RunsInEDT
  private ComponentLookupException componentNotFound(ComponentHierarchy h, ComponentMatcher m) {
    String message = concat("Unable to find component using matcher ", m, ".");
    if (includeHierarchyIfComponentNotFound())
      message = concat(message,
          LINE_SEPARATOR, LINE_SEPARATOR, "Component hierarchy:", LINE_SEPARATOR, formattedHierarchy(root(h)));
    throw new ComponentLookupException(message);
  }

  private static Container root(ComponentHierarchy h) {
    if (h instanceof SingleComponentHierarchy) return ((SingleComponentHierarchy)h).root();
    return null;
  }

  @RunsInEDT
  private String formattedHierarchy(Container root) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out, true);
    printer.printComponents(printStream, root);
    printStream.flush();
    return new String(out.toByteArray());
  }

  @RunsInEDT
  private static ComponentLookupException multipleComponentsFound(Collection<Component> found, ComponentMatcher m) {
    StringBuilder message = new StringBuilder();
    message.append("Found more than one component using matcher ").append(m).append(".").append(LINE_SEPARATOR)
           .append(LINE_SEPARATOR)
           .append("Found:");
    appendComponents(message, found);
    if (!found.isEmpty()) message.append(LINE_SEPARATOR);
    throw new ComponentLookupException(message.toString(), found);
  }

  @RunsInEDT
  private static void appendComponents(final StringBuilder message, final Collection<Component> found) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        for (Component c : found) message.append(LINE_SEPARATOR).append(format(c));
      }
    });
  }

  /** {@inheritDoc} */
  public boolean includeHierarchyIfComponentNotFound() {
    return includeHierarchyInComponentLookupException;
  }

  /** {@inheritDoc} */
  public void includeHierarchyIfComponentNotFound(boolean newValue) {
    includeHierarchyInComponentLookupException = newValue;
  }

  /** {@inheritDoc} */
  public Collection<Component> findAll(ComponentMatcher m) {
    return finderDelegate.find(hierarchy, m);
  }

  /** {@inheritDoc} */
  public Collection<Component> findAll(Container root, ComponentMatcher m) {
    return finderDelegate.find(hierarchy(root), m);
  }

  /** {@inheritDoc} */
  public <T extends Component> Collection<T> findAll(GenericTypeMatcher<T> m) {
    return finderDelegate.find(hierarchy, m);
  }

  /** {@inheritDoc} */
  public <T extends Component> Collection<T> findAll(Container root, GenericTypeMatcher<T> m) {
    return finderDelegate.find(hierarchy(root), m);
  }

  /**
   * Returns the value of the flag "requireShowing" in the <code>{@link ComponentLookupScope}</code> this finder's
   * <code>{@link Settings}</code>. If the settings object is {@code null}, this method will return the provided
   * default value.
   * @param defaultValue the value to return if this matcher does not have any configuration settings.
   * @return the value of the flag "requireShowing" in this finder's settings, or the provided default value if this
   * finder does not have configuration settings.
   */
  protected final boolean requireShowingFromSettingsOr(boolean defaultValue) {
    if (settings == null) return defaultValue;
    return settings.componentLookupScope().requireShowing();
  }

  private ComponentHierarchy hierarchy(Container root) {
    if (root == null) return hierarchy;
    return new SingleComponentHierarchy(root, hierarchy);
  }
}
