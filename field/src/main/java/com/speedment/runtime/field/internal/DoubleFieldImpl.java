/*
 *
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
package com.speedment.runtime.field.internal;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.field.DoubleField;
import com.speedment.runtime.field.comparator.DoubleFieldComparator;
import com.speedment.runtime.field.comparator.NullOrder;
import com.speedment.runtime.field.internal.comparator.DoubleFieldComparatorImpl;
import com.speedment.runtime.field.internal.method.GetDoubleImpl;
import com.speedment.runtime.field.internal.predicate.doubles.*;
import com.speedment.runtime.field.method.DoubleGetter;
import com.speedment.runtime.field.method.DoubleSetter;
import com.speedment.runtime.field.method.GetDouble;
import com.speedment.runtime.field.predicate.FieldPredicate;
import com.speedment.runtime.field.predicate.Inclusion;
import com.speedment.runtime.field.predicate.SpeedmentPredicate;
import com.speedment.runtime.typemapper.TypeMapper;

import java.util.Collection;

import static com.speedment.runtime.field.internal.util.CollectionUtil.collectionToSet;
import static java.util.Objects.requireNonNull;

/**
 * Default implementation of the {@link DoubleField}-interface.
 * 
 * Generated by com.speedment.sources.pattern.FieldImplPattern
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
@GeneratedCode(value = "Speedment")
public final class DoubleFieldImpl<ENTITY, D> implements DoubleField<ENTITY, D> {
    
    private final ColumnIdentifier<ENTITY> identifier;
    private final GetDouble<ENTITY, D> getter;
    private final DoubleSetter<ENTITY> setter;
    private final TypeMapper<D, Double> typeMapper;
    private final boolean unique;
    private final String tableAlias;
    
    public DoubleFieldImpl(
            ColumnIdentifier<ENTITY> identifier,
            DoubleGetter<ENTITY> getter,
            DoubleSetter<ENTITY> setter,
            TypeMapper<D, Double> typeMapper,
            boolean unique) {
        this.identifier = requireNonNull(identifier);
        this.getter     = new GetDoubleImpl<>(this, getter);
        this.setter     = requireNonNull(setter);
        this.typeMapper = requireNonNull(typeMapper);
        this.unique     = unique;
        this.tableAlias = identifier.getTableId();
    }
    
    private DoubleFieldImpl(
            ColumnIdentifier<ENTITY> identifier,
            DoubleGetter<ENTITY> getter,
            DoubleSetter<ENTITY> setter,
            TypeMapper<D, Double> typeMapper,
            boolean unique,
            String tableAlias) {
        this.identifier = requireNonNull(identifier);
        this.getter     = new GetDoubleImpl<>(this, getter);
        this.setter     = requireNonNull(setter);
        this.typeMapper = requireNonNull(typeMapper);
        this.unique     = unique;
        this.tableAlias = requireNonNull(tableAlias);
    }
    
    @Override
    public ColumnIdentifier<ENTITY> identifier() {
        return identifier;
    }
    
    @Override
    public DoubleSetter<ENTITY> setter() {
        return setter;
    }
    
    @Override
    public GetDouble<ENTITY, D> getter() {
        return getter;
    }
    
    @Override
    public TypeMapper<D, Double> typeMapper() {
        return typeMapper;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public String tableAlias() {
        return tableAlias;
    }
    
    @Override
    public DoubleField<ENTITY, D> tableAlias(String tableAlias) {
        requireNonNull(tableAlias);
        return new DoubleFieldImpl<>(identifier, getter, setter, typeMapper, unique, tableAlias);
    }
    
    @Override
    public DoubleFieldComparator<ENTITY, D> comparator() {
        return new DoubleFieldComparatorImpl<>(this);
    }
    
    @Override
    public DoubleFieldComparator<ENTITY, D> reversed() {
        return comparator().reversed();
    }
    
    @Override
    public DoubleFieldComparator<ENTITY, D> comparatorNullFieldsFirst() {
        return comparator();
    }
    
    @Override
    public NullOrder getNullOrder() {
        return NullOrder.LAST;
    }
    
    @Override
    public boolean isReversed() {
        return false;
    }
    
    @Override
    public FieldPredicate<ENTITY> equal(Double value) {
        return new DoubleEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Double value) {
        return new DoubleGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Double value) {
        return new DoubleGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(
            Double start,
            Double end,
            Inclusion inclusion) {
        return new DoubleBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Collection<Double> values) {
        return new DoubleInPredicate<>(this, collectionToSet(values));
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notEqual(Double value) {
        return new DoubleNotEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessOrEqual(Double value) {
        return new DoubleLessOrEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessThan(Double value) {
        return new DoubleLessThanPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notBetween(
            Double start,
            Double end,
            Inclusion inclusion) {
        return new DoubleNotBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notIn(Collection<Double> values) {
        return new DoubleNotInPredicate<>(this, collectionToSet(values));
    }
}