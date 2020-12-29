package net.barakiroth.lombokwithvalidations.validation;

import java.util.function.BiFunction;

public abstract class AbstractValidationStrategy<DATA_OBJECT>
        implements IValidationStrategy<DATA_OBJECT> {

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

    @Override
    public ConstraintViolation<DATA_OBJECT> validate(final DATA_OBJECT unvalidatedMyDataObject) {
        final ConstraintViolation<DATA_OBJECT> constraintViolation =
                this.validator.apply(unvalidatedMyDataObject, this);
        return constraintViolation;
    }

    public String toString() {
        return this.name;
    }
}