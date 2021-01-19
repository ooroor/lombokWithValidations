package net.barakiroth.lombokwithvalidations.validation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Validator {

    private Validator() {
    }

    /**
     * @return The validated data object if it brought about no or only warning constraint violations
     * @throws ConstraintViolationException if at least one constraint violation is found that has error severity.
     */
    public static <DATA_OBJECT> DATA_OBJECT validate(
            final DATA_OBJECT unvalidatedMyDataObject,
            final Set<CategorizedValidationStrategy<DATA_OBJECT>> categorizedValidationStrategies,
            final Collection<ConstraintViolation<DATA_OBJECT>> constraintViolationsParm) throws ConstraintViolationException {

        final Set<ConstraintViolation<DATA_OBJECT>> constraintViolations =
                categorizedValidationStrategies
                        .stream()
                        .map(categorizedValidationStrategy -> categorizedValidationStrategy.getValidationStrategy().validate(unvalidatedMyDataObject, categorizedValidationStrategy.getSeverity()))
                        .filter(constraintViolation -> constraintViolation != null)
                        .collect(HashSet::new, HashSet::add, HashSet::addAll);

        if (constraintViolationsParm != null) {
            constraintViolationsParm.addAll(constraintViolations);
        }

        final DATA_OBJECT validatedMyDataObject;
        if (constraintViolations
                .stream()
                .noneMatch(
                        (constraintViolation) ->
                                constraintViolation.getCategorizedValidationStrategy().getSeverity().equals(IValidationStrategy.Severity.ERR))) {
            validatedMyDataObject = unvalidatedMyDataObject;
        } else {
            final String msg =
                    "There "
                            + (constraintViolations.size() == 1 ? "is " : "are ")
                            + constraintViolations.size()
                            + " constraint violation"
                            + (constraintViolations.size() > 1 ? "s" : "")
                            + ", and at least one of them is of type "
                            + IValidationStrategy.Severity.ERR;
            throw new ConstraintViolationException(msg, constraintViolations);
        }

        return validatedMyDataObject;
    }

    public static <DATA_OBJECT> ConstraintViolation<DATA_OBJECT> validate(
            final DATA_OBJECT unvalidatedDataObject,
            final CategorizedValidationStrategy<DATA_OBJECT> categorizedValidationStrategy,
            final String errMsg,
            final Function<DATA_OBJECT, Boolean> validator,
            final Pair<String, Object>...involvedFieldsAndValues

            ) {
        final ConstraintViolation<DATA_OBJECT> constraintViolation;
        if (validator.apply(unvalidatedDataObject)) {
            final Set<Pair<String, Object>> fieldsInvolvedInTheViolation =
                    new HashSet<>(Arrays.asList(involvedFieldsAndValues));
            constraintViolation =
                    new ConstraintViolation<>(categorizedValidationStrategy, errMsg, fieldsInvolvedInTheViolation);
        } else {
            constraintViolation = null;
        }
        return constraintViolation;
    }
}