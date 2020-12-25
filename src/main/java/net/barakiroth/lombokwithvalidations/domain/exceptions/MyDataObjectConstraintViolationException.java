package net.barakiroth.lombokwithvalidations.domain.exceptions;

import lombok.Getter;
import net.barakiroth.lombokwithvalidations.domain.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.domain.MyDataObjectValidationStrategy;

import java.util.Collections;
import java.util.Set;

public class MyDataObjectConstraintViolationException extends RuntimeException {

    @Getter
    private final Set<ConstraintViolation<MyDataObjectValidationStrategy>> constraintViolations;

    public MyDataObjectConstraintViolationException(
            final String msg,
            final Set<ConstraintViolation<MyDataObjectValidationStrategy>> constraintViolations) {
        super(msg);
        this.constraintViolations = Collections.unmodifiableSet(constraintViolations);
    }
}