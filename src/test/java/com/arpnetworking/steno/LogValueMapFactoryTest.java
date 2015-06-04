/**
 * Copyright 2015 Groupon.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arpnetworking.steno;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for <code>LogValueMapFactory</code>.
 *
 * @author Ville Koskela (vkoskela at groupon dot com)
 */
public class LogValueMapFactoryTest {

    @Test
    public void testOneKeyValuePair() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        final Map<String, Object> actualValue = LogValueMapFactory.of("k1", "v1");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testTwoKeyValuePair() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("k2", "v2");
        final Map<String, Object> actualValue = LogValueMapFactory.of(
                "k1", "v1",
                "k2", "v2");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testThreeKeyValuePair() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("k2", "v2");
        expectedValue.put("k3", "v3");
        final Map<String, Object> actualValue = LogValueMapFactory.of(
                "k1", "v1",
                "k2", "v2",
                "k3", "v3");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFourKeyValuePair() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("k2", "v2");
        expectedValue.put("k3", "v3");
        expectedValue.put("k4", "v4");
        final Map<String, Object> actualValue = LogValueMapFactory.of(
                "k1", "v1",
                "k2", "v2",
                "k3", "v3",
                "k4", "v4");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFiveKeyValuePair() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("k2", "v2");
        expectedValue.put("k3", "v3");
        expectedValue.put("k4", "v4");
        expectedValue.put("k5", "v5");
        final Map<String, Object> actualValue = LogValueMapFactory.of(
                "k1", "v1",
                "k2", "v2",
                "k3", "v3",
                "k4", "v4",
                "k5", "v5");
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testBuilderWithNullKey() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("_nullKeys", true);
        expectedValue.put("k3", "v3");
        final Map<String, Object> actualValue = LogValueMapFactory.builder()
                .put("k1", "v1")
                .put(null, "v2")
                .put("k3", "v3")
                .build();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testBuilderWithNullValue() {
        final Map<String, Object> expectedValue = new HashMap<>();
        expectedValue.put("k1", "v1");
        expectedValue.put("_nullValues", true);
        expectedValue.put("k3", "v3");
        final Map<String, Object> actualValue = LogValueMapFactory.builder()
                .put("k1", "v1")
                .put("k2", null)
                .put("k3", "v3")
                .build();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        final Constructor<LogValueMapFactory> constructor =
                LogValueMapFactory.class.getDeclaredConstructor();
        Assert.assertNotNull(constructor);
        try {
            constructor.newInstance();
            Assert.fail("Static helper class should have private no-args constructor");
        } catch (final IllegalAccessException e) {
            constructor.setAccessible(true);
            final LogValueMapFactory logValueMapFactory = constructor.newInstance();
            Assert.assertNotNull(logValueMapFactory);
        }
    }
}
