/*
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.jpastreamer.merger;

import com.speedment.jpastreamer.pipeline.Pipeline;

import javax.persistence.criteria.CriteriaQuery;

/**
 * @author Mislav Milicevic
 */
public interface Merger {

    /**
     * Inspects the provided {@code pipeline} and merges all available operations
     * into the provided {@code query}. Operations are to be removed from the pipeline
     * if they are merged.
     *
     * The modified pipeline and query are stored in a {@code MergeResult} and returned.
     *
     * @param pipeline to inspect and merge
     * @param query that accepts the merged operations
     * @param <T> root entity
     * @return a new MergeResult containing the modified {@code Pipeline}
     *         and {@code CriteriaQuery}
     */
    <T> MergeResult<T> merge(Pipeline<T> pipeline, CriteriaQuery<T> query);
}