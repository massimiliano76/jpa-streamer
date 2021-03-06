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
package com.speedment.jpastreamer.renderer;

import com.speedment.jpastreamer.pipeline.Pipeline;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;

public interface Renderer {

    /**
     * Creates and returns a new RenderResult whereby the provided {@code Pipeline}
     * is rendered to a stream using a {@code EntityManagerFactory}
     * provided via the {@code RendererFactory}.
     *
     * @param pipeline describing the intended Stream
     * @param streamConfiguration containing additional information, such as joins
     * @param <T> type of the elements in the returned Stream
     * @return a new RenderResult whereby the provided {@code Pipeline}
     *         is rendered to a stream using a {@code EntityManagerFactory}
     */
    <T> RenderResult<?> render(final Pipeline<T> pipeline, final StreamConfiguration<T> streamConfiguration);

    /**
     * Used to release any dangling resources after the expiration period of the
     * Renderer instance.
     */
    void close();
}
