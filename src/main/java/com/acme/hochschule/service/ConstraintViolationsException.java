package com.acme.hochschule.service;

import com.acme.hochschule.entity.Fakultaet;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.Collection;

/**
 * Exception, falls es mindestens ein verletztes Constraint gibt.
 *
 */
@Getter
public class ConstraintViolationsException extends RuntimeException{
    /**
     * Die verletzten Constraints.
     */
    private final transient Collection<ConstraintViolation<Fakultaet>> violations;
    ConstraintViolationsException(
        @SuppressWarnings("ParameterHidesMemberVariable")
        final Collection<ConstraintViolation<Fakultaet>> violations
    ) {
        super("Constraints sind verletzt");
        this.violations = violations;
    }
}
