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

package com.speedment.jpastreamer.merger.standard.internal.criteria;

import static java.util.Objects.requireNonNull;

import com.speedment.jpastreamer.merger.result.CriteriaMergeResult;
import com.speedment.jpastreamer.merger.CriteriaMerger;
import com.speedment.jpastreamer.merger.standard.internal.criteria.result.StandardCriteriaMergeResult;
import com.speedment.jpastreamer.pipeline.Pipeline;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public final class InternalStandardCriteriaMerger implements CriteriaMerger {

    private final List<CriteriaMerger> mergingStrategies = new ArrayList<>();

    @Override
    public <T> CriteriaMergeResult<T> merge(final Pipeline<T> pipeline, final CriteriaQuery<T> query) {
        requireNonNull(pipeline);
        requireNonNull(query);

        CriteriaMergeResult<T> result = new StandardCriteriaMergeResult<>(pipeline, query);

        for (CriteriaMerger merger : mergingStrategies) {
            result = merger.merge(result.getPipeline(), result.getCriteriaQuery());
        }

        return result;
    }

    private void registerMergingStrategy(CriteriaMerger criteriaMerger) {
        mergingStrategies.add(criteriaMerger);
    }
}
