package com.mecalux.prueba.common.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4699636364667560957L;

    public NotFoundException() {
        super("Entity not found");
    }

    public NotFoundException(String entity) {
        super(String.format("Entity %s not found", entity));
    }
}
