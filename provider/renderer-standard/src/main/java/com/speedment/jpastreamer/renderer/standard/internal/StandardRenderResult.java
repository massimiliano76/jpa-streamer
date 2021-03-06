/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2020, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 */
package com.speedment.jpastreamer.renderer.standard.internal;

import com.speedment.jpastreamer.pipeline.terminal.TerminalOperation;
import com.speedment.jpastreamer.renderer.RenderResult;

import java.util.stream.Stream;

public class StandardRenderResult<T> implements RenderResult<T> {

    private final Class<T> returnType;
    private final Stream<T> stream;
    private final TerminalOperation<?, ?> terminalOperation;

    public StandardRenderResult(
        final Class<T> returnType,
        final Stream<T> stream,
        final TerminalOperation<?, ?> terminalOperation
    ) {
        this.stream = stream;
        this.returnType = returnType;
        this.terminalOperation = terminalOperation;
    }

    @Override
    public Class<T> root() {
        return returnType;
    }

    @Override
    public Stream<T> stream() {
        return stream;
    }

    @Override
    public TerminalOperation<?, ?> terminalOperation() {
        return terminalOperation;
    }
}
