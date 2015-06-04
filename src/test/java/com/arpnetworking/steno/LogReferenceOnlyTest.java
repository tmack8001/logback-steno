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

import java.util.Map;

/**
 * Tests for <code>LogReferenceOnly</code>.
 *
 * @author Ville Koskela (vkoskela at groupon dot com)
 */
public class LogReferenceOnlyTest {

    @Test
    public void test() {
        final String value = "This is a String";
        final LogReferenceOnly valueReference = LogReferenceOnly.of(value);

        @SuppressWarnings("unchecked")
        final Map<String, Object> logValue = (Map<String, Object>) valueReference.toLogValue();
        Assert.assertTrue("Missing id", logValue.containsKey("id"));
        Assert.assertTrue("Missing class", logValue.containsKey("class"));
        Assert.assertEquals("Wrong class", String.class.getName(), logValue.get("class"));

        final String asString = valueReference.toString();
        Assert.assertNotNull(asString);
        Assert.assertFalse(asString.isEmpty());
    }
}