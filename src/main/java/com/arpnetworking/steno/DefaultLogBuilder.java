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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class facilitates type-safe construction of a Steno log message. Instances
 * of this class are not thread safe.
 *
 * @since 1.3.0
 *
 * @author Ville Koskela (vkoskela at groupon dot com)
 */
public class DefaultLogBuilder implements LogBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public LogBuilder setEvent(final String value) {
        _event = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogBuilder setMessage(final String value) {
        addData(Logger.MESSAGE_DATA_KEY, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LogBuilder setThrowable(final Throwable value) {
        _throwable = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultLogBuilder addData(final String name, final Object value) {
        if (_data == null) {
            _data = new LinkedHashMap<>();
        }
        _data.put(name, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultLogBuilder addContext(final String name, final Object value) {
        if (_context == null) {
            _context = new LinkedHashMap<>();
        }
        _context.put(name, value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log() {
        // TODO(vkoskela): Add STENO_MAPS_MARKER and convert to it. [ISSUE-12]
        List<String> dataKeys = null;
        List<Object> dataValues = null;
        List<String> contextKeys = null;
        List<Object> contextValues = null;

        if (_data != null) {
            dataKeys = new ArrayList<>();
            dataValues = new ArrayList<>();
            populateKeyValueLists(_data, dataKeys, dataValues);
        }
        if (_context != null) {
            contextKeys = new ArrayList<>();
            contextValues = new ArrayList<>();
            populateKeyValueLists(_context, contextKeys, contextValues);
        }

        _logger.log(
                _level,
                _event,
                dataKeys,
                dataValues,
                contextKeys,
                contextValues,
                _throwable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{Logger=" + _logger
                + ", Level=" + _level
                + ", Event=" + _event
                + ", Throwable=" + _throwable
                + ", Data=" + _data
                + ", Context=" + _context
                + "}";
    }

    private void populateKeyValueLists(final Map<String, Object> map, final List<String> keys, final List<Object> values) {
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }
    }

    /* package private */ DefaultLogBuilder(final Logger logger, final LogLevel level) {
        _logger = logger;
        _level = level;
    }

    private final Logger _logger;
    private final LogLevel _level;
    private String _event = null;
    private Throwable _throwable = null;
    private Map<String, Object> _data = null;
    private Map<String, Object> _context = null;
}
