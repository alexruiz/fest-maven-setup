/*
 * Created on Mar 26, 2010
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
package org.fest.swing.keystroke;

import static org.fest.util.Strings.join;

import java.util.*;

import org.fest.swing.util.OSFamily;

/**
 * Understands a generated name for a <code>{@link KeyStrokeMappingProvider}</code> to use, based on operating system
 * family and locale.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
class KeyStrokeMappingProviderNames implements Iterable<String> {

  private final String osFamily;
  private final String language;
  private final String country;

  static KeyStrokeMappingProviderNames generateNamesFrom(OSFamily osFamily, Locale locale) {
    return new KeyStrokeMappingProviderNames(osFamily, locale);
  }

  private KeyStrokeMappingProviderNames(OSFamily osFamily, Locale locale) {
    this.osFamily = osFamily.key();
    language = locale.getLanguage();
    country = locale.getCountry().toLowerCase();
  }

  public Iterator<String> iterator() {
    return new NameIterator(osFamily, language, country);
  }

  private static class NameIterator implements Iterator<String> {
    private static final String PREFIX = packageName() + ".KeyStrokeProvider";

    private static String packageName() {
      return KeyStrokeMappingProviderNames.class.getPackage().getName();
    }

    private static final String DELIMETER = "_";

    private final String osFamily;
    private final String language;
    private final String country;

    private State state;

    private enum State {
      FULL_NAME, WITHOUT_COUNTRY, LANGUAGE_ONLY, END
    }

    NameIterator(String osFamily, String language, String country) {
      this.osFamily = osFamily;
      this.language = language;
      this.country = country;
      state = State.FULL_NAME;
    }

    public String next() {
      switch(state) {
        case FULL_NAME:
          state = State.WITHOUT_COUNTRY;
          return join(PREFIX, osFamily, language, country).with(DELIMETER);
        case WITHOUT_COUNTRY:
          state = State.LANGUAGE_ONLY;
          return join(PREFIX, osFamily, language).with(DELIMETER);
        case LANGUAGE_ONLY:
          state = State.END;
          return join(PREFIX, language).with(DELIMETER);
        default:
          throw new NoSuchElementException("There are no more names to generate");
      }
    }

    public boolean hasNext() {
      return state != State.END;
    }

    public void remove() {
      throw new UnsupportedOperationException("This iterator does not support 'remove'");
    }
  }
}
