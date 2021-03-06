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

/**
 * Tests for <code>NoOpLogBuilder</code>.
 *
 * @author Ville Koskela (vkoskela at groupon dot com)
 */
public class NoOpLogBuilderTest {

    @Test
    public void testReturnSelf() {
        final LogBuilder logBuilder = new NoOpLogBuilder();
        Assert.assertSame(logBuilder, logBuilder.addData("key", "value"));
        Assert.assertSame(logBuilder, logBuilder.addContext("key", "value"));
        Assert.assertSame(logBuilder, logBuilder.setEvent("event"));
        Assert.assertSame(logBuilder, logBuilder.setMessage("message"));
        Assert.assertSame(logBuilder, logBuilder.setThrowable(new NullPointerException("NPE!")));
    }
}
