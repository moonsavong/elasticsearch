/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the "Elastic License
 * 2.0", the "GNU Affero General Public License v3.0 only", and the "Server Side
 * Public License v 1"; you may not use this file except in compliance with, at
 * your election, the "Elastic License 2.0", the "GNU Affero General Public
 * License v3.0 only", or the "Server Side Public License, v 1".
 */

package org.elasticsearch.index.mapper;

import org.elasticsearch.test.ESTestCase;

public class IntervalThrottlerTests extends ESTestCase {

    public void testThrottling() throws Exception {
        var throttler = new IntervalThrottler.Acceptor(10);
        assertTrue(throttler.accept());
        assertFalse(throttler.accept());
        assertFalse(throttler.accept());

        Thread.sleep(20);
        assertTrue(throttler.accept());
        assertFalse(throttler.accept());
        assertFalse(throttler.accept());
    }
}
