package net.barakiroth.lombokwithvalidations.domain;

import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public enum MyDataObjectValidationStrategy {

    I_IS_7((unvalidatedMyDataObject, thiz) -> {
        final ConstraintViolation<MyDataObjectValidationStrategy> constraintViolation;
        if (unvalidatedMyDataObject.getI() != 7) {
            constraintViolation = new ConstraintViolation<>(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("i", unvalidatedMyDataObject.getI()));
                    }},
                    "i differs from 7");
        } else {
            constraintViolation = null;
        }
        return constraintViolation;
    }),
    S_IS_4_LONG((unvalidatedMyDataObject, thiz) -> {
        final ConstraintViolation<MyDataObjectValidationStrategy> constraintViolation;
        if (unvalidatedMyDataObject.getS() == null || unvalidatedMyDataObject.getS().length() != 4) {
            constraintViolation = new ConstraintViolation<>(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedMyDataObject.getS()));
                    }},
                    "length of s differs from 4");
        } else {
            constraintViolation = null;
        }
        return constraintViolation;
    }),
    S_IS_BETWEEN_7_AND_11_LONG((unvalidatedMyDataObject, thiz) -> {
        final ConstraintViolation<MyDataObjectValidationStrategy> constraintViolation;
        if (unvalidatedMyDataObject.getS() == null || unvalidatedMyDataObject.getS().length() < 7 || unvalidatedMyDataObject.getS().length() > 11) {
            constraintViolation = new ConstraintViolation<>(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedMyDataObject.getS()));
                    }},
                    "length of s not between 7 and 11");
        } else {
            constraintViolation = null;
        }
        return constraintViolation;
    });

    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.S_IS_4_LONG};

    final BiFunction<MyDataObject, MyDataObjectValidationStrategy, ConstraintViolation<MyDataObjectValidationStrategy>> validator;

    MyDataObjectValidationStrategy(final BiFunction<MyDataObject, MyDataObjectValidationStrategy, ConstraintViolation<MyDataObjectValidationStrategy>> validator) {
        this.validator = validator;
    }

    public static MyDataObject validate(final MyDataObject unvalidatedMyDataObject, final MyDataObjectValidationStrategy[] validationStrategies) {

        final Set<MyDataObjectValidationStrategy> uniqueValidationStrategies = new HashSet<>();
        uniqueValidationStrategies.addAll(Arrays.asList(validationStrategies));

        final Set<ConstraintViolation<MyDataObjectValidationStrategy>> constraintViolations =
            uniqueValidationStrategies
                .stream()
                .map(myDataObjectValidationStrategy -> myDataObjectValidationStrategy.internalValidate(unvalidatedMyDataObject))
                .filter(constraintViolation -> constraintViolation != null)
                .collect(HashSet::new, HashSet::add, HashSet::addAll);
        if (!constraintViolations.isEmpty()) {
            final String msg = "There is/are " + constraintViolations.size() + " constraint violation(s)";
            throw new MyDataObjectConstraintViolationException(msg, constraintViolations);
        } else {
            return unvalidatedMyDataObject;
        }
    }

    private ConstraintViolation<MyDataObjectValidationStrategy> internalValidate(final MyDataObject unvalidatedMyDataObject) {
        final ConstraintViolation<MyDataObjectValidationStrategy> constraintViolation =
                this.validator.apply(unvalidatedMyDataObject, this);
        return constraintViolation;
    }
}