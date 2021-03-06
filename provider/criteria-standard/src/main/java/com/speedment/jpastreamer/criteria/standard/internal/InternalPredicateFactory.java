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
package com.speedment.jpastreamer.criteria.standard.internal;

import static java.util.Objects.requireNonNull;

import com.speedment.jpastreamer.criteria.Criteria;
import com.speedment.jpastreamer.criteria.PredicateFactory;
import com.speedment.jpastreamer.criteria.standard.internal.predicate.PredicateMapper;
import com.speedment.jpastreamer.exception.JPAStreamerException;
import com.speedment.jpastreamer.field.predicate.CombinedPredicate;
import com.speedment.jpastreamer.field.predicate.FieldPredicate;
import com.speedment.jpastreamer.field.predicate.SpeedmentPredicate;

import javax.persistence.criteria.Predicate;

public final class InternalPredicateFactory implements PredicateFactory {

    private final PredicateMapper predicateMapper = PredicateMapper.createPredicateMapper();

    @Override
    @SuppressWarnings("unchecked")
    public <ENTITY> Predicate createPredicate(
        final Criteria<ENTITY, ?> criteria,
        final SpeedmentPredicate<ENTITY> speedmentPredicate
    ) {
        requireNonNull(criteria);
        requireNonNull(speedmentPredicate);

        if (speedmentPredicate instanceof FieldPredicate) {
            final FieldPredicate<ENTITY> fieldPredicate = (FieldPredicate<ENTITY>) speedmentPredicate;

            return predicateMapper.mapPredicate(criteria, fieldPredicate);
        }

        if (speedmentPredicate instanceof CombinedPredicate) {
            final CombinedPredicate<ENTITY> combinedPredicate = (CombinedPredicate<ENTITY>) speedmentPredicate;

            final Predicate[] predicates = combinedPredicate.stream().map(predicate -> {
                if (predicate instanceof SpeedmentPredicate) {
                    return createPredicate(criteria, (SpeedmentPredicate<ENTITY>) predicate);
                }
                throw new JPAStreamerException(
                    "Predicate type [" + predicate.getClass().getSimpleName() + "] is not supported"
                );
            }).toArray(Predicate[]::new);

            switch (combinedPredicate.getType()) {
                case AND:
                    return criteria.getBuilder().and(predicates);
                case OR:
                    return criteria.getBuilder().or(predicates);
                default:
                    throw new JPAStreamerException(
                        "Predicate logical operator [" + combinedPredicate.getType() + "] is not supported"
                    );
            }
        }

        throw new JPAStreamerException(
            "Predicate type [" + speedmentPredicate.getClass().getSimpleName() + "] is not supported"
        );
    }
}
