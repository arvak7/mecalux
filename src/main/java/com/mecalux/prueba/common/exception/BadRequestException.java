package com.mecalux.prueba.common.exception;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5074632263740161411L;

    public BadRequestException(String message) {
        super(message);
    }
}
