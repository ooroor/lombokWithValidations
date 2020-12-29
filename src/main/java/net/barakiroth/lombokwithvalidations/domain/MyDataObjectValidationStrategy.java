package net.barakiroth.lombokwithvalidations.domain;

import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public enum MyDataObjectValidationStrategy
        implements IValidationStrategy<MyDataObject> {
    I_IS_7(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getI() != 7) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("i", unvalidatedDataObject.getI()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(thiz, "i differs from 7", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    ),
    S_IS_4_LONG(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() != 4) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(thiz, "length of s differs from 4", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    ),
    S_IS_BETWEEN_7_AND_11_LONG(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() < 7 || unvalidatedDataObject.getS().length() > 11) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(thiz, "length of s not between 7 and 11", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    );

    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_01 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.I_IS_7, MyDataObjectValidationStrategy.S_IS_BETWEEN_7_AND_11_LONG};
    public static final MyDataObjectValidationStrategy[] VALIDATION_STRATEGIES_02 =
            new MyDataObjectValidationStrategy[]{MyDataObjectValidationStrategy.S_IS_4_LONG};

    private final BiFunction<MyDataObject, MyDataObjectValidationStrategy, ConstraintViolation<MyDataObject>> validator;

    MyDataObjectValidationStrategy(
            final BiFunction<MyDataObject, MyDataObjectValidationStrategy, ConstraintViolation<MyDataObject>> validator) {
        this.validator = validator;
    }

    @Override
    public ConstraintViolation<MyDataObject> validate(final MyDataObject unvalidatedMyDataObject) {
        return this.validator.apply(unvalidatedMyDataObject, this);
    }
}
