package net.barakiroth.lombokwithvalidations.validation;

public interface IValidationStrategy<DATA_OBJECT> {
    enum Severity {ERR, WARN}

    ConstraintViolation<DATA_OBJECT> validate(final DATA_OBJECT unvalidatedMyDataObject, final IValidationStrategy.Severity severity);
}