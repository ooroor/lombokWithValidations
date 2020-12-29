package net.barakiroth.lombokwithvalidations.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator<DATA_OBJECT> {

    public DATA_OBJECT validate(
            final DATA_OBJECT unvalidatedMyDataObject,
            final IValidationStrategy<DATA_OBJECT>[] validationStrategies) {


        final Set<IValidationStrategy<DATA_OBJECT>> uniqueValidationStrategies = new HashSet<>();
        uniqueValidationStrategies.addAll(Arrays.asList(validationStrategies));


        final Set<ConstraintViolation<?>> constraintViolations =
                uniqueValidationStrategies
                        .stream()
                        .map(validationStrategy -> validationStrategy.validate(unvalidatedMyDataObject))
                        .filter(constraintViolation -> constraintViolation != null)
                        .collect(HashSet::new, HashSet::add, HashSet::addAll);
        final DATA_OBJECT validatedMyDataObject;
        if (!constraintViolations.isEmpty()) {
            final String msg = "There " + (constraintViolations.size() == 1 ? "is " : "are ") + constraintViolations.size() + " constraint violation" + (constraintViolations.size() > 1 ? "s" : "");
            throw new ConstraintViolationException(msg, constraintViolations);
        } else {
            validatedMyDataObject = unvalidatedMyDataObject;
        }

        return validatedMyDataObject;
    }
}