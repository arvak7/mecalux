package com.mecalux.prueba.common.validate;

import com.mecalux.prueba.common.base.Family;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FamilyValidator implements ConstraintValidator<ValidateFamily, String> {

    @Override
    public void initialize(ValidateFamily constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        boolean exist = false;
        for (Family family : Family.values()) {
            if (family.toString().equals(value)) {
                exist = true;
                if (exist) {
                    break;
                }
            }
        }
        return exist;
    }
}