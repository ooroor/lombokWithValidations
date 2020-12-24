package net.barakiroth.lombokwithvalidations.domain;

import net.barakiroth.lombokwithvalidations.domain.exceptions.MyDataObjectConstraintViolationException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public enum MyDataObjectValidationStrategy {

    I_IS_7((unvalidatedMyDataObject, thiz) -> {
        final MyDataObjectConstraintViolation myDataObjectConstraintViolation;
        if (unvalidatedMyDataObject.getI() != 7) {
            myDataObjectConstraintViolation = new MyDataObjectConstraintViolation(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("i", unvalidatedMyDataObject.getI()));
                    }},
                    "i differs from 7");
        } else {
            myDataObjectConstraintViolation = null;
        }
        return myDataObjectConstraintViolation;
    }),
    S_IS_4_LONG((unvalidatedMyDataObject, thiz) -> {
        final MyDataObjectConstraintViolation myDataObjectConstraintViolation;
        if (unvalidatedMyDataObject.getS().length() != 4) {
            myDataObjectConstraintViolation = new MyDataObjectConstraintViolation(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedMyDataObject.getS()));
                    }},
                    "\"length of s differs from 4");
        } else {
            myDataObjectConstraintViolation = null;
        }
        return myDataObjectConstraintViolation;
    }),
    S_IS_BETWEEN_7_AND_11_LONG((unvalidatedMyDataObject, thiz) -> {
        final MyDataObjectConstraintViolation myDataObjectConstraintViolation;
        if (unvalidatedMyDataObject.getS() == null || unvalidatedMyDataObject.getS().length() < 7 || unvalidatedMyDataObject.getS().length() > 11) {
            myDataObjectConstraintViolation = new MyDataObjectConstraintViolation(
                    thiz,
                    new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedMyDataObject.getS()));
                    }},
                    "\"\"length of s not between 7 and 11");
        } else {
            myDataObjectConstraintViolation = null;
        }
        return myDataObjectConstraintViolation;
    })
    ;

    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.S_IS_4_LONG};

    final BiFunction<MyDataObject, MyDataObjectValidationStrategy, MyDataObjectConstraintViolation> validator;

    MyDataObjectValidationStrategy(final BiFunction<MyDataObject, MyDataObjectValidationStrategy, MyDataObjectConstraintViolation> validator) {
        this.validator = validator;
    }

    public static MyDataObject validate(final MyDataObject unvalidatedMyDataObject, final MyDataObjectValidationStrategy[] validationStrategies) {

        if (validationStrategies != null) {

            final Set<MyDataObjectConstraintViolation> myDataObjectConstraintViolations =
                Arrays
                    .stream(validationStrategies)
                    .map(myDataObjectValidationStrategy -> myDataObjectValidationStrategy.internalValidate(unvalidatedMyDataObject))
                    .filter(myDataObjectConstraintViolation -> myDataObjectConstraintViolation != null)
                    .collect(HashSet::new, HashSet::add, HashSet::addAll)
                    ;
            if (!myDataObjectConstraintViolations.isEmpty()) {
                throw new MyDataObjectConstraintViolationException(myDataObjectConstraintViolations);
            } else {
                return unvalidatedMyDataObject;
            }
        } else {
            return unvalidatedMyDataObject;
        }
    }

    private MyDataObjectConstraintViolation internalValidate(final MyDataObject unvalidatedMyDataObject) {
        final MyDataObjectConstraintViolation myDataObjectConstraintViolation = this.validator.apply(unvalidatedMyDataObject, this);
        return myDataObjectConstraintViolation;
    }
}