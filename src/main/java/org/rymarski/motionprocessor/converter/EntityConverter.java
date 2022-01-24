package org.rymarski.motionprocessor.converter;


public interface EntityConverter<E, D> {
    E toEntity(D d);

    D toDto(E e);
}
