package net.barakiroth.lombokwithvalidations.validation;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstraintViolationException extends RuntimeException {

    @Getter
    private final Set<ConstraintViolation<?>> constraintViolations;

    public <DATA_OBJECT> ConstraintViolationException(
            final String msg,
            final Set<ConstraintViolation<DATA_OBJECT>> constraintViolations) {
        super(msg);
        this.constraintViolations = new HashSet<>();
        this.constraintViolations.addAll(constraintViolations);
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
