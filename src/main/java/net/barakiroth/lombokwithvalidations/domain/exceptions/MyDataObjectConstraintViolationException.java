package net.barakiroth.lombokwithvalidations.domain.exceptions;

import lombok.Getter;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectConstraintViolation;

import java.util.Collections;
import java.util.Set;

public class MyDataObjectConstraintViolationException extends RuntimeException {
    @Getter
    private final Set<MyDataObjectConstraintViolation> constraintViolations;
    public MyDataObjectConstraintViolationException(final Set<MyDataObjectConstraintViolation> constraintViolations) {
        this.constraintViolations = Collections.unmodifiableSet(constraintViolations);
    }
}