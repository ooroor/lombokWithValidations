package net.barakiroth.lombokwithvalidations.validation;

public interface IValidationStrategy<DATA_OBJECT> {
    ConstraintViolation<DATA_OBJECT> validate(final DATA_OBJECT unvalidatedMyDataObject);
}