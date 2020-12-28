package net.barakiroth.lombokwithvalidations2.domain;

import net.barakiroth.lombokwithvalidations2.domain.exceptions.ConstraintViolationException2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public abstract class AbstractValidationStrategy2<DATA_OBJECT> {

    private final BiFunction<
            DATA_OBJECT,
            AbstractValidationStrategy2<DATA_OBJECT>,
            ConstraintViolation2<DATA_OBJECT>
     > validator;

    protected AbstractValidationStrategy2(
            final BiFunction<
                    DATA_OBJECT,
                    AbstractValidationStrategy2<DATA_OBJECT>,
                    ConstraintViolation2<DATA_OBJECT>
            > validator) {
        this.validator = validator;
    }

    public static <DATA_OBJECT> DATA_OBJECT validate(
            final DATA_OBJECT unvalidatedMyDataObject,
            final AbstractValidationStrategy2<DATA_OBJECT>[] validationStrategies) {

        final Set<AbstractValidationStrategy2<DATA_OBJECT>> uniqueValidationStrategies = new HashSet<>();
        uniqueValidationStrategies.addAll(Arrays.asList(validationStrategies));

        final Set<ConstraintViolation2<?>> constraintViolations =
            uniqueValidationStrategies
                .stream()
                .map(validationStrategy -> validationStrategy.internalValidate(unvalidatedMyDataObject))
                .filter(constraintViolation -> constraintViolation != null)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);
        if (!constraintViolations.isEmpty()) {
            final String msg = "There is/are " + constraintViolations.size() + " constraint violation(s)";
            throw new ConstraintViolationException2(msg, constraintViolations);
        } else {
            return unvalidatedMyDataObject;
        }
    }

    private ConstraintViolation2<DATA_OBJECT> internalValidate(final DATA_OBJECT unvalidatedMyDataObject) {
        final ConstraintViolation2<DATA_OBJECT> constraintViolation =
                this.validator.apply(unvalidatedMyDataObject, this);
        return constraintViolation;
    }
}