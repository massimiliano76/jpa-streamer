package com.speedment.jpastreamer.renderer;

import com.speedment.jpastreamer.pipeline.Pipeline;

public interface Renderer {

    /**
     * Creates and returns a new RenderResult whereby the provided {@code Pipeline}
     * is rendered to a stream using a {@code EntityManagerFactory}
     * provided via the {@code RendererFactory}.
     *
     * @param pipeline describing the intended Stream
     * @param <T> type of the elements in the returned Stream
     * @return a new RenderResult whereby the provided {@code Pipeline}
     *         is rendered to a stream using a {@code EntityManagerFactory}
     */
    <T> RenderResult<T> render(Pipeline<T> pipeline);

    void close();
}
