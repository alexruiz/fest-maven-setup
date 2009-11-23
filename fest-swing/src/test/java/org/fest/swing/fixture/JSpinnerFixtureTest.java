/*
 * Created on Jul 1, 2007
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
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JSpinnerFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSpinnerFixtureTest extends JSpinnerFixture_TestCase {

  // TODO Reorganize into smaller unites

  @Test
  public void shouldRequireValue() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireValue(target(), "A Value");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireValue("A Value"));
      }
    }.run();
  }

  @Test
  public void shouldIncrementTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().increment(target(), 8);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().increment(8));
      }
    }.run();
  }

  @Test
  public void shouldIncrement() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().increment(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().increment());
      }
    }.run();
  }

  @Test
  public void shouldDecrementTheGivenTimes() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().decrement(target(), 8);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().decrement(8));
      }
    }.run();
  }

  @Test
  public void shouldDecrement() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().decrement(target());
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().decrement());
      }
    }.run();
  }

  @Test
  public void shouldReturnText() {
    final String text = "Some Text";
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().textOf(target())).andReturn(text);
      }

      protected void codeToTest() {
        assertThat(fixture().text()).isEqualTo(text);
      }
    }.run();
  }

  @Test
  public void shouldEnterText() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().enterText(target(), "Some Text");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().enterText("Some Text"));
      }
    }.run();
  }

  @Test
  public void shouldSelectValue() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().selectValue(target(), "Some Text");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().select("Some Text"));
      }
    }.run();
  }

  @Test
  public void shouldEnterTextAndCommit() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().enterTextAndCommit(target(), "Some Text");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().enterTextAndCommit("Some Text"));
      }
    }.run();
  }
}
