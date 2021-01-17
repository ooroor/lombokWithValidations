package net.barakiroth.lombokwithvalidations.domain;

import net.barakiroth.lombokwithvalidations.validation.CategorizedValidationStrategy;
import net.barakiroth.lombokwithvalidations.validation.ConstraintViolation;
import net.barakiroth.lombokwithvalidations.validation.IValidationStrategy;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public enum MyDataObjectValidationStrategy
        implements IValidationStrategy<MyDataObject> {
    I_IS_7(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz, final IValidationStrategy.Severity severity) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getI() != 7) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("i", unvalidatedDataObject.getI()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(CategorizedValidationStrategy.of(thiz, severity), "i differs from 7", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    ),
    S_IS_4_LONG(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz, final IValidationStrategy.Severity severity) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() != 4) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(CategorizedValidationStrategy.of(thiz, severity), "length of s differs from 4", fieldsInvolvedInTheViolation);
                } else {
                    constraintViolation = null;
                }
                return constraintViolation;
            }
    ),
    S_IS_BETWEEN_7_AND_11_LONG(
            (final MyDataObject unvalidatedDataObject, final MyDataObjectValidationStrategy thiz, final IValidationStrategy.Severity severity) -> {
                final ConstraintViolation<MyDataObject> constraintViolation;
                if (unvalidatedDataObject.getS() == null || unvalidatedDataObject.getS().length() < 7 || unvalidatedDataObject.getS().length() > 11) {
                    final Set<Pair<String, Object>> fieldsInvolvedInTheViolation = new HashSet<>() {{
                        add(new ImmutablePair("s", unvalidatedDataObject.getS()));
                    }};
                    constraintViolation =
                            new ConstraintViolation<>(CategorizedValidationStrategy.of(thiz, severity), "length of s not between 7 and 11", fieldsInvolvedInTheViolation);
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

    private final TriFunction<MyDataObject, MyDataObjectValidationStrategy, IValidationStrategy.Severity, ConstraintViolation<MyDataObject>> validator;

    MyDataObjectValidationStrategy(
            final TriFunction<MyDataObject, MyDataObjectValidationStrategy, IValidationStrategy.Severity, ConstraintViolation<MyDataObject>> validator) {
        this.validator = validator;
    }

    @Override
    public ConstraintViolation<MyDataObject> validate(final MyDataObject unvalidatedMyDataObject, final IValidationStrategy.Severity severity) {
        return this.validator.apply(unvalidatedMyDataObject, this, severity);
    }

    @FunctionalInterface
    private interface TriFunction<T, U, V, R>{
        R apply(final T t, final U u, final V v);
    }
}
