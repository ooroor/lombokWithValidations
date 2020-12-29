package net.barakiroth.lombokwithvalidations.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public abstract class AbstractValidationStrategy<DATA_OBJECT> {

    private final String name;
    private final BiFunction<
            DATA_OBJECT,
            AbstractValidationStrategy<DATA_OBJECT>,
            ConstraintViolation<DATA_OBJECT>
            > validator;

    protected AbstractValidationStrategy(
            final String name,
            final BiFunction<
                    DATA_OBJECT,
                    AbstractValidationStrategy<DATA_OBJECT>,
                    ConstraintViolation<DATA_OBJECT>
                    > validator) {
        this.name = name;
        this.validator = validator;
    }

    public static <DATA_OBJECT> DATA_OBJECT validate(
            final DATA_OBJECT unvalidatedMyDataObject,
            final AbstractValidationStrategy<DATA_OBJECT>[] validationStrategies) {

        final Set<AbstractValidationStrategy<DATA_OBJECT>> uniqueValidationStrategies = new HashSet<>();
        uniqueValidationStrategies.addAll(Arrays.asList(validationStrategies));

        final Set<ConstraintViolation<?>> constraintViolations =
                uniqueValidationStrategies
                        .stream()
                        .map(validationStrategy -> validationStrategy.internalValidate(unvalidatedMyDataObject))
                        .filter(constraintViolation -> constraintViolation != null)
                        .collect(HashSet::new, HashSet::add, HashSet::addAll);
        if (!constraintViolations.isEmpty()) {
            final String msg = "There " + (constraintViolations.size() == 1 ? "is " : "are ") + constraintViolations.size() + " constraint violation" + (constraintViolations.size() > 1 ? "s" : "");
            throw new ConstraintViolationException(msg, constraintViolations);
        } else {
            return unvalidatedMyDataObject;
        }
    }

    private ConstraintViolation<DATA_OBJECT> internalValidate(final DATA_OBJECT unvalidatedMyDataObject) {
        final ConstraintViolation<DATA_OBJECT> constraintViolation =
                this.validator.apply(unvalidatedMyDataObject, this);
        return constraintViolation;
    }

    public String toString() {
        return this.name;
    }
}