package net.barakiroth.lombokwithvalidations.domain.exceptions;

import lombok.Getter;
import net.barakiroth.lombokwithvalidations.domain.ConstraintViolation;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstraintViolationException extends RuntimeException {

    @Getter
    private final Set<ConstraintViolation<?>> constraintViolations;

    public ConstraintViolationException(
            final String msg,
            final Set<ConstraintViolation<?>> constraintViolations) {
        super(msg);
        this.constraintViolations = Collections.unmodifiableSet(constraintViolations);
    }

    @Override
    public String getMessage() {

        final AtomicInteger i = new AtomicInteger(0);
        return super.getMessage()
                + ": "
                + this.constraintViolations
                    .stream()
                    .map((constraintViolation) -> constraintViolation.toString())
                    .reduce(
                            "",
                            (partialString, constraintViolation) ->
                                    partialString
                                            + ("".equals(partialString) ? "" : ", ")
                                            + "{" + i.incrementAndGet()
                                            + ": <"
                                            + constraintViolation
                                            + ">}");
    }
}
