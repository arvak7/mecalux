package com.mecalux.prueba.common.base;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@Component
public abstract class AbstractConverter<T, V> implements Converter<T, V> {

    private final Mapper mapper;
    private final Class<T> tClazz;
    private final Class<V> vClazz;

    protected AbstractConverter(Mapper mapper, Class<T> tClazz, Class<V> vClazz) {
        this.mapper = mapper;
        this.tClazz = tClazz;
        this.vClazz = vClazz;
    }

    @Override
    @Nonnull
    public V convertEntityToDto(T t) {
        return mapEntityToDto(mapper.map(t, vClazz), t);
    }

    @Override
    @Nonnull
    public T convertDtoToEntity(V v) {
        return mapDtoToEntity(mapper.map(v, tClazz), v);
    }

    private V mapEntityToDto(V v, T t) {
        return v;
    }

    private T mapDtoToEntity(T t, V v) {
        return t;
    }
}
