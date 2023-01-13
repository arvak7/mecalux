package com.mecalux.prueba.common.base;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Converter<T, V> {

    @Nonnull
    V convertEntityToDto(T t);

    @Nonnull
    T convertDtoToEntity(V v);

}
