/*
 * Created on Jul 10, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.applet;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.BorderFactory.*;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.SwingUtilities.isEventDispatchThread;
import static javax.swing.border.BevelBorder.LOWERED;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands a window that displays an <code>{@link Applet}</code>.
 * <p>
 * Typical usage:
 * <pre>
 * AppletViewer viewer = AppletViewer.newViewer(new MyApplet());
 *
 * // test the applet, viewer can be wrapped with a FrameFixture.
 * FrameFixture viewerFixture = new FrameFixture(viewer);
 *
 * viewer.unloadApplet() // stops and destroys the applet
 * viewerFixture.cleanUp();
 * </pre>
 * <p>
 * <b>Note:</b> In version 1.2, due to bug 
 * <a href="http://jira.codehaus.org/browse/FEST-219" target="_blank">FEST-219</a> constructors in this class have been 
 * replaced with the static factory methods {@code newViewer}. It was not possible to just deprecate them. To ensure 
 * correct behavior of the applet viewer, they had to be made unaccessible to client code.
 * </p>
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AppletViewer extends JFrame implements StatusDisplay {

  private static final long serialVersionUID = 1L;

  private static final String APPLET_VIEWER_TITLE = "Applet Viewer: ";
  private static final String APPLET_LOADED_MESSAGE = "Applet loaded";
  private static final Dimension DEFAULT_SIZE = new Dimension(100, 100);

  private final JLabel statusLabel = new JLabel();

  private final Applet applet;
  private transient AppletStub stub;
  private boolean loaded;

  /**
   * Creates a new </code>{@link AppletViewer}</code>. This factory method creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * <p>
   * <b>Note:</b> This method is executed in the event dispatch thread (EDT.)
   * </p>
   * @param applet the applet to view.
   * @return the created {@code AppletViewer}.
   * @throws NullPointerException if {@code applet} is {@code null}.
   * @since 1.2
   */
  @RunsInEDT
  public static AppletViewer newViewer(Applet applet) {
    AppletViewer viewer = createInEDT(applet);
    viewer.appletStub(new BasicAppletStub(viewer, new BasicAppletContext(viewer)));
    return viewer;
  }

  /**
   * Creates a new </code>{@link AppletViewer}</code>. This factory method creates new instances of
   * <code>{@link BasicAppletStub}</code> and <code>{@link BasicAppletContext}</code>.
   * <p>
   * <b>Note:</b> This method is executed in the event dispatch thread (EDT.)
   * </p>
   * @param applet the applet to view.
   * @param parameters the parameters included in an applet HTML tag.
   * @return the created {@code AppletViewer}.
   * @throws NullPointerException if {@code applet} is {@code null}.
   * @throws NullPointerException if {@code parameters} is {@code null}.
   * @since 1.2
   */
  @RunsInEDT
  public static AppletViewer newViewer(Applet applet, Map<String, String> parameters) {
    AppletViewer viewer = createInEDT(applet);
    viewer.appletStub(new BasicAppletStub(viewer, new BasicAppletContext(viewer), parameters));
    return viewer;
  }


  /**
   * Creates a new </code>{@link AppletViewer}</code>.
   * <p>
   * <b>Note:</b> This method is executed in the event dispatch thread (EDT.)
   * </p>
   * @param applet the applet to view.
   * @param stub the applet's stub.
   * @return the created {@code AppletViewer}.
   * @throws NullPointerException if {@code applet} is {@code null}.
   * @throws NullPointerException if {@code stub} is {@code null}.
   * @since 1.2
   */
  @RunsInEDT
  public static AppletViewer newViewer(Applet applet, AppletStub stub) {
    AppletViewer viewer = createInEDT(applet);
    viewer.appletStub(stub);
    return viewer;
  }

  @RunsInEDT
  private static AppletViewer createInEDT(final Applet applet) {
    return execute(new GuiQuery<AppletViewer>() {
      protected AppletViewer executeInEDT() {
        return new AppletViewer(applet);
      }
    });
  }

  private AppletViewer(Applet applet) {
    if (applet == null) throw new NullPointerException("The applet to load should not be null");
    this.applet = applet;
    setUpFrame();
    addContent();
  }

  private void setUpFrame() {
    setTitle(concat(APPLET_VIEWER_TITLE, applet.getClass().getName()));
    setSize(DEFAULT_SIZE);
    setLayout(new BorderLayout());
  }

  private void addContent() {
    add(applet, CENTER);
    statusLabel.setBorder(createCompoundBorder(createBevelBorder(LOWERED), createEmptyBorder(2, 5, 2, 5)));
    statusLabel.setName("status");
    add(statusLabel, SOUTH);
  }

  private void appletStub(AppletStub newAppletStub) {
    if (newAppletStub == null) throw new NullPointerException("The AppletStub should not be null");
    stub = newAppletStub;
    applet.setStub(stub);
    setUpApplet();
  }

  private void setUpApplet() {
    loadApplet();
    showStatus(APPLET_LOADED_MESSAGE);
  }

  /**
   * Initializes and starts the applet in this viewer.
   */
  public void reloadApplet() {
    if (loaded) unloadApplet();
    loadApplet();
  }

  private void loadApplet() {
    applet.init();
    applet.start();
    loaded = true;
  }

  /**
   * Stops and destroys the applet loaded in this viewer. This method should be called before closing or disposing this
   * viewer.
   */
  public void unloadApplet() {
    applet.stop();
    applet.destroy();
    loaded = false;
  }

  /**
   * Indicates whether the applet in this viewer is loaded or not.
   * @return {@code true} if this applet is loaded, {@code false} otherwise.
   */
  public boolean appletLoaded() { return loaded; }

  /**
   * Displays the given status message. This method is executed in the event dispatch thread (EDT.)
   * @param status the status to display.
   */
  @RunsInEDT
  public void showStatus(final String status) {
    if (isEventDispatchThread()) {
      setStatus(status);
      return;
    }
    invokeLater(new Runnable() {
      public void run() {
        setStatus(status);
      }
    });
  }

  @RunsInCurrentThread
  private void setStatus(String status) {
    statusLabel.setText(status);
  }

  /**
   * Returns the applet displayed in this viewer.
   * @return the applet displayed in this viewer.
   */
  public Applet applet() {
    return applet;
  }

  /**
   * Returns the <code>{@link AppletStub}</code> in this viewer.
   * @return the {@code AppletStub} in this viewer.
   */
  public AppletStub stub() {
    return stub;
  }
}
