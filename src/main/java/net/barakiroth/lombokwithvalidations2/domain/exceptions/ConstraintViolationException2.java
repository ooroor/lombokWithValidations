package net.barakiroth.lombokwithvalidations2.domain.exceptions;

import lombok.Getter;
import net.barakiroth.lombokwithvalidations2.domain.ConstraintViolation2;

import java.util.Collections;
import java.util.Set;

public class ConstraintViolationException2 extends RuntimeException {

    @Getter
    private final Set<ConstraintViolation2<?>> constraintViolations;

    public ConstraintViolationException2(
            final String msg,
            final Set<ConstraintViolation2<?>> constraintViolations) {
        super(msg);
        this.constraintViolations = Collections.unmodifiableSet(constraintViolations);
    }
}
